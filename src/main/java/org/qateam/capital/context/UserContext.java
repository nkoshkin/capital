package org.qateam.capital.context;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;
import org.qateam.capital.utils.UrlUtils;

public class UserContext {
    private final UserType userType;
    private final BrowserContext context;
    private final Page page;

    public UserContext(UserType userType, BrowserContext context, Page page) {
        this.userType = userType;
        this.context = context;
        this.page = page;
    }

    public UserType getUserType() { return userType; }
    public BrowserContext getContext() { return context; }
    public Page getPage() { return page; }

    public String getFullUrl(LicenseType licenseType, String pagePath){
        return UrlUtils.buildUrl(licenseType, pagePath);
    }
}
