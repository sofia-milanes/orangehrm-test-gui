package com.orangehrm.tests.tests.pim;

import com.orangehrm.tests.OrangeHRMTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.orangehrm.tests.pages.PageFactory.*;
import static com.orangehrm.tests.utils.ValuesGenerator.randomString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmployeeListTests extends OrangeHRMTest {

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Given I am on the Employee List page
        loginPage().doLogin("Admin", "admin123");
        employeeListPage().selectPimSideMenuOption();
        employeeListPage().selectFromPimTopMenu("Employee List");
    }

    @Test
    public void searchByEmployeeName() {
        // And I enter employee's name
        employeeListPage().setEmployeeName(employeeListPage().getLoggedUsername());

        // When I click on Search button
        employeeListPage().clickSearchButton();

        // Then I get one result that matches
        List<String> searchNames = employeeListPage().getEmployeesNamesOnTable();
        String expectedFirstName = employeeListPage().getLoggedUsername().split("\\s+")[0];
        assertEquals(searchNames.get(0).split("\\s+")[0], expectedFirstName, "Search results did not match expected");
        assertEquals(searchNames.size(), 1);
    }

    @Test
    public void addNewEmployee() {
        // And I click on Add button
        employeeListPage().clickAddEmployeeListButton();

        // And I'm redirected to 'Add Employee' page
        assertTrue(addEmployeePage().isOnPage());

        // When I enter user's information
        String firstName = randomString();
        String lastName = randomString();
        addEmployeePage().addEmployee(firstName, lastName);

        // Then a success message is displayed
        assertTrue(addEmployeePage().isSuccessMessageOnPage());
    }

    @Test
    public void editEmployee() {
        // And I add an employee
        String firstName = randomString();
        String middleName = randomString();
        addEmployeePage().setRequiredFieldsAddEmployee(firstName,middleName,randomString());
        assertTrue(addEmployeePage().isSuccessMessageOnPage());

        // And I search the employee in employee's table
        employeeListPage().goToPage();
        employeeListPage().setEmployeeName(firstName);
        employeeListPage().clickSearchButton();

        // When I click on Edit action button
        employeeListPage().clickActionEditButton();

        // And modify the employee first name
        String editedFirstName = randomString();
        employeeListPage().setFirstNamePersonalDetails(editedFirstName);
        employeeListPage().savePersonalDetails();

        // And search by employee first name
        employeeListPage().goToPage();
        employeeListPage().setEmployeeName(editedFirstName);
        employeeListPage().clickSearchButton();

        // Then the edited first name is displayed in results founds
        List<String> searchNames = employeeListPage().getEmployeesNamesOnTable();
        assertEquals(searchNames.get(0), editedFirstName + " " + middleName, "Search results did not match expected");
    }

    @Test
    public void deleteEmployee() {
        // And I add an employee
        String firstName = randomString();
        addEmployeePage().setRequiredFieldsAddEmployee(firstName,randomString(),randomString());
        assertTrue(addEmployeePage().isSuccessMessageOnPage());

        // And I search the employee in employee's table
        employeeListPage().goToPage();
        employeeListPage().setEmployeeName(firstName);
        employeeListPage().clickSearchButton();

        // When I click on Delete action button
        employeeListPage().clickActionDeleteButton();

        // And confirm deletion
        employeeListPage().confirmDeletion();

        // Then the removal success message is displayed
        assertTrue(addEmployeePage().isSuccessMessageOnPage());
    }
}
