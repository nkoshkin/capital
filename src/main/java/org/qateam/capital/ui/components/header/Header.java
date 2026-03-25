package org.qateam.capital.ui.components.header;

import com.microsoft.playwright.Page;
import org.qateam.capital.ui.components.BaseComponent;

public class Header extends BaseComponent<Header> {

    private static final String componentSelector = "#header";
    private final TopPanel topPanel;
    private final Holder holder;
    private final Menu menu;

    public Header(Page page) {
        super(page, componentSelector);
        topPanel = new TopPanel(page, componentLocator);
        holder = new Holder(page, componentLocator);
        menu = new Menu(page, componentLocator);
    }

    public Menu getMenu(){
        return menu;
    }

}
