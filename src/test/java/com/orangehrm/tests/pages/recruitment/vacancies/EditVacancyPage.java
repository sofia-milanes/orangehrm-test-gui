package com.orangehrm.tests.pages.recruitment.vacancies;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class EditVacancyPage extends BasePage {
    private By addEditVacancyTitle = By.xpath("//h6[contains(., 'Edit Vacancy')]");
    private By vacancyNameInput = By.cssSelector("div.oxd-grid-3:nth-of-type(1) input.oxd-input");
    private By saveButton = By.cssSelector("button[type=submit]");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "recruitment/addJobVacancy/%d");
    }
    @Override
    protected By onPageMarkerLoc() {
        return addEditVacancyTitle;
    }

    /*******
     * Set *
     *******/
    public void setVacancyName(String vacancyName) {
        enterText(vacancyNameInput, vacancyName, true);
    }

    /***********
     * Actions *
     ***********/
    public void saveJobVacancy() {
        click(saveButton);
    }
}
