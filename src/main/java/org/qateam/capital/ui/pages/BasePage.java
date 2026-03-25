package org.qateam.capital.ui.pages;

import com.microsoft.playwright.Page;
import org.qateam.capital.enums.License;
import org.qateam.capital.ui.components.header.Header;
import org.qateam.capital.utils.URLBuilder;


public class BasePage<T extends BasePage<T>> {
    protected final String basePageUrl = "https://capital.com";
    protected final Page page;
    protected final License license;
    protected final Header header;

    public BasePage(Page page, License license) {
        this.page = page;
        this.license = license;
        this.header = new Header(page);
    }

    public void navigate(){
        this.page.navigate(URLBuilder.build(basePageUrl, license.path));
    }
}
