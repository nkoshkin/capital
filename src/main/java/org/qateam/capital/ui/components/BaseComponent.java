package org.qateam.capital.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class BaseComponent {

    protected final Page page;
    protected final Locator rootLocator;

    public BaseComponent(Page page, String selector){
        this.page = page;
        this.rootLocator = page.locator(selector);
    }
}
