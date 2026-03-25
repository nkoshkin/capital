package org.qateam.capital.ui.components.banners;

import com.microsoft.playwright.Page;
import org.qateam.capital.ui.components.BaseComponent;

public class CookieBanner extends BaseComponent<CookieBanner> {
    private static final String componentSelector = "div[data-type='cb'] > .ot-sdk-container";
    private final String BUTTON_REJECT_ALL_SELECTOR = "#onetrust-reject-all-handler";
    private final String BUTTON_ACCEPT_ALL_SELECTOR = "#onetrust-accept-btn-handler";
    private final String BUTTTON_CONFIG_COOKIES_SELECTOR = "#onetrust-pc-btn-handler";


    public CookieBanner(Page page) {
        super(page, componentSelector);
    }

    public CookieBanner rejectAll(){
        clickButtonBySelector(BUTTON_REJECT_ALL_SELECTOR);
        return this;
    }

    public CookieBanner acceptAll(){
        clickButtonBySelector(BUTTON_ACCEPT_ALL_SELECTOR);
        return this;
    }

}
