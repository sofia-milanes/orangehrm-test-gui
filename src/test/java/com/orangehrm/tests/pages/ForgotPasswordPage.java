package com.orangehrm.tests.pages;

import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class ForgotPasswordPage extends BasePage {
    private By cancelButton = By.cssSelector(".orangehrm-forgot-password-button--cancel");
    private By resetPassword = By.cssSelector(".orangehrm-forgot-password-button--reset");
    private By errorRequiredField = By.cssSelector(".oxd-input-group__message");
    private By usernameInput0 = By.cssSelector("input[name='username']");

    @Override
    protected By onPageMarkerLoc() {
        return By.cssSelector(".orangehrm-forgot-password-title");
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "auth/requestPasswordResetCode");
    }
    @Override
    public void goToPage() {
        super.goToPage(true, false);
    }

    /*******
     * Get *
     *******/
    public String getErrorRequiredField() {
        return oranWait().For(errorRequiredField).getText().trim();
    }

    /***********
     * Actions *
     ***********/
    public void clickCancelButton() {
        click(cancelButton);
    }
    public void clickResetPassword() {
        click(resetPassword);
    }
    public void enterUsername(String username) {
        enterText(usernameInput0, username, false);
    }
}
