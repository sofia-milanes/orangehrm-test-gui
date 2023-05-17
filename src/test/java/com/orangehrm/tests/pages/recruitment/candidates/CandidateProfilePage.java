package com.orangehrm.tests.pages.recruitment.candidates;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class CandidateProfilePage extends BasePage {
    private String candidateProfileUrl = TestingContext.appUrl + "recruitment/addCandidate/%d", candidateID;
    private By candidateProfileTitle = By.xpath("//h6[contains(.,\"Candidate Profile\")]");
    private By editSwitch = By.cssSelector(".oxd-switch-input");
    private By candidateFirstNameInput = By.cssSelector(".orangehrm-firstname");
    private By submitButton = By.cssSelector("button[type=submit]");
    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(candidateProfileUrl);
    }
    @Override
    protected By onPageMarkerLoc() {
        return candidateProfileTitle;
    }

    /*******
     * Set *
     *******/
    public void setFirstNameCandidateProfile(String candidateFirstName) {
        oranWait().hardwait(2);
        enterText(candidateFirstNameInput, candidateFirstName, true);
    }

    /***********
     * Actions *
     ***********/
    public void enableEditMode() {
        click(editSwitch);
    }
    public void saveCandidateProfile() {
        click(submitButton);
    }
}
