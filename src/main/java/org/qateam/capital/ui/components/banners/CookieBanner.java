package org.qateam.capital.ui.components.banners;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.qateam.capital.ui.components.BaseComponent;

public class CookieBanner extends BaseComponent {
    private static final String rootSelector = "//div[@data-sentry-component='CookieBannerInitiator']";

    public CookieBanner(Page page) {
        super(page, rootSelector);
    }


    public Locator getButtonReject(){
        return rootLocator.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Отклонить все"));
    }

}
