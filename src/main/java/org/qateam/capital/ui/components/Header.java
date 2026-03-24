package org.qateam.capital.ui.components;

import com.microsoft.playwright.Page;

public class Header extends BaseComponent{

    private static final String rootSelector = "//header";

    public Header(Page page) {
        super(page, rootSelector);
    }
}
