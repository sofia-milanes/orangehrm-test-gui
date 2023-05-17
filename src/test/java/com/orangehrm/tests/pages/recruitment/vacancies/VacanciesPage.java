package com.orangehrm.tests.pages.recruitment.vacancies;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class VacanciesPage extends BasePage {
    private By vacanciesPageTitle = By.xpath("//h5[contains(., 'Vacancies')]");
    private By addVacancyButton = By.cssSelector("i.oxd-icon.bi-plus.oxd-button-icon");
    private By searchVacancyButton = By.cssSelector("button[type=submit]");
    private By actionEdit = By.cssSelector(".bi-pencil-fill");
    private By actionDelete = By.cssSelector(".bi-trash");
    private By vacancyColumn = By.cssSelector("div.oxd-table-body div.oxd-table-cell:nth-of-type(2)");
    private By confirmDelete = By.cssSelector(".oxd-button--label-danger");
    private By vacancyDropdown = By.cssSelector(".oxd-grid-item:nth-of-type(2) .oxd-select-text");
    private By vacancyDropdownOptions = By.cssSelector(".oxd-select-option");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "recruitment/viewJobVacancy");
    }
    @Override
    protected By onPageMarkerLoc() {
        return vacanciesPageTitle;
    }

    /*******
     * Set *
     *******/
    public void setVacancyName(String vacancyName) {
        selectDropdownOption(vacancyDropdown, vacancyName);
    }

    /*******
     * Get *
     *******/
    public List<String> getVacancyNamesOnTable() {
        return getTexts(vacancyColumn).stream()
                .filter(name -> !name.equals("Select")).collect(toList());
    }

    public List<String> getAvailableVacanciesForSelection() {
        click(vacancyDropdown);
        return getTexts(vacancyDropdownOptions).stream()
                .filter(name -> !name.equals("Select")).collect(toList());
    }

    /***********
     * Actions *
     ***********/
    public void clickAddNewVacancy() {
        click(addVacancyButton);
        oranWait().hardwait(3);
    }

    public void searchByVacancyName(String vacancyName) {
        setVacancyName(vacancyName);
        click(searchVacancyButton);
    }

    public void clickActionEditButton() {
        click(actionEdit);
        oranWait().hardwait(3);
    }

    public void clickActionDeleteButton() {
        click(actionDelete);
        oranWait().hardwait(3);
    }

    public void confirmDeletion() {
        click(confirmDelete);
    }
}
