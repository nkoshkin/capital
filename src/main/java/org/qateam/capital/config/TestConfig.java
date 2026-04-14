package org.qateam.capital.config;

import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TestConfig {

    private static final Logger log = LoggerFactory.getLogger(TestConfig.class);

    private final AppConfig appConfig;

    private TestConfig(){
        appConfig = AppConfig.getInstance();
    }

    public String getBaseUrl() {
        return appConfig.getWebConfig().url();
    }

    public Integer getViewPortWidth() {return appConfig.getBrowserConfig().viewportWidth();}

    public Set<UserType> getUserFilter() {
        String val = System.getProperty("users", "ALL");
        if ("ALL".equalsIgnoreCase(val)) return null;
        return Arrays.stream(val.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(UserType::valueOf)
                .collect(Collectors.toSet());
    }

    public Set<LicenseType> getLicenseFilter() {
        String val = System.getProperty("licenses", "ALL");
        if ("ALL".equalsIgnoreCase(val)) return null;
        return Arrays.stream(val.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(LicenseType::valueOf)
                .collect(Collectors.toSet());
    }

    public Set<String> getPageFilter() {
        String val = System.getProperty("pages", "ALL");
        if ("ALL".equalsIgnoreCase(val)) return null;
        return Arrays.stream(val.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    public Integer getViewPortHeight() {
        return appConfig.getBrowserConfig().viewportHeight();
    }

    public String getLocale() {
        return appConfig.getBrowserConfig().locale();
    }

    public String getTimezone() {
        return appConfig.getBrowserConfig().timezone();
    }

    public int getRetryAttempts() {
        return appConfig.getRetryConfig().attempts();
    }

    public long getRetryDelay() {
        return appConfig.getRetryConfig().delay();
    }


    private static final class InstanceHolder {
        private static final TestConfig instance = new TestConfig();
    }

    public static TestConfig getInstance(){
        return InstanceHolder.instance;
    }
}
