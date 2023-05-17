package com.orangehrm.tests.pages.admin.job;

import com.orangehrm.tests.pages.BasePage;
import com.orangehrm.tests.utils.TestingContext;
import org.openqa.selenium.By;

import java.util.Optional;

public class AddJobTitlePage extends BasePage {
    private By addJobTitlePageTitle = By.xpath("//h6[contains(., 'Add Job Title')]");
    private By jobTitleInput = By.cssSelector("div.oxd-form-row .oxd-input");
    private By jobDescriptionInput = By.cssSelector(".oxd-textarea[placeholder='Type description here']");
    private By saveButton = By.cssSelector("button[type=submit]");

    @Override
    protected Optional<String> onPageUrl() {
        return Optional.empty();
    }
    @Override
    protected Optional<String> pageUrl() {
        return Optional.of(TestingContext.appUrl + "admin/saveJobTitle");
    }
    @Override
    protected By onPageMarkerLoc() {
        return addJobTitlePageTitle;
    }

    public void addJobTitle(String jobTitle, String jobDescription) {
        enterText(jobTitleInput, jobTitle, false);
        enterText(jobDescriptionInput, jobDescription, false);
        clickSave();
    }

    public void clickSave() {
        click(saveButton);
    }

}
