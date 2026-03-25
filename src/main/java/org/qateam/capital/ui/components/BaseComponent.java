package org.qateam.capital.ui.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public abstract class BaseComponent<T extends BaseComponent<T>> {

    protected static final Logger logger = LoggerFactory.getLogger(BaseComponent.class);

    protected final Page page;
    protected final Locator componentLocator;
    protected final String selector;
    protected final Locator parentLocator;

    public BaseComponent(Page page, String selector, Locator parentLocator){
        this.page = page;
        this.selector = selector;
        this.parentLocator = parentLocator;
        if (parentLocator != null){
            this.componentLocator = parentLocator.locator(selector).first();
        } else {
            this.componentLocator = page.locator(selector).first();
        }
        logger.debug("Component created: {} with selector: {} with parent: {}", getClass().getSimpleName(), selector, parentLocator);
    }

    public BaseComponent(Page page, String selector){
        this(page, selector, null);
    }



    @SuppressWarnings("unchecked")
    public T waitForVisible(){
        componentLocator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(Duration.ofSeconds(10).toMillis()));
        return (T) this;
    }

    public void waitForHidden(){
        componentLocator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(Duration.ofSeconds(10).toMillis()));
    }

    public Boolean isVisible(){
        return componentLocator.isVisible();
    }

    protected void clickButtonBySelector(String buttonSelector){
        componentLocator.locator(buttonSelector).click();
    }
}
