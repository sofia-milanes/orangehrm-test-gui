package com.orangehrm.tests.pages.pim;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class AddEmployeePage extends BasePage {
    private By addEmployeeTitle = By.cssSelector("h6.orangehrm-main-title");
    private By firstNameInput = By.cssSelector(".orangehrm-firstname");
    private By middleNameInput = By.cssSelector(".orangehrm-middlename");
    private By lastNameInput = By.cssSelector(".orangehrm-lastname");
    private By saveEmployeeButton = By.cssSelector(".oxd-form-actions button[type='submit']");
    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "pim/addEmployee");
    }
    @Override
    protected By onPageMarkerLoc() {
        return addEmployeeTitle;
    }

    /*******
     * Set *
     *******/

    public void setFirstName(String employeeName) {
        enterText(firstNameInput, employeeName, false);
    }

    public void setMiddleName(String middleName) {
        enterText(middleNameInput, middleName, false);
    }

    public void setLastName(String lastName) {
        enterText(lastNameInput, lastName, false);
    }

    public void setRequiredFieldsAddEmployee(String firstName, String middleName, String lastName) {
        new AddEmployeePage().goToPage();
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        click(saveEmployeeButton);
    }

    /***********
     * Actions *
     ***********/
    public void addEmployee(String firstName, String lastName) {
        enterText(firstNameInput, firstName, false);
        enterText(lastNameInput, lastName, false);
        click(saveEmployeeButton);
    }

}
