package org.qateam.capital.annotations;

import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class AnnotationExtractor {
    private AnnotationExtractor() {}

    public static Set<UserType> extractUsers(Method method, Class<?> testClass) {
        Set<UserType> users = new LinkedHashSet<>();
        if (method != null && method.isAnnotationPresent(WithUsers.class)) {
            for (WithUsers ann : method.getAnnotationsByType(WithUsers.class)) {
                if (ann.all()) users.addAll(Set.of(UserType.values()));
                else users.addAll(Set.of(ann.value()));
            }
        }
        if (users.isEmpty() && testClass != null && testClass.isAnnotationPresent(WithUsers.class)) {
            for (WithUsers ann : testClass.getAnnotationsByType(WithUsers.class)) {
                if (ann.all()) users.addAll(Set.of(UserType.values()));
                else users.addAll(Set.of(ann.value()));
            }
        }
        if (users.isEmpty()) users.addAll(Set.of(UserType.values()));
        return users;
    }

    public static Set<LicenseType> extractLicenses(Method method, Class<?> testClass) {
        Set<LicenseType> licenses = new LinkedHashSet<>();
        if (method != null && method.isAnnotationPresent(WithLicenses.class)) {
            for (WithLicenses ann : method.getAnnotationsByType(WithLicenses.class)) {
                if (ann.all()) licenses.addAll(Set.of(LicenseType.values()));
                else licenses.addAll(Set.of(ann.value()));
            }
        }
        if (licenses.isEmpty() && testClass != null && testClass.isAnnotationPresent(WithLicenses.class)) {
            for (WithLicenses ann : testClass.getAnnotationsByType(WithLicenses.class)) {
                if (ann.all()) licenses.addAll(Set.of(LicenseType.values()));
                else licenses.addAll(Set.of(ann.value()));
            }
        }
        if (licenses.isEmpty()) licenses.addAll(Set.of(LicenseType.values()));
        return licenses;
    }

    public static boolean shouldSkipForUsers(Method method, Class<?> testClass, UserType user) {
        if (method != null && method.isAnnotationPresent(SkipForUsers.class)) {
            for (SkipForUsers ann : method.getAnnotationsByType(SkipForUsers.class)) {
                if (Arrays.asList(ann.value()).contains(user)) return true;
            }
        }
        if (testClass != null && testClass.isAnnotationPresent(SkipForUsers.class)) {
            for (SkipForUsers ann : testClass.getAnnotationsByType(SkipForUsers.class)) {
                if (Arrays.asList(ann.value()).contains(user)) return true;
            }
        }
        return false;
    }
}
