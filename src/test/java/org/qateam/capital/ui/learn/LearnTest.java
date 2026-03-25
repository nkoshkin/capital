package org.qateam.capital.ui.learn;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.qateam.capital.ui.BaseTest;
import org.qateam.capital.ui.components.banners.CookieBanner;
import org.qateam.capital.ui.components.banners.GeoLocationBanner;
import org.qateam.capital.ui.components.header.Header;

public class LearnTest extends BaseTest {
    private String uri = "https://capital.com/en-au";

    @Test
    void bannerLearnToTrade() {
        page.navigate(uri);
        var firstBanner = new CookieBanner(page);
        firstBanner
                .waitForVisible()
                .rejectAll()
                .waitForHidden();
        var secondBanner = new GeoLocationBanner(page);
        secondBanner
                .waitForVisible()
                .stayHere()
                .waitForHidden();

        var header = new Header(page);
        header.getMenu().getItem("About").hover();
        page.waitForTimeout(2000);
    }
}
