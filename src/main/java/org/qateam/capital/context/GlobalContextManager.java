package org.qateam.capital.context;

import com.microsoft.playwright.*;
import org.qateam.capital.config.AppConfig;
import org.qateam.capital.config.TestConfig;
import org.qateam.capital.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalContextManager {

    private static final Logger log = LoggerFactory.getLogger(GlobalContextManager.class);

    private static volatile GlobalContextManager instance;

    private final Playwright playwright;
    private final Browser browser;
    private final Map<UserType, UserContext> contexts = new ConcurrentHashMap<>();

    private GlobalContextManager() {
        AppConfig appConfig = AppConfig.getInstance();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(appConfig.getBrowserConfig().headless())
                .setSlowMo(appConfig.getBrowserConfig().slowMo())
                .setChannel(appConfig.getBrowserConfig().channel())
                .setArgs(appConfig.getBrowserConfig().args()));
    }

    public static GlobalContextManager getInstance() {
        if (instance == null) {
            synchronized (GlobalContextManager.class) {
                if (instance == null) {
                    instance = new GlobalContextManager();
                }
            }
        }
        return instance;
    }

    public void createContexts(Set<UserType> userTypes) {
        TestConfig testConfig = TestConfig.getInstance();
        for (UserType user : userTypes) {
            log.info("Create context for user: {}", user);
            contexts.computeIfAbsent(user, u -> {
                BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(testConfig.getViewPortWidth(), testConfig.getViewPortHeight())
                        .setLocale(testConfig.getLocale())
                        .setTimezoneId(testConfig.getTimezone()));
                Page page = ctx.newPage();
                if (u == UserType.AUTHORIZED) {
                    performAuthorization(page, testConfig);
                }
                return new UserContext(u, ctx, page);
            });
        }
    }

    public UserContext getContext(UserType user) {
        UserContext ctx = contexts.get(user);
        if (ctx == null) throw new IllegalStateException("Context not found for user: " + user);
        return ctx;
    }

    private void performAuthorization(Page page, TestConfig testConfig) {
        //TODO: login
    }

    public void closeAll() {
        contexts.values().forEach(ctx -> {
            try{
                ctx.getContext().close();
            } catch (Exception e) {
                log.warn("Error closing context for user: {}", ctx.getUserType(), e);
            }
        });
        contexts.clear();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        log.info("All resources closed");
    }
}
