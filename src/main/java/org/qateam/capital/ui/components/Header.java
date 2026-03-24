package org.qateam.capital.ui.components;

import com.microsoft.playwright.Page;

public class Header extends BaseComponent{

    private static final String componentSelector = "//header";

    public Header(Page page) {
        super(page, componentSelector);
    }
}
