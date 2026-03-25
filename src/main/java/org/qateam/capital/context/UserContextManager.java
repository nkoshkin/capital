package org.qateam.capital.context;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import org.qateam.capital.enums.UserType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class UserContextManager {

    private static final Logger logger = Logger.getLogger(UserContextManager.class.getName());

    private final Playwright playwright;
    private final Browser browser;
    private final Map<UserType, UserContext> contexts = new ConcurrentHashMap<>();

    public UserContextManager(Playwright playwright, Browser browser) {
        this.playwright = playwright;
        this.browser = browser;
    }

    public void initializeAllContext() {
        for (UserType userType : UserType.values()){
            createContext(userType);
        }
    }

    public void createContext(UserType userType){

    }
}
