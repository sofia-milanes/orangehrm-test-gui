package com.orangehrm.tests.tests.recruitment;

import com.orangehrm.tests.OrangeHRMTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.orangehrm.tests.pages.PageFactory.*;
import static com.orangehrm.tests.utils.ValuesGenerator.randomEmail;
import static com.orangehrm.tests.utils.ValuesGenerator.randomString;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CandidatesTests extends OrangeHRMTest {
    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Given I am on the Candidates page
        loginPage().doLogin("Admin", "admin123");
        candidatesPage().goToPage();
    }

    @Test
    public void addCandidateForVacancySeniorQALead() {
        // And I click on Add button
        candidatesPage().clickAddNewCandidate();

        // And I'm redirected to 'Add Candidate' page
        assertTrue(addCandidatePage().isOnPage());

        // When I enter candidate's information
        String firstName = "First_" + randomString();
        String middleName = "Middle_" + randomString();
        String lastName = "Last_" + randomString();
        addCandidatePage().addNewCandidate(firstName, middleName, lastName, "Senior QA Lead", randomEmail());

        // Then candidate's full name is displayed in page
        String expectedCandidateFullName = firstName + " " + middleName + " " + lastName;
        assertEquals(addCandidatePage().getFullName(), expectedCandidateFullName);
    }

    @Test
    public void editCandidateFirstName() {
        // And I add an employee
        String firstName = "First_" + randomString();
        String middleName = "Middle_" + randomString();
        String lastName = "Last_" + randomString();
        candidatesPage().clickAddNewCandidate();
        addCandidatePage().addNewCandidate(firstName, middleName, lastName, "Senior QA Lead", randomEmail());
        assertTrue(addCandidatePage().isSuccessMessageOnPage());

        // And I search the candidate in candidate's table
        candidatesPage().goToPage();
        candidatesPage().searchByCandidateName(firstName);

        // And I click on View action button
        candidatesPage().clickActionViewButton();

        // And I am redirected to the candidate profile page, enable Edit mode
        assertTrue(candidateProfilePage().isOnPage());
        candidateProfilePage().enableEditMode();

        // When I modify the candidate first name
        String editedCandidateFirstName = randomString();
        candidateProfilePage().setFirstNameCandidateProfile(editedCandidateFirstName);
        candidateProfilePage().saveCandidateProfile();

        // Then I get a success saved message
        assertTrue(candidateProfilePage().isSuccessMessageOnPage());

        // And correct name is displayed in candidates table
        candidatesPage().goToPage();
        candidatesPage().searchByCandidateName(editedCandidateFirstName);
        List<String> searchNames = candidatesPage().getCandidatesNamesOnTable();
        Assert.assertEquals(searchNames.get(0), editedCandidateFirstName + " " + middleName + " " + lastName, "Search results did not match expected");
    }

    @Test
    public void deleteCandidate() {
        // And I add an employee
        String firstName = randomString();
        candidatesPage().clickAddNewCandidate();
        addCandidatePage().addNewCandidate(firstName, randomString(), randomString(), "Senior QA Lead", randomEmail());
        assertTrue(addCandidatePage().isSuccessMessageOnPage());

        // And I search the candidate in candidate's table
        candidatesPage().goToPage();
        candidatesPage().searchByCandidateName(firstName);

        // And I click on Delete action button
        candidatesPage().clickActionDeleteButton();

        // And I confirm deletion
        candidatesPage().confirmDeletion();

        // Then I get a success saved message
        assertTrue(candidatesPage().isSuccessMessageOnPage());

        // And name is not displayed in candidates table
        candidatesPage().goToPage();
        assertEquals(candidatesPage().getCandidateNameFromAutocomplete(firstName), "No Records Found");
    }

    @Test
    public void searchCandidate() {
        // And I add an employee
        String firstName = "First_" + randomString();
        String middleName = "Middle_" + randomString();
        String lastName = "Last_" + randomString();
        candidatesPage().clickAddNewCandidate();
        addCandidatePage().addNewCandidate(firstName, middleName, lastName, "Senior QA Lead", randomEmail());
        assertTrue(addCandidatePage().isSuccessMessageOnPage());

        // When I search the candidate name in candidate's table
        candidatesPage().goToPage();
        candidatesPage().searchByCandidateName(firstName);

        // Then correct name is displayed in candidates table
        List<String> searchNames = candidatesPage().getCandidatesNamesOnTable();
        String expectedCandidateFullName = firstName + " " + middleName + " " + lastName;
        assertEquals(searchNames.get(0), expectedCandidateFullName);
    }
}
