package org.qateam.capital.ui.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.qateam.capital.annotations.WithLicenses;
import org.qateam.capital.annotations.WithUsers;
import org.qateam.capital.enums.LicenseType;
import org.qateam.capital.enums.UserType;
import org.qateam.capital.ui.BaseTest;

@WithUsers(UserType.UNREGISTERED)
@WithLicenses(LicenseType.ASIC)
@Tag("Learn")
@DisplayName("Learn Page Tests")
public class LearnPageTest extends BaseTest {

    @Test
    public void bannerLearnToTrade() {
        Assertions.assertTrue(true);
    }
}
