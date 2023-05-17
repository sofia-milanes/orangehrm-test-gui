package com.orangehrm.tests.pages;

import com.orangehrm.tests.pages.admin.job.AddJobTitlePage;
import com.orangehrm.tests.pages.admin.UserManagementPage;
import com.orangehrm.tests.pages.leave.AssignLeavePage;
import com.orangehrm.tests.pages.pim.AddEmployeePage;
import com.orangehrm.tests.pages.pim.EmployeeListPage;
import com.orangehrm.tests.pages.recruitment.candidates.AddCandidatePage;
import com.orangehrm.tests.pages.recruitment.candidates.CandidateProfilePage;
import com.orangehrm.tests.pages.recruitment.candidates.CandidatesPage;
import com.orangehrm.tests.pages.recruitment.vacancies.AddVacancyPage;
import com.orangehrm.tests.pages.recruitment.vacancies.EditVacancyPage;
import com.orangehrm.tests.pages.recruitment.vacancies.VacanciesPage;

public class PageFactory {

    private static LoginPage _loginPage;
    private static ForgotPasswordPage _forgotPasswordPage;
    private static UserManagementPage _userManagementPage;
    private static EmployeeListPage _employeeListPage;
    private static AddEmployeePage _addEmployeePage;
    private static AssignLeavePage _assignLeavePage;
    private static CandidatesPage _candidatesPage;
    private static AddCandidatePage _addCandidatePage;
    private static CandidateProfilePage _candidateProfilePage;
    private static VacanciesPage _vacanciesPage;
    private static AddVacancyPage _addVacancyPage;
    private static EditVacancyPage _editVacancyPage;
    private static AddJobTitlePage _addJobTitlePage;

    static {
        _loginPage = new LoginPage();
        _forgotPasswordPage = new ForgotPasswordPage();
        _userManagementPage = new UserManagementPage();
        _employeeListPage = new EmployeeListPage();
        _addEmployeePage = new AddEmployeePage();
        _assignLeavePage = new AssignLeavePage();
        _candidatesPage = new CandidatesPage();
        _addCandidatePage = new AddCandidatePage();
        _candidateProfilePage = new CandidateProfilePage();
        _vacanciesPage = new VacanciesPage();
        _addVacancyPage = new AddVacancyPage();
        _editVacancyPage = new EditVacancyPage();
        _addJobTitlePage = new AddJobTitlePage();
    }

    public static LoginPage loginPage() {
        return _loginPage;
    }
    public static ForgotPasswordPage forgotPasswordPage() { return _forgotPasswordPage; }
    public static UserManagementPage userManagementPage() { return _userManagementPage; }
    public static EmployeeListPage employeeListPage() { return _employeeListPage; }
    public static AddEmployeePage addEmployeePage() { return _addEmployeePage; }
    public static AssignLeavePage assignLeavePage() { return _assignLeavePage; }
    public static CandidatesPage candidatesPage() { return _candidatesPage; }
    public static AddCandidatePage addCandidatePage() { return _addCandidatePage; }
    public static CandidateProfilePage candidateProfilePage() { return _candidateProfilePage; }
    public static VacanciesPage vacanciesPage() { return _vacanciesPage; }
    public static AddVacancyPage addVacancyPage() { return _addVacancyPage; }
    public static EditVacancyPage editVacancyPage() { return _editVacancyPage; }
    public static AddJobTitlePage addJobTitlePage() { return _addJobTitlePage; }
}
