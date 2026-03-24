package org.qateam.capital.ui.components.banners;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CookieBanner {
    private final Page page;
    private final String path = "//div[@data-sentry-component='CookieBannerInitiator']";
    private final Locator currentLocator;

    public CookieBanner(Page page) {
        this.page = page;
        currentLocator = page.locator("//div[@data-sentry-component='CookieBannerInitiator']");
    }


    public Locator getButtonReject(){
        return currentLocator.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Отклонить все"));
    }

    public boolean isVisible() {
        return getButtonReject().isVisible();
    }
}
