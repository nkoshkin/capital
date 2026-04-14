package org.qateam.capital.ui;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.opentest4j.TestAbortedException;
import org.qateam.capital.config.TestConfig;
import org.qateam.capital.context.GlobalContextManager;
import org.qateam.capital.context.UserContext;
import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;
import org.qateam.capital.extensions.AllureScreenshotExtension;
import org.qateam.capital.extensions.RetryExtension;
import org.qateam.capital.helper.TestCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Set;

import static org.qateam.capital.annotations.AnnotationExtractor.shouldSkipForUsers;
import static org.qateam.capital.helper.StateHelper.clearContext;
import static org.qateam.capital.helper.TestCombinationResolver.resolve;
import static org.qateam.capital.helper.TestInvoker.invoke;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({RetryExtension.class, AllureScreenshotExtension.class})
public abstract class BaseTest {
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected static final TestConfig testConfig = TestConfig.getInstance();
    protected static final GlobalContextManager contextManager = GlobalContextManager.getInstance();

    public UserContext userContext;
    public Page page;
    public TestCombination currentCombo;

    static {
        Set<UserType> users = testConfig.getUserFilter();
        if (users == null || users.isEmpty()) {
            users = Set.of(UserType.values());
        }
        contextManager.createContexts(users);
    }

    @BeforeEach
    void setup(TestInfo info) {
        Method method = info.getTestMethod().orElse(null);
        Class<?> clazz = info.getTestClass().orElse(null);
        var combinations = resolve(method, clazz);

        if (combinations.isEmpty()) {
            throw new TestAbortedException("No combinations for " + info.getDisplayName());
        }

        for (TestCombination combo : combinations) {
            try {
                currentCombo = combo;
                userContext = contextManager.getContext(combo.user());
                page = userContext.getPage();

                if (shouldSkipForUsers(method, clazz, combo.user())) {
                    log.info("Skipping {} for {} / {}", info.getDisplayName(), combo.user(), combo.license());
                    continue;
                }

                clearContext(userContext, page);
                updateAllureTestName(info, combo.user(), combo.license());
                invoke(this, info);
            } catch (Exception e) {
                log.error("Test failed for {} / {}", combo.user(), combo.license(), e);
                throw new RuntimeException(e);
            }
        }
    }

    private void updateAllureTestName(TestInfo info, UserType user, LicenseType license) {
        Allure.getLifecycle().updateTestCase(tc -> {
            tc.setName(String.format("%s [%s | %s]", info.getDisplayName(), user, license));
            tc.getParameters().add(new io.qameta.allure.model.Parameter().setName("User").setValue(user.name()));
            tc.getParameters().add(new io.qameta.allure.model.Parameter().setName("License").setValue(license.name()));
        });
    }

    protected void navigateTo(String pagePath) {
        String url = userContext.getFullUrl(currentCombo.license(), pagePath);
        log.info("Navigating to: {}", url);
        page.navigate(url);
        page.waitForLoadState();
    }
}
