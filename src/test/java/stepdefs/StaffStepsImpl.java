package stepdefs;

import POJO.Staff;
import PageObjects.Entities.BranchPage;
import PageObjects.Entities.StafffPage;
import PageObjects.Entities.ViewBranchPage;
import PageObjects.Entities.ViewStaffPage;
import PageObjects.LoginPage;
import PageObjects.PreLoginNavBar;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class StaffStepsImpl {
    private WebDriver webDriver = null;
    private UserNavBar userHomepage = null;
    private PreLoginNavBar preLoginNavBar = null;
    private LoginPage loginPage = null;
    private BranchPage branchPage = null;
    private ViewBranchPage viewBranchPage = null;
    private StafffPage staffPage = null;
    private static String appHomeURL = "http://localhost:8080";

    private String validBranchName = "ENG004";

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    private Staff expectedStaffData = null;
    private List<Staff> expectedStaffList = null;

    private static String validUsername = "admin";
    private static String validPassword = "admin";

    @Before
    public void beforeScenario() {
        this.webDriver = WebDriverUtils.initializeDriver("firefox");
        this.webDriver.get(appHomeURL);

        this.userHomepage = new UserNavBar(webDriver);
        this.preLoginNavBar = new PreLoginNavBar(webDriver);
        this.loginPage = new LoginPage(webDriver);
        this.branchPage = new BranchPage(webDriver);
        this.viewBranchPage = new ViewBranchPage(webDriver);
        this.staffPage = new StafffPage(webDriver);
    }

    @After
    public void afterScenario() {
        this.webDriver.close();
    }

    @Given("^User Logins successfully$")
    public void userLoginSuccessfully() {
        preLoginNavBar.navigateToLoginPage();
        loginPage.dologin(validUsername, validPassword, Boolean.TRUE);
    }

    @Given("^User navigates to Staff Page$")
    public void userNavigatesToStaffPage() {
        userHomepage.navigateToStaffPage();
    }

    @When("^User creates a New Staff$")
    public void userCreatesANewStaff() {
        String validStaffName = "AnotherStaffMemberOne";
        expectedStaffData = new Staff(0, validStaffName, validBranchName);
        staffPage.createNewStaff(validStaffName, validBranchName, Boolean.FALSE);
    }


    @When("^User creates a New Staff with Staff name exceeding char limit$")
    public void userCreatesANewStaffWithStaffNameExceedingCharLimit() throws Throwable {
        String staffNameExceedingCharLimit = "TEMPORINCIDIDUNTUTLABOREETDOLOREMAGNAALIQUAUTENIMADMINIMVENIAM";
        expectedStaffData = new Staff(0, staffNameExceedingCharLimit, validBranchName);
        staffPage.createNewStaff(staffNameExceedingCharLimit, validBranchName, Boolean.TRUE);
    }


    @When("^User creates a New Staff with Staff name less than min char limit$")
    public void userCreatesANewStaffWithStaffNameLessThanMinCharLimit() {
        String staffNameMinChars = "TDOL";
        expectedStaffData = new Staff(0, staffNameMinChars, validBranchName);
        staffPage.createNewStaff(staffNameMinChars, validBranchName, Boolean.TRUE);
    }

    @When("^User creates a New Staff with Empty Branch$")
    public void userCreatesANewStaffWithEmptyBranch() {
        String validStaffName = "AnotherStaffMemberTwo";
        String emptyBranchName = "";
        expectedStaffData = new Staff(0, validStaffName, emptyBranchName);
        staffPage.createNewStaff(validStaffName, emptyBranchName, Boolean.FALSE);
    }

    @When("^User views a Staff details$")
    public void userViewsAStaffDetails() {
        String validStaffName = "AnotherStaffMemberOne";
        expectedStaffData = new Staff(0, validStaffName, validBranchName);
        staffPage.viewStaffByName(validStaffName);
    }


    @When("^User edits an existing Staff with valid details$")
    public void userEditsAnExistingStaffWithValidDetails() {
        expectedStaffData = staffPage.listofStaffDisplayed().get(1);
        Staff existingStaff = expectedStaffData;

        String validStaffName = "AnotherEditedStaffMemberTwo";
        String validBranchName = existingStaff.getBranchName();

        staffPage.editStaff(existingStaff, validStaffName, validBranchName, Boolean.FALSE);
    }


    @When("^User edits an existing Staff with invalid details$")
    public void userEditsAnExistingStaffWithInvalidDetails() {
        expectedStaffData = staffPage.listofStaffDisplayed().get(0);
        Staff existingStaff = expectedStaffData;

        String invalidStaffName = "AnotherEditedStaffMember2";
        String validBranchName = existingStaff.getBranchName();

        staffPage.editStaff(existingStaff, invalidStaffName, validBranchName, Boolean.FALSE);
    }


    @When("^User deletes an existing Staff$")
    public void userDeletesAnExistingStaff() {
        expectedStaffData = staffPage.listofStaffDisplayed().get(2);

        staffPage.deleteStaff(expectedStaffData);
    }


    @When("^User searches for existing Staff$")
    public void userSearchesForExistingStaff() {
        expectedStaffList = staffPage.listofStaffDisplayed();

        String searchQuery = "Another*";
        staffPage.searchStaff(searchQuery);

    }

    @When("^User searches for Staff using wildcard$")
    public void userSearchesForStaffUsingWildcard() {
        expectedStaffList = staffPage.listofStaffDisplayed();

        String searchQuery = "*";
        staffPage.searchStaff(searchQuery);

    }

    @Then("^New staff should be created successfully$")
    public void newStaffShouldBeCreatedSuccessfully() {
        Staff actualStaffData = staffPage.getStaffDetailsByName(expectedStaffData.getName());
        assertThat(expectedStaffData, equalTo(actualStaffData));
    }

    @Then("^New Staff should not be created$")
    public void newStaffShouldNotBeCreated() {
        Staff actualStaffData = staffPage.getStaffDetailsByName(expectedStaffData.getName());
        assertThat(actualStaffData, is(nullValue()));
    }

    @Then("^staff details should be displayed correctly$")
    public void staffDetailsShouldBeDisplayedCorrectly() {
        ViewStaffPage viewStaffPage = new ViewStaffPage(webDriver);
        Staff actualStaffData = viewStaffPage.getStaffDetails();
        assertThat(expectedStaffData, equalTo(actualStaffData));
    }

    @Then("^staff details should be updated successfully$")
    public void staffDetailsShouldBeUpdatedSuccessfully() {
        Staff actualStaffData = staffPage.getStaffDetailsByName(expectedStaffData.getName());
        assertThat(expectedStaffData, equalTo(actualStaffData));
    }

    @Then("^staff details should not be updated successfully$")
    public void staffDetailsShouldNotBeUpdatedSuccessfully() {
        Staff actualStaffData = staffPage.getStaffDetailsByName(expectedStaffData.getName());
        assertThat(expectedStaffData, equalTo(actualStaffData));
    }

    @Then("^staff details should be deleted successfully$")
    public void staffDetailsShouldBeDeletedSuccessfully() {
        Staff actualStaffData = staffPage.getStaffDetailsByName(expectedStaffData.getName());
        assertThat(expectedStaffData, is(nullValue()));
    }

    @Then("^All staff data should be displayed$")
    public void allStaffDataShouldBeDisplayed() {
        List<Staff> actualStaffList = staffPage.listofStaffDisplayed();
        assertThat(expectedStaffList, containsInAnyOrder(actualStaffList));
    }

    @Then("^Matching Staff Search results should be displayed correctly$")
    public void matchingSearchResultsShouldBeDisplayedCorrectly() {
        List<Staff> actualStaffList = staffPage.listofStaffDisplayed();
        assertThat(expectedStaffList, containsInAnyOrder(actualStaffList));
    }

}
