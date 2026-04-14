package org.qateam.capital.utils;

import org.qateam.capital.config.TestConfig;
import org.qateam.capital.enums.LicenseType;

public class UrlUtils {
    public static String buildUrl(LicenseType license, String pagePath) {
        String base = TestConfig.getInstance().getBaseUrl();
        String licensePath = license.path();
        String path = (pagePath == null || pagePath.isEmpty()) ? ""
                : (pagePath.startsWith("/")) ? pagePath : "/" + pagePath;

        return base + licensePath + path;
    }
}
