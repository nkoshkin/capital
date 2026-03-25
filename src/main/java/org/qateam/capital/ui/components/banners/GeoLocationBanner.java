package org.qateam.capital.ui.components.banners;

import com.microsoft.playwright.Page;
import org.qateam.capital.ui.components.BaseComponent;

public class GeoLocationBanner extends BaseComponent<GeoLocationBanner> {
    private static final String componentSelector = "div[data-sentry-component='Box']:has(button[data-type='wrong_location_cancel'])";

    private final String STAY_HERE_BUTTON = "button[data-type='wrong_location_cancel']";

    public GeoLocationBanner(Page page) {
        super(page, componentSelector);
    }

    public GeoLocationBanner stayHere(){
        componentLocator.locator(STAY_HERE_BUTTON).click();
        return this;
    }
}
