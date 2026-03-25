package org.qateam.capital.ui.components.header;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.qateam.capital.ui.components.BaseComponent;
//TODO:: возможно стоит убрать.
public class Holder extends BaseComponent<Holder> {
    private static final String componentSelector = "[data-sentry-component='Holder']";

    public Holder(Page page, Locator parentLocator) {
        super(page, componentSelector, parentLocator);
    }
}
