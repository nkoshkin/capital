package org.qateam.capital.ui.pages;

import com.microsoft.playwright.Page;
import org.qateam.capital.licenses.License;
import org.qateam.capital.ui.components.Header;
import org.qateam.capital.utils.URLBuilder;

import java.net.URI;
import java.net.URL;

public class BasePage {
    protected final String basePageUrl = "https://capital.com/en-au";
    protected final Page page;
    protected final License license;
    protected final Header header;

    public BasePage(Page page, License license) {
        this.page = page;
        this.license = license;
        this.header = new Header(page);
    }

    public void navigate(){
        URI.create()
        this.page.navigate(URLBuilder.build(basePageUrl, license.));
    }
}
