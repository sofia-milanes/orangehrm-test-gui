package com.orangehrm.tests.tests.leave;

import com.orangehrm.tests.OrangeHRMTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import static com.orangehrm.tests.pages.PageFactory.assignLeavePage;
import static com.orangehrm.tests.pages.PageFactory.loginPage;

public class AssignLeaveTests extends OrangeHRMTest {

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Given I am on the Assign Leave page
        loginPage().doLogin("Admin", "admin123");
        assignLeavePage().goToPage();
    }

    @Test
    public void assignPersonalLeaveToUser() {
        // When I fill all required fields
        assignLeavePage().setEmployeeName(assignLeavePage().getLoggedUsername());
        assignLeavePage().setLeaveType("CAN - Personal");
        assignLeavePage().setFromDateTime(LocalDate.now());
        assignLeavePage().setToDateTime(LocalDate.of(2023,12,01));
        assignLeavePage().submitAssign();

        // And I confirm leave Assignment
        assignLeavePage().confirmLeaveAssignment();

        // Then a success message is displayed
        assignLeavePage().isSuccessMessageOnPage();
    }

}
