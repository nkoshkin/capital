package org.qateam.capital.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public abstract class BaseComponent {
    protected final Page page;
    protected final Locator componentLocator;

    public BaseComponent(Page page, String selector){
        this.page = page;
        this.componentLocator = page.locator(selector);
    }

    public Boolean isVisible(){
        return componentLocator.isVisible();
    }
}
