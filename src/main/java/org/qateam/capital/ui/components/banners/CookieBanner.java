package org.qateam.capital.ui.components.banners;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.qateam.capital.ui.components.BaseComponent;

public class CookieBanner extends BaseComponent {
    private static final String componentSelector = "//div[@data-sentry-component='CookieBannerInitiator']";

    public CookieBanner(Page page) {
        super(page, componentSelector);
    }


    public Locator getButtonReject(){
        return componentLocator.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Отклонить все"));
    }

}
