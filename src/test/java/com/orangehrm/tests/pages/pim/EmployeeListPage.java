package com.orangehrm.tests.pages.pim;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class EmployeeListPage extends BasePage {
    private By employeeTitle = By.cssSelector("div.oxd-table-filter-header-title");
    private By loggedUsername = By.cssSelector("p.oxd-userdropdown-name");
    private By employeeNameInput = By.cssSelector("input[placeholder=\"Type for hints...\"]");
    private By autocompleteEmployeeName = By.cssSelector("div.oxd-autocomplete-dropdown div");
    private By searchButton = By.cssSelector("button[type=submit]");
    private By firstNameColumn = By.cssSelector("div.oxd-table-body div.oxd-table-cell:nth-of-type(3)");
    private By addButton = By.cssSelector("div.orangehrm-header-container button");
    private By actionDelete = By.cssSelector(".bi-trash");
    private By actionEdit= By.cssSelector(".bi-pencil-fill");
    private By firstNamePersonalDetailsInput = By.cssSelector(".orangehrm-firstname");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }

    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "pim/viewEmployeeList");
    }

    @Override
    protected By onPageMarkerLoc() {
        return employeeTitle;
    }
    public static final By pimOption = By.cssSelector("a[href='/web/index.php/pim/viewPimModule']");

    /*******
     * Set *
     *******/
    public void setEmployeeName(String employeeName) {
        enterText(employeeNameInput, employeeName, false);
        oranWait().hardwait(3);
        click(autocompleteEmployeeName);
    }

    public void setFirstNamePersonalDetails(String firstName) {
        enterText(firstNamePersonalDetailsInput, firstName, true);
    }

    /*******
     * Get *
     *******/
    public String getLoggedUsername() {
        return getText(loggedUsername);
    }

    public List<String> getEmployeesNamesOnTable() {
        return getTexts(firstNameColumn).stream()
                .filter(name -> !name.equals("Select")).collect(toList());
    }

    /***********
     * Actions *
     ***********/
    public void selectPimSideMenuOption() {
        click(pimOption);
    }

    public void selectFromPimTopMenu(String pimOption) {
        click(By.xpath("//a[@class='oxd-topbar-body-nav-tab-item'][contains(.,'" + pimOption + "')]"));
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public void clickAddEmployeeListButton() {
        click(addButton);
        oranWait().hardwait(5);
    }

    public void clickActionDeleteButton() {
            click(actionDelete);
    }

    public void clickActionEditButton() {
        click(actionEdit);
        oranWait().hardwait(3);
    }

    public void confirmDeletion() {
        click(By.cssSelector(".oxd-button--label-danger"));
    }

    public void savePersonalDetails() {
        click(By.xpath("(//div[@class='oxd-form-actions']//button[@type='submit'])[1]"));
    }
}
