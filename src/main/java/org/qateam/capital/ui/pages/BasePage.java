package org.qateam.capital.ui.pages;

import com.microsoft.playwright.Page;
import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.ui.components.header.Header;
import org.qateam.capital.utils.UrlUtils;


public class BasePage<T extends BasePage<T>> {
    protected final String basePageUrl = "https://capital.com";
    protected final Page page;
    protected final LicenseType licenseType;
    protected final Header header;

    public BasePage(Page page, LicenseType licenseType) {
        this.page = page;
        this.licenseType = licenseType;
        this.header = new Header(page);
    }

    public void navigate(){
        this.page.navigate(UrlUtils.buildUrl(licenseType, null));
    }
}
