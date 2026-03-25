package org.qateam.capital.ui.components.header;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.qateam.capital.ui.components.BaseComponent;

public class TopPanel extends BaseComponent<TopPanel> {
    private static final String componentSelector = "[data-sentry-component='TopPanelScroll']";

    public TopPanel(Page page, Locator parentLocator) {
        super(page, componentSelector, parentLocator);
    }
}
