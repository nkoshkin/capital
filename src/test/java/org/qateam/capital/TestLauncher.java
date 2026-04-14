package org.qateam.capital;

import io.qameta.allure.Allure;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.qateam.capital.config.TestConfig;
import org.qateam.capital.context.GlobalContextManager;
import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;
import org.qateam.capital.listeners.AllureTestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class TestLauncher {
    private static final Logger log = LoggerFactory.getLogger(TestLauncher.class);
    private static AllureTestListener allureListener;

    public static void main(String[] args) {
        int exitCode = 0;
        try {
            TestConfig config = TestConfig.getInstance();

            Set<UserType> users = config.getUserFilter() == null
                    ? Set.of(UserType.values())
                    : config.getUserFilter();
            Set<LicenseType> licenses = config.getLicenseFilter() == null
                    ? Set.of(LicenseType.values())
                    : config.getLicenseFilter();
            log.info("USERS: {}", users);
            GlobalContextManager ctxMgr = GlobalContextManager.getInstance();
            ctxMgr.createContexts(users);

            LauncherDiscoveryRequestBuilder builder = LauncherDiscoveryRequestBuilder.request()
                    .selectors(selectPackage("org.qateam.capital.ui"));

            if (config.getPageFilter() != null) {
                builder.filters(TagFilter.includeTags(config.getPageFilter().toArray(new String[0])));
                log.info("Filtering by page tags: {}", config.getPageFilter());
            }

            LauncherDiscoveryRequest request = builder.build();

            Launcher launcher = LauncherFactory.create();
            SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
            allureListener = new AllureTestListener();
            launcher.registerTestExecutionListeners(summaryListener, allureListener);

            log.info("========================================");
            log.info("Starting test execution");
            log.info("Users: {}", users);
            log.info("Licenses: {}", licenses);
            log.info("Profile: {}", System.getProperty("test.profile", "default"));
            log.info("========================================");

            launcher.execute(request);

            TestExecutionSummary summary = summaryListener.getSummary();
            printSummary(summary);
            exitCode = summary.getTestsFailedCount() > 0 ? 1 : 0;

        } catch (Exception e) {
            log.error("Test execution failed", e);
            Allure.addAttachment("Launcher Error", "text/plain", e.getMessage());
            exitCode = 1;
        } finally {
            GlobalContextManager.getInstance().closeAll();
            System.exit(exitCode);
        }
    }

    private static void printSummary(TestExecutionSummary summary) {
        System.out.println("\n========================================");
        System.out.println("TEST EXECUTION SUMMARY");
        System.out.println("========================================");
        System.out.printf("Total tests:    %d%n", summary.getTestsFoundCount());
        System.out.printf("Successful:     %d ✅%n", summary.getTestsSucceededCount());
        System.out.printf("Failed:         %d ❌%n", summary.getTestsFailedCount());
        System.out.printf("Skipped:        %d ⏭️%n", summary.getTestsSkippedCount());
        System.out.printf("Total time:     %d ms%n", summary.getTimeFinished() - summary.getTimeStarted());
        System.out.println("========================================");
    }
}
