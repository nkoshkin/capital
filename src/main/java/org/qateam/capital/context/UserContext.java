package org.qateam.capital.context;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.qateam.capital.enums.UserType;

public class UserContext {
    private final UserType userType;
    private final BrowserContext context;
    private final Page page;

    public UserContext(UserType userType, BrowserContext context, Page page) {
        this.userType = userType;
        this.context = context;
        this.page = page;
    }
}
