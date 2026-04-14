package org.qateam.capital.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.qateam.capital.context.UserContext;
import org.qateam.capital.enums.LicenseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AllureTestListener implements TestExecutionListener {
    private final static Logger log = LoggerFactory.getLogger(AllureTestListener.class);
    private final Map<String, TestContext> contexts = new ConcurrentHashMap<>();

    @Override
    public void executionStarted(TestIdentifier test) {
        if (test.isTest()){
            TestContext ctx = new TestContext();
            ctx.start = System.currentTimeMillis();
            contexts.put(test.getUniqueId(), ctx);
            Allure.getLifecycle().updateTestCase(tc -> {
                tc.setName(test.getDisplayName());
                tc.setStart(ctx.start);
            });
        }
    }

    @Override
    public void executionFinished(TestIdentifier test, TestExecutionResult result) {
        if (test.isTest()) {
            TestContext ctx = contexts.remove(test.getUniqueId());
            if (ctx != null) {
                Allure.getLifecycle().updateTestCase(tc -> {
                    tc.setStop(System.currentTimeMillis());
                    tc.setStatus(mapStatus(result.getStatus()));
                    if (result.getStatus() == TestExecutionResult.Status.FAILED) {
                        if (ctx.screenshot != null) attachScreenshot(ctx.screenshot);
                        if (ctx.pageSource != null) attachPageSource(ctx.pageSource);
                        if (ctx.userContext != null) attachUserContext(ctx.userContext, ctx.license);
                    }
                });
            }
        }
    }

    private Status mapStatus(TestExecutionResult.Status s) {
        return switch (s) {
            case SUCCESSFUL -> Status.PASSED;
            case FAILED -> Status.FAILED;
            default -> Status.BROKEN;
        };
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot(byte[] data) {return data;}

    @Attachment(value = "Page Source", type = "text/html")
    private String attachPageSource(String src) { return src; }

    @Attachment(value = "User Context", type = "text/plain")
    private String attachUserContext(UserContext uc, LicenseType licenseType) {
        return String.format("User: %s, License: %s", uc.getUserType(), licenseType);
    }

    public void captureScreenshot(String id, byte[] screenshot) {
        TestContext ctx = contexts.get(id);
        if (ctx != null) ctx.screenshot = screenshot;
    }

    public void capturePageSource(String id, String source) {
        TestContext ctx = contexts.get(id);
        if (ctx != null) ctx.pageSource = source;
    }

    public void setUserContext(String id, UserContext uc) {
        TestContext ctx = contexts.get(id);
        if (ctx != null) ctx.userContext = uc;
    }

    public void setLicense(String id, LicenseType license) {
        TestContext ctx = contexts.get(id);
        if (ctx != null) ctx.license = license;
    }

    private static class TestContext {
        LicenseType license;
        long start;
        byte[] screenshot;
        String pageSource;
        UserContext userContext;
    }
}
