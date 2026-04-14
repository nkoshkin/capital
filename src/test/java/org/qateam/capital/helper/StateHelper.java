package org.qateam.capital.helper;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.qateam.capital.context.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StateHelper {
    private static final Logger log = LoggerFactory.getLogger(StateHelper.class);

    private StateHelper() {}

    public static void clearContext(UserContext userContext, Page page) {

        try {
            userContext.getContext().clearCookies();
            page.evaluate("localStorage.clear(); sessionStorage.clear();");
        } catch (PlaywrightException e) {
            log.warn("Could not clear storage");
        }
    }
}