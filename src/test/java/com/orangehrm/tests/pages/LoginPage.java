package com.orangehrm.tests.pages;

import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;
import static org.testng.Assert.fail;

public class LoginPage extends BasePage {
    private By usernameInput = By.cssSelector("input[name='username']");
    private By passwordInput = By.cssSelector("input[name='password']");
    private By submitButton = By.cssSelector(".orangehrm-login-button");
    private By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private By requiredFieldError = By.cssSelector(".oxd-input-field-error-message");
    private By forgotPasswordLink = By.cssSelector(".orangehrm-login-forgot-header");

    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "auth/login");
    }

    @Override
    protected By onPageMarkerLoc() {
        return usernameInput;
    }

    /*******
     * Get *
     *******/
    public String getErrorMessages() {
        return oranWait().For(errorMessage).getText().trim();
    }

    public String getErrorRequiredField() {
        return oranWait().For(requiredFieldError).getText().trim();
    }

    /***********
     * Actions *
     ***********/
    public void enterUsername(String username) {
        enterText(usernameInput, username, false);
    }

    public void enterPassword(String password) {
        enterText(passwordInput, password, false);
    }

    public void pressSubmit(){
        click(submitButton, true);
    }

    public void pressSubmitWithNoData(){
        click(submitButton, false);
        oranWait().waitUntilStable();
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }

    public void doLogin(String username, String password) {
        try {
            oranWait().For(submitButton);
            enterUsername(username);
            enterPassword(password);
            pressSubmit();
        } catch (RuntimeException e) {
            fail("login failed", e);
        }
    }
}
