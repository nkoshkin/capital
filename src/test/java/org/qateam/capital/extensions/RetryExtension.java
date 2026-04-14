package org.qateam.capital.extensions;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.TestAbortedException;
import org.qateam.capital.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryExtension implements TestExecutionExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(RetryExtension.class);

    private int attempts = 0;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        int max = TestConfig.getInstance().getRetryAttempts();
        long delay = TestConfig.getInstance().getRetryDelay();
        attempts++;
        if (throwable instanceof TestAbortedException) throw throwable;
        if (attempts < max) {
            log.warn("Retry test ({}/{})", attempts, max);
            Thread.sleep(delay);
            throw new TestAbortedException("Retry", throwable);
        }
        throw throwable;
    }
}
