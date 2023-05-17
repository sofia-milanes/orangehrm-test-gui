package com.orangehrm.tests.pages.admin;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class UserManagementPage extends BasePage {
    private By adminTitle = By.cssSelector(".oxd-topbar-header-breadcrumb-level");
    public static final By adminOption = By.cssSelector("a[href='/web/index.php/admin/viewAdminModule']");
    private By usernameInput = By.cssSelector("div.oxd-input-field-bottom-space input.oxd-input");
    private By usersMenuOption = By.cssSelector(".oxd-topbar-body-nav-tab-link");
    private By userRoleDropdown = By.xpath("(//div[@class[contains(., 'oxd-select-text-input')]])[last() -1]");
    private By userRoleOption = By.cssSelector(".oxd-select-option");
    private By employeeNameInput = By.cssSelector("input[placeholder=\"Type for hints...\"]");
    private By statusDropdown = By.xpath("(//div[@class[contains(., 'oxd-select-text-input')]])[last() -0]");
    private By userStatusOption = By.cssSelector(".oxd-select-option");
    private By searchButton = By.cssSelector("button[type=submit]");
    private By usernameColumn = By.cssSelector("div.oxd-table-body div.oxd-table-cell:nth-of-type(2)");
    private By passwordInput = By.cssSelector("div.user-password-cell input.oxd-input[type=\"password\"]");
    private By confirmPasswordInput = By.cssSelector("div.oxd-input-field-bottom-space:nth-child(1) input.oxd-input[type=\"password\"]");
    private By saveButton = By.cssSelector(".orangehrm-left-space");
    private By addUserNewButton = By.cssSelector("div.orangehrm-header-container button[type=\"button\"]");
    private By loggedUsername = By.cssSelector("p.oxd-userdropdown-name");
    private By autocompleteEmployeeName = By.cssSelector("div.oxd-autocomplete-dropdown div");
    private By successMessage = By.xpath("//p[contains(.,'Success')]");
    @Override
    protected By onPageMarkerLoc() {
        return adminTitle;
    }

    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "admin/viewSystemUsers");
    }

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }

    /*******
    * Set *
    *******/
    public void setUserName(String userName) {
        enterText(usernameInput, userName, false);
        oranWait().hardwait(2);
    }

    public void setEmployeeName(String employeeName) {
        enterText(employeeNameInput, employeeName, false);
        oranWait().hardwait(3);
        click(autocompleteEmployeeName);
    }

    public void setPassword(String password) {
        enterText(passwordInput, password, false);
    }

    public void confirmPassword(String password) {
        enterText(confirmPasswordInput, password, false);
    }

    /*******
    * Get *
    *******/

    public List<String> getUsernamesOnTable() {
        return getTexts(usernameColumn).stream()
                .filter(name -> !name.equals("Select")).collect(toList());
    }

    public String getLoggedUsername() {
        return getText(loggedUsername);
    }

    /************
    * Booleans *
    ************/

    public boolean isSuccessMessageOnPage() {
        oranWait().hardwait(3);
        return isElementVisible(successMessage);
    }

    /***********
    * Actions *
    ***********/

    public void selectAdminTopMenu(String adminOption) {
        click(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item'][contains(.,'" + adminOption + "')]"));
    }
    public void clickUsersMenu() {
        click(usersMenuOption);
    }
    public void clickAddNewUser() {
        click(addUserNewButton);
    }
    public void selectAdminSideMenuOption() {
        click(adminOption);
    }
    public void selectUserRole(String userRole) {
        click(userRoleDropdown);
        click(findByText(userRoleOption, userRole).get());
        click(By.cssSelector("body"));
    }
    public void selectStatus(String status) {
        click(statusDropdown);
        click(findByText(userStatusOption, status).get());
        click(By.cssSelector("body"));
    }
    public void clickSearchButton() {
        click(searchButton);
    }
    public void clickSaveButton() {
        click(saveButton);
    }
}
