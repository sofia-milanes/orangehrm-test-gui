package com.orangehrm.tests.tests.admin;

import com.orangehrm.tests.OrangeHRMTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.orangehrm.tests.pages.PageFactory.userManagementPage;
import static com.orangehrm.tests.pages.PageFactory.loginPage;
import static com.orangehrm.tests.utils.ValuesGenerator.randomString;
import static org.testng.Assert.*;

public class UserManagementTests extends OrangeHRMTest {

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Given I am on the User Management page
        loginPage().doLogin("Admin", "admin123");
        userManagementPage().selectAdminSideMenuOption();
        userManagementPage().selectAdminTopMenu("User Management");
        userManagementPage().clickUsersMenu();
    }

    @Test
    public void searchSystemUsers() {
        // And I enter user's name, role. status
        userManagementPage().setUserName("Admin");
        userManagementPage().selectUserRole("Admin");
        userManagementPage().setEmployeeName(userManagementPage().getLoggedUsername());
        userManagementPage().selectStatus("Enabled");

        // When I click on Search button
        userManagementPage().clickSearchButton();

        // Then I get one result that matches
        List<String> searchNames = userManagementPage().getUsernamesOnTable();
        assertEquals(searchNames.get(0), "Admin", "Search results did not match expected");
        assertEquals(searchNames.size(), 1);
    }

    @Test
    public void addSystemUser() {
        // And I click on Add button
        userManagementPage().clickAddNewUser();

        // When I enter user's information
        userManagementPage().selectUserRole("Admin");
        userManagementPage().setEmployeeName(userManagementPage().getLoggedUsername());
        userManagementPage().selectStatus("Enabled");
        userManagementPage().setUserName(randomString());
        userManagementPage().setPassword("Admin.123");
        userManagementPage().confirmPassword("Admin.123");

        // And I click on Save button
        userManagementPage().clickSaveButton();

        // Then a success message is displayed
        assertTrue(userManagementPage().isSuccessMessageOnPage());
    }
}
