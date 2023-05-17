package com.orangehrm.tests.pages.recruitment.candidates;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CandidatesPage extends BasePage {
    private By candidatesTitle = By.cssSelector("h5.oxd-table-filter-title");
    private By addCandidateButton = By.cssSelector("i.oxd-icon.bi-plus.oxd-button-icon");
    private By submitSearchButton = By.cssSelector("button[type=submit]");
    private By viewActionButton = By.cssSelector("i.bi-eye-fill");
    private By deleteActionButton = By.cssSelector("i.bi-trash");
    private By firstNameColumn = By.cssSelector("div.oxd-table-body div.oxd-table-cell:nth-of-type(3)");
    private By confirmDelete = By.cssSelector(".oxd-button--label-danger");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "recruitment/viewCandidates");
    }
    @Override
    protected By onPageMarkerLoc() {
        return candidatesTitle;
    }

    /*******
     * Set *
     *******/
    public void setCandidateName(String candidateName) {
        selectAutocompleteOption(candidateName, false);
    }

    /*******
     * Get *
     *******/
    public List<String> getCandidatesNamesOnTable() {
        return getTexts(firstNameColumn).stream()
                .filter(name -> !name.equals("Select")).collect(toList());
    }

    public String getCandidateNameFromAutocomplete(String candidateFirstName) {
        enterText(By.cssSelector("input[placeholder=\"Type for hints...\"]"), candidateFirstName, true);
        oranWait().hardwait(3);
        return getText(By.cssSelector("div.oxd-autocomplete-dropdown div"));

    }

    /***********
     * Actions *
     ***********/
    public void clickAddNewCandidate() {
        click(addCandidateButton);
        oranWait().hardwait(3);
    }

    public void searchByCandidateName(String candidateFirstName) {
        selectAutocompleteOption(candidateFirstName, false);
        submitSearch();
    }

    public void submitSearch() {
        click(submitSearchButton);
    }

    public void clickActionViewButton() {
        click(viewActionButton);
        oranWait().hardwait(3);
    }

    public void clickActionDeleteButton() {
        click(deleteActionButton);
        oranWait().hardwait(3);
    }

    public void confirmDeletion() {
        click(confirmDelete);
    }
}
