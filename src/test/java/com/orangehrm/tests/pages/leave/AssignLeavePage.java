package com.orangehrm.tests.pages.leave;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.Optional;

public class AssignLeavePage extends BasePage {
    private By assignLeaveTitle = By.cssSelector("h6.orangehrm-main-title");
    private By leaveTypeDropdown = By.xpath("//div[@class[contains(., 'oxd-select-text-input')]]");
    private By fromDateContainer = By.cssSelector("div.oxd-grid-item:first-of-type input.oxd-input");
    private By toDateContainer = By.cssSelector("div.oxd-grid-item:last-of-type input.oxd-input");
    private By assignButton = By.cssSelector("button[type='submit']");
    private By leaveConfirmationButton = By.xpath("//button[contains(.,'Ok')]");
    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "leave/assignLeave");
    }
    @Override
    protected By onPageMarkerLoc() {
        return assignLeaveTitle;
    }

    /*******
     * Set *
     *******/
    public void setEmployeeName(String employeeName) {
        selectAutocompleteOption(employeeName, false);
    }

    public void setLeaveType(String option) {
        selectDropdownOption(leaveTypeDropdown, option);
    }

    public void setFromDateTime(LocalDate d) {
        enterText(fromDateContainer, d.toString(), false);
    }

    public void setToDateTime(LocalDate d) {
        enterText(toDateContainer, d.toString(), true);
    }

    /***********
     * Actions *
     ***********/
    public void submitAssign() {
        click(assignButton);
    }

    public void confirmLeaveAssignment() {
        click(leaveConfirmationButton);
    }
}
