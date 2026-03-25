package org.qateam.capital.ui.components.banners;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BannerManager {
    private static final Logger logger = LoggerFactory.getLogger(BannerManager.class);

    private final CookieBanner cookieBanner;
    private final GeoLocationBanner geoLocationBanner;

    public BannerManager(Page page){
        this.cookieBanner = new CookieBanner(page);
        this.geoLocationBanner = new GeoLocationBanner(page);
        logger.info("Initialization BannerMangger completed");
    }

    public void handleAllBanners(){
        logger.info("Start banner handler");
        cookieBanner
                .waitForVisible()
                .rejectAll()
                .waitForHidden();
        geoLocationBanner
                .waitForVisible()
                .stayHere()
                .waitForHidden();
        logger.info("Finish banner handler");
    }
}
