package org.qateam.capital.helper;

import org.junit.jupiter.api.TestInfo;

import java.lang.reflect.Method;

public final class TestInvoker {
    private TestInvoker() {}

    public static void invoke(Object testInstance, TestInfo info) throws Exception {
        Method method = info.getTestMethod()
                .orElseThrow(() -> new IllegalStateException("No test method"));
        method.setAccessible(true);
        method.invoke(testInstance);
    }
}
