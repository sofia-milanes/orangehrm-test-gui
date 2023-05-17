package com.orangehrm.tests.tests;

import com.orangehrm.tests.OrangeHRMTest;
import com.orangehrm.tests.utils.OrangeWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.tests.driver.DriverFactory.driver;
import static com.orangehrm.tests.pages.PageFactory.*;
import static org.testng.Assert.*;

public class LoginTests extends OrangeHRMTest {

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        new OrangeWait().waitUntilStable();
    }

    @Test
    public void loginSuccess() {
        // When I enter correct credentials
        loginPage().doLogin("Admin", "admin123");

        // Then I got logged in and redirected to Dashboard page
        assertTrue(currentUrl().contains("/dashboard/index"));
    }

    @Test
    public void loginErrorInvalidCredentials() {
        // When I enter incorrect credentials
        loginPage().doLogin("InvalidAdmin", "admin123");

        // Then an error is displayed
        assertTrue(loginPage().getErrorMessages().contains("Invalid credentials"));

        // And I 'm not redirected to Dashboard page
        assertFalse(currentUrl().contains("/dashboard/index"));
    }

    @Test
    public void forgotPasswordLinkDirectsToCorrectPage() {
        // When I click on the Forgot password link
        loginPage().clickForgotPassword();

        // Then I am redirected to the Forgot password page
        assertTrue(currentUrl().contains("/auth/requestPasswordResetCode"));
    }

    @Test
    public void requiredFieldsErrorSubmitWithoutEnteringAnything() {
        // When I click Submit on the login page without entering anything first
        loginPage().pressSubmitWithNoData();

        // Then I get errors telling me that email and password are required
        assertTrue(loginPage().getErrorRequiredField().contains("Required"));
    }

    private String currentUrl() {
        return driver().getCurrentUrl();
    }
}
