package com.orangehrm.tests.pages.recruitment.candidates;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class AddCandidatePage extends BasePage {
    private By addCandidateTitle = By.xpath("//h6[contains(., 'Add Candidate')]");
    private By firstNameInput = By.cssSelector(".orangehrm-firstname");
    private By middleNameInput = By.cssSelector(".orangehrm-middlename");
    private By lastNameInput = By.cssSelector(".orangehrm-lastname");
    private By emailInput = By.cssSelector(".oxd-grid-item:first-of-type input.oxd-input.oxd-input--active[placeholder='Type here']");
    private By dropdownVacancy = By.cssSelector(".oxd-select-text");
    private By saveButton = By.cssSelector("button[type=submit]");
    private By fullName = By.cssSelector(".oxd-grid-item:nth-of-type(1) .oxd-input-group div p");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "recruitment/addCandidate");
    }
    @Override
    protected By onPageMarkerLoc() {
        return addCandidateTitle;
    }

    /*******
     * Set *
     *******/
    public void setVacancy(String vacancy) {
        selectDropdownOption(dropdownVacancy, vacancy);
    }

    /*******
     * Get *
     *******/
    public String getFullName(){
        return getText(fullName);
    }

    /***********
     * Actions *
     ***********/
    public void addNewCandidate(String name, String middleName, String lastName, String vacancy, String email) {
        enterText(firstNameInput, name, false);
        enterText(middleNameInput, middleName, false);
        enterText(lastNameInput, lastName, false);
        setVacancy(vacancy);
        enterText(emailInput, email, false);
        clickSave();
    }

    public void clickSave() {
        click(saveButton);
    }
}
