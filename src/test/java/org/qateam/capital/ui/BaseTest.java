package org.qateam.capital.ui;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setSlowMo(500)
                .setChannel("chromium")
                .setArgs(List.of(
                        "--start-maximized",
                        "--disable-blink-features=AutomationControlled",
                        "--disable-dev-shm-usage",
                        "--no-sandbox"
                ))
                .setHeadless(false));
    }

    @BeforeEach
    void setUpContext(){
        browserContext = browser.newContext( new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
    }

    @AfterEach
    void closeContext(){
        if (browserContext != null) {
            browserContext.close();
        }

    }

    @AfterAll
    void tearDown(){
        browser.close();
        playwright.close();
    }
}
