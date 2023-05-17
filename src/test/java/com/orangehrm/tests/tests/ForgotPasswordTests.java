package com.orangehrm.tests.tests;

import com.orangehrm.tests.OrangeHRMTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.tests.driver.DriverFactory.driver;
import static com.orangehrm.tests.pages.PageFactory.forgotPasswordPage;
import static com.orangehrm.tests.pages.PageFactory.loginPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ForgotPasswordTests extends OrangeHRMTest {

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Given I am on the Forgot password page
        loginPage().clickForgotPassword();
    }

    @Test
    public void cancelButtonDirectsToCorrectPage() {
        // When I click on the Cancel button
        forgotPasswordPage().clickCancelButton();

        // Then I am redirected to the Login page
        assertTrue(currentUrl().contains("/auth/login"));
    }

    @Test
    public void errorIfSubmittingWithoutEnteringUsername() {
        // When I click 'Reset Password' without entering a Username
        forgotPasswordPage().clickResetPassword();

        // Then I am warned that a Username is required
        String expected = "Required";
        assertEquals(forgotPasswordPage().getErrorRequiredField(), expected);
    }

    @Test
    public void validUsernameSendsLinkToResetPassword() {
        // When I enter a submit a valid user email
        forgotPasswordPage().enterUsername("Admin");
        forgotPasswordPage().clickResetPassword();

        // Then I get a message with a valid link to reset my password
        assertTrue(currentUrl().contains("/auth/sendPasswordReset"));
    }

    private String currentUrl() {
        return driver().getCurrentUrl();
    }
}
