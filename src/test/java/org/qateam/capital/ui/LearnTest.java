package org.qateam.capital.ui;

import org.junit.jupiter.api.Test;
import org.qateam.capital.ui.components.banners.CookieBanner;

public class LearnTest extends BaseTest{
    private String uri = "https://capital.com/en-au";

    @Test
    void bannerLearnToTrade(){
        page.navigate(uri);
        page.waitForCondition(() -> new CookieBanner(page).isVisible());
        System.out.println("Banner появился");
//       page.waitForSelector("//div[@data-sentry-component='CookieBannerInitiator']/descendant::div[@id='onetrust-button-group-parent']", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        new CookieBanner(page).getButtonReject().click();
        page.waitForTimeout(5000);

    }
}
