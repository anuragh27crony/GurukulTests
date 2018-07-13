package stepdefs;

import POJO.Branch;
import PageObjects.Entities.ViewBranchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

public class BranchStepsImpl extends BaseStepsImpl {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private String errMsgMaxCharLimit = "This field cannot be longer than 20 characters.";
    private String errMsgMinChars = "This field should follow pattern ^[a-zA-Z\\s]*$.";
    private String errMsgInvalidBranchCode = "This field should follow pattern ^[A-Z0-9]*$.";
    private Branch expectedBranchData = null;
    private List<Branch> expectedBranchList = null;


    @Given("^User navigates to Branch Page$")
    public void userNavigatesToBranchPage() {
        userHomepage.navigateToBranchPage();
    }

    @When("^User creates a New Branch$")
    public void userCreatesANewBranch() {
        String validBranchName = "COMPUTERSCIENCE";
        String validBranchCode = "ENG001";
        expectedBranchData = new Branch(0, validBranchName, validBranchCode);
        branchPage.createNewBranch(validBranchName, validBranchCode, Boolean.FALSE);
    }

    @When("^User creates a New Branch with branch name exceeding char limit$")
    public void userCreatesANewBranchWithBranchNameExceedingCharLimit() {
        String branchNameExceedingChars = "LOREMIPSUMCLASSATFACILISISAENEANPOSUEREQUISQUELOBORTIS";
        String validBranchCode = "ENG002";
        expectedBranchData = new Branch(0, branchNameExceedingChars, validBranchCode);
        branchPage.createNewBranch(branchNameExceedingChars, validBranchCode, Boolean.TRUE);
    }

    @When("^User creates a New Branch with branch name less than min char limit$")
    public void userCreatesANewBranchWithBranchNameLessThanMinCharLimit() {
        String branchNameMinChars = "SQUEL";
        String validBranchCode = "ENG003";
        expectedBranchData = new Branch(0, branchNameMinChars, validBranchCode);
        branchPage.createNewBranch(branchNameMinChars, validBranchCode, Boolean.TRUE);
    }

    @When("^User creates a New Branch with invalid branch code$")
    public void userCreatesANewBranchWithInvalidBranchCode() {
        String validBranchName = "SQUELERTERT";
        String invalidBranchCode = "eng004";
        expectedBranchData = new Branch(0, validBranchName, invalidBranchCode);
        branchPage.createNewBranch(validBranchName, invalidBranchCode, Boolean.TRUE);
    }


    @When("^User creates a New Branch with invalid branch Name$")
    public void userCreatesANewBranchWithInvalidBranchName() {
        String validBranchName = "!@!#!@$!!@%!@%";
        String invalidBranchCode = "ENG005";
        expectedBranchData = new Branch(0, validBranchName, invalidBranchCode);
        branchPage.createNewBranch(validBranchName, invalidBranchCode, Boolean.TRUE);
    }

    @When("^User views a Branch details$")
    public void userViewsABranchDetails() {
        expectedBranchData = branchPage.listofBranchesDisplayed().get(0);
        branchPage.viewBranchDetails(expectedBranchData.getId());
    }

    @When("^User edits an existing Branch with invalid details$")
    public void userEditsAnExistingBranchWithInvalidDetails() {

        expectedBranchData = branchPage.listofBranchesDisplayed().get(1);
        String newInvalidBranchName = "CIVIL@$#$@#%@#%@#%";
        String newBranchCode = "ENG006";

        branchPage.editBranch(expectedBranchData, newInvalidBranchName, newBranchCode, Boolean.TRUE);
    }

    @When("^User edits an existing Branch with valid details$")
    public void userEditsAnExistingBranchWithValidDetails() {
        expectedBranchData = branchPage.listofBranchesDisplayed().get(1);

        Branch existingBranch = expectedBranchData;

        String validBranchName = "CHEMICALENGINEERING";
        String validBranchCode = existingBranch.getCode();

        branchPage.editBranch(existingBranch, validBranchName, validBranchCode, Boolean.TRUE);
    }

    @When("^User deletes an existing Branch with no staff assigned$")
    public void userDeletesAnExistingBranchWithNoStaffAssigned() {
        expectedBranchData = branchPage.listofBranchesDisplayed().get(2);
        branchPage.deleteBranch(expectedBranchData);
        expectedBranchData = null;
    }

    @When("^User deletes an existing Branch with staff assigned$")
    public void userDeletesAnExistingBranchWithStaffAssigned() {
        String validStaffName = "RegularStaffName";
        expectedBranchData = branchPage.listofBranchesDisplayed().get(3);

        userHomepage.navigateToStaffPage();
        staffPage.createNewStaff(validStaffName, expectedBranchData.getName(), Boolean.FALSE);
        userHomepage.navigateToBranchPage();

        branchPage.deleteBranch(expectedBranchData);
    }

    @When("^User searches for existing Branch$")
    public void userSearchesForExistingBranch() {
        expectedBranchList = branchPage.listofBranchesDisplayed();

        String existingBranchName = "ENG*";
        branchPage.searchBranch(existingBranchName);

    }

    @When("^User searches for Branch using wildcard$")
    public void userSearchesForBranchUsingWildcard() {
        String wildcard = "*";
        branchPage.searchBranch(wildcard);
        expectedBranchList = branchPage.listofBranchesDisplayed();
    }

    @Then("^New branch should be created successfully$")
    public void newBranchShouldBeCreatedSuccessfully() {
        Branch actualBranchData = branchPage.getBranchDetailsByName(expectedBranchData.getName());
        assertThat(expectedBranchData, equalTo(actualBranchData));

    }

    @Then("^Error message for exceeding charlimit should be displayed$")
    public void errorMessageForExceedingCharlimitShouldBeDisplayed() {
        assertThat(errMsgMaxCharLimit, equalTo(branchPage.getErrorMsgExceedingCharLimit()));
    }

    @Then("^New branch should not be created$")
    public void newBranchShouldNotBeCreated() {
        thrown.expect(NoSuchElementException.class);
    }


    @Then("^Error message for minimum char limit should be displayed$")
    public void errorMessageForMinimumCharLimitShouldBeDisplayed() {
        assertThat(errMsgMinChars, equalTo(branchPage.getErrorMsgMinChar()));
    }


    @Then("^Error message for invalid branch code should be displayed$")
    public void errorMessageForInvalidBranchCodeShouldBeDisplayed() {
        assertThat(errMsgInvalidBranchCode, equalTo(branchPage.getErrorMsgInvalidBranchCode()));
    }


    @Then("^branch details should be displayed correctly$")
    public void branchDetailsShouldBeDisplayedCorrectly() {
        ViewBranchPage viewBranchPage = new ViewBranchPage(webDriver);
        Branch actualBranchData = viewBranchPage.getBranchDetails();

        assertThat(expectedBranchData, equalTo(actualBranchData));
    }


    @Then("^branch details should be updated successfully$")
    public void branchDetailsShouldBeUpdatedSuccessfully() {
        Branch actualBranchData = branchPage.getBranchDetailsByName(expectedBranchData.getName());
        assertThat(expectedBranchData, equalTo(actualBranchData));

    }

    @Then("^branch details should not be updated successfully$")
    public void branchDetailsShouldNotBeUpdatedSuccessfully() {
        Branch actualBranchData = branchPage.getBranchDetailsByName(expectedBranchData.getName());
        assertThat(expectedBranchData, equalTo(actualBranchData));
    }


    @Then("^branch details should be deleted successfully$")
    public void branchDetailsShouldBeDeletedSuccessfully() {
        Branch actualBranchData = branchPage.getBranchDetailsByName(expectedBranchData.getName());
        assertThat(actualBranchData, is(nullValue()));
    }


    @Then("^branch details should not be deleted$")
    public void branchDetailsShouldNotBeDeleted() {
        Branch actualBranchData = branchPage.getBranchDetailsByName(expectedBranchData.getName());
        assertThat(expectedBranchData, equalTo(actualBranchData));
    }

    @Then("^All branches should be displayed$")
    public void allBranchesShouldBeDisplayed() {
        List<Branch> actualBranchesList = branchPage.listofBranchesDisplayed();
        assertThat(expectedBranchList, containsInAnyOrder(actualBranchesList));
    }

    @Then("^Matching Search results should be displayed correctly$")
    public void matchingSearchResultsShouldBeDisplayedCorrectly() {
        List<Branch> actualBranchesList = branchPage.listofBranchesDisplayed();
        assertThat(expectedBranchList, containsInAnyOrder(actualBranchesList));
    }
}
