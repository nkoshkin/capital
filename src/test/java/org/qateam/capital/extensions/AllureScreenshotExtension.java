package org.qateam.capital.extensions;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.TestAbortedException;
import org.qateam.capital.listeners.AllureTestListener;
import org.qateam.capital.ui.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class AllureScreenshotExtension implements TestExecutionExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AllureScreenshotExtension.class);
    private static AllureTestListener listener;

    static {
        try {
            Class<?> launcher = Class.forName("org.qateam.capital.TestLauncher");
            Field f = launcher.getDeclaredField("allureListener");
            f.setAccessible(true);
            listener = (AllureTestListener) f.get(null);
        } catch (Exception e) {
            log.warn("AllureTestListener not available", e);
        }
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof TestAbortedException) throw throwable;
        if (listener != null && context.getRequiredTestInstance() instanceof BaseTest) {
            BaseTest test = (BaseTest) context.getRequiredTestInstance();
            Page page = test.page;
            if (page != null) {
                listener.captureScreenshot(context.getUniqueId(), page.screenshot());
                listener.capturePageSource(context.getUniqueId(), page.content());
                listener.setUserContext(context.getUniqueId(), test.userContext);
                // Передаём текущую лицензию
                listener.setLicense(context.getUniqueId(), test.currentCombo.license());
            }
        }
        throw throwable;
    }
}
