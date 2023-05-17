package com.orangehrm.tests.pages.recruitment.vacancies;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class AddVacancyPage extends BasePage {
    private By addVacancyTitle = By.xpath("//h6[contains(., 'Add Vacancy')]");
    private By vacancyNameInput = By.cssSelector("div.oxd-grid-3:nth-of-type(1) input.oxd-input");
    private By dropdownJobTitle = By.cssSelector(".oxd-select-text");
    private By descriptionInput = By.cssSelector(".oxd-textarea");
    private By numberOfPositionsInput = By.cssSelector("div.oxd-grid-3:nth-of-type(3) input.oxd-input");
    private By saveButton = By.cssSelector("button[type=submit]");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "recruitment/addJobVacancy");
    }
    @Override
    protected By onPageMarkerLoc() {
        return addVacancyTitle;
    }

    /*******
     * Set *
     *******/
    public void setJobTitle(String jobTitle) {
        selectDropdownOption(dropdownJobTitle, jobTitle);
    }

    public void setHiringManager(String hiringManager) {
        selectAutocompleteOption(hiringManager, false);
    }

    /***********
     * Actions *
     ***********/
    public void addNewVacancy(String vacancyName, String jobTitle, String description, String hiringManager, Integer numberOfPositions) {
        enterText(vacancyNameInput, vacancyName, false);
        setJobTitle(jobTitle);
        enterText(descriptionInput, description, false);
        setHiringManager(hiringManager);
        enterText(numberOfPositionsInput, String.valueOf(numberOfPositions), false);
        clickSave();
        oranWait().hardwait(2);
    }

    public void clickSave() {
        click(saveButton);
    }
}
