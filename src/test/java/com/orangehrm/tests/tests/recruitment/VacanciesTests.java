package com.orangehrm.tests.tests.recruitment;

import com.orangehrm.tests.OrangeHRMTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.orangehrm.tests.pages.PageFactory.*;
import static com.orangehrm.tests.pages.PageFactory.vacanciesPage;
import static com.orangehrm.tests.utils.ValuesGenerator.randomString;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class VacanciesTests extends OrangeHRMTest {

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Given I am on the Vacancies page
        loginPage().doLogin("Admin", "admin123");
    }

    @Test
    public void addVacancySeniorQA() {
        // And I have created a job tile
        String jobTitle = "Senior QA - " + randomString();
        addNewJobTitle(jobTitle);

        // And I click on Add button
        vacanciesPage().goToPage();
        vacanciesPage().clickAddNewVacancy();

        // And I'm redirected to 'Add Vacancy' page
        assertTrue(addVacancyPage().isOnPage());

        // When I enter vacancy information
        addVacancyPage().addNewVacancy(randomString(), jobTitle, randomString(), vacanciesPage().getLoggedUsername(), 2);

        // Then I am redirected to edit vacancy page
        assertTrue(editVacancyPage().isOnPage());
    }

    @Test
    public void editVacancyName() {
        // And I add a job title along with new vacancy
        String jobTitle = "Software engineer - " + randomString();
        addNewJobTitle(jobTitle);
        vacanciesPage().goToPage();
        vacanciesPage().clickAddNewVacancy();
        String vacancyName = "SD engineer " + randomString();
        addVacancyPage().addNewVacancy(vacancyName, jobTitle, randomString(), vacanciesPage().getLoggedUsername(), 2);

        // And I search the candidate in candidate's table
        vacanciesPage().goToPage();
        vacanciesPage().searchByVacancyName(vacancyName);

        // And I click on Edit action button
        vacanciesPage().clickActionEditButton();

        // When I modify the vacancy name and save changes
        String editedVacancyName = "Edit vacancy - " + randomString();
        editVacancyPage().setVacancyName(editedVacancyName);
        editVacancyPage().saveJobVacancy();

        // And I search by vacancy name
        vacanciesPage().goToPage();
        vacanciesPage().searchByVacancyName(editedVacancyName);

        // Then the edited vacancy name is displayed in results founds
        List<String> searchVacancyNames = vacanciesPage().getVacancyNamesOnTable();
        assertEquals(searchVacancyNames.get(0), editedVacancyName, "Search results did not match expected");
    }

    @Test
    public void deleteVacancy() {
        // And I add a job title along with new vacancy
        String jobTitle = "Software engineer - " + randomString();
        addNewJobTitle(jobTitle);
        vacanciesPage().goToPage();
        vacanciesPage().clickAddNewVacancy();
        String vacancyName = "SD engineer " + randomString();
        addVacancyPage().addNewVacancy(vacancyName, jobTitle, randomString(), vacanciesPage().getLoggedUsername(), 2);

        // And I search the vacancy name in table
        vacanciesPage().goToPage();
        vacanciesPage().searchByVacancyName(vacancyName);

        // And I click on Delete action button
        vacanciesPage().clickActionDeleteButton();

        // And I confirm deletion
        vacanciesPage().confirmDeletion();

        // Then I get a success saved message
        assertTrue(vacanciesPage().isSuccessMessageOnPage());

        // And name is not displayed in vacancies table
        vacanciesPage().reloadPage();
        assertFalse(vacanciesPage().getAvailableVacanciesForSelection().contains(vacancyName));
    }

    @Test
    public void searchVacancy() {
        // And I add a job title along with new vacancy
        String jobTitle = "Software engineer - " + randomString();
        addNewJobTitle(jobTitle);
        vacanciesPage().goToPage();
        vacanciesPage().clickAddNewVacancy();
        String vacancyName = "SD engineer " + (randomString());
        addVacancyPage().addNewVacancy(vacancyName, jobTitle, randomString(), vacanciesPage().getLoggedUsername(), 2);

        // When I search the vacancy name in table
        vacanciesPage().goToPage();
        vacanciesPage().searchByVacancyName(vacancyName);

        // Then vacancy is displayed in table
        vacanciesPage().goToPage();
        List<String> searchVacancies = vacanciesPage().getVacancyNamesOnTable();
        assertEquals(searchVacancies.get(0), vacancyName, "Search results did not match expected");
    }

    private void addNewJobTitle(String jobTitle) {
        addJobTitlePage().goToPage();
        addJobTitlePage().addJobTitle(jobTitle, randomString());
    }
}
