package org.qateam.capital.helper;

import org.qateam.capital.annotations.AnnotationExtractor;
import org.qateam.capital.config.TestConfig;
import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class TestCombinationResolver {
    private static final Logger log = LoggerFactory.getLogger(TestCombinationResolver.class);
    private static final TestConfig config = TestConfig.getInstance();

    private TestCombinationResolver() {}

    public static List<TestCombination> resolve(Method method, Class<?> testClass) {
        Set<UserType> users = AnnotationExtractor.extractUsers(method, testClass);
        Set<LicenseType> licenses = AnnotationExtractor.extractLicenses(method, testClass);
        log.info("Extracted users: {}, licenses: {}", users, licenses);

        Set<UserType> userFilter = config.getUserFilter();
        Set<LicenseType> licenseFilter = config.getLicenseFilter();
        log.info("Filters - users: {}, licenses: {}", userFilter, licenseFilter);

        List<TestCombination> combinations = new ArrayList<>();
        for (UserType user : users) {
            if (userFilter != null && !userFilter.contains(user)) continue;
            for (LicenseType license : licenses) {
                if (licenseFilter != null && !licenseFilter.contains(license)) continue;
                combinations.add(new TestCombination(user, license));
            }
        }
        log.info("Resolved {} combinations", combinations.size());
        return combinations;
    }
}