package org.qateam.capital.ui.components.header;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.qateam.capital.ui.components.BaseComponent;

public class Menu extends BaseComponent<Menu> {

    private static final String componentSelector = "[data-sentry-component='Menu']";

    public Menu(Page page, Locator parentLocator) {
        super(page, componentSelector, parentLocator);
    }

    public Locator getItem(String item){
        String locatorItem = String.format("xpath=.//a[text()='%s']", item);
        return componentLocator.locator(locatorItem);
    }
}
