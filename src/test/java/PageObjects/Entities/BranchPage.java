package PageObjects.Entities;

import POJO.Branch;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BranchPage extends UserNavBar {
    private WebDriver webdriver = null;

    public BranchPage(WebDriver webdriver) {
        super(webdriver);
        this.webdriver = webdriver;
    }

    @FindBy(how = How.XPATH, using = "//button/*[text()='Create a new Branch']")
    private WebElement createBranchButton;

    @FindBy(how = How.NAME, using = "id")
    private WebElement branchID;

    @FindBy(how = How.NAME, using = "name")
    private WebElement branchNameCreation;

    @FindBy(how = How.NAME, using = "code")
    private WebElement branchCodeCreation;

    @FindBy(how = How.NAME, using = "name")
    private WebElement branchNameEdit;

    @FindBy(how = How.NAME, using = "code")
    private WebElement branchCodeEdit;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Save']")
    private WebElement confirmSaveButton;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Cancel']")
    private WebElement confirmCancelChangesButton;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Delete']")
    private WebElement confirmDeleteButton;

    @FindBy(how = How.ID, using = "searchQuery")
    private WebElement searchBranchQuery;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Search a Branch']")
    private WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//tbody/tr")
    private List<WebElement> branchList;


    @FindBy(how = How.CLASS_NAME, using = "form-group has-error")
    private WebElement errMsgCreateBranchWindow;


    private Branch parseBranchDetails(WebElement branchRow) {
        try {
            List<WebElement> branchDetails = branchRow.findElements(By.tagName("td"));
            String id = branchDetails.get(0).getText();
            String name = branchDetails.get(1).getText();
            String code = branchDetails.get(2).getText();

            WebElement viewBranch = branchRow.findElements(By.tagName("button")).get(0);
            WebElement editBranch = branchRow.findElements(By.tagName("button")).get(1);
            WebElement deleteBranch = branchRow.findElements(By.tagName("button")).get(2);
            return new Branch(Integer.parseInt(id), name, code, viewBranch, editBranch, deleteBranch);
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public void createNewBranch(String branchName, String branchCode, boolean clickCancel) {
        createBranchButton.click();
        this.branchNameCreation.clear();
        this.branchNameCreation.sendKeys(branchName);

        this.branchCodeCreation.clear();
        this.branchCodeCreation.sendKeys(branchCode);
        if (clickCancel)
            WebDriverUtils.click(webdriver, confirmCancelChangesButton);

        else
            WebDriverUtils.click(webdriver, confirmSaveButton);

    }

    public Branch viewBranchDetails(Integer branchID) {
        Branch branchDetails = null;
        for (WebElement branch : branchList) {
            Branch currentBranch = this.parseBranchDetails(branch);
            if (currentBranch != null && currentBranch.getId() == branchID) {
                branchDetails = currentBranch;
                WebDriverUtils.click(webdriver, currentBranch.getViewButton());
                break;
            }
        }

        return branchDetails;
    }

    public Branch getBranchDetailsByName(String branchName) {
        Branch branchDetails = null;
        for (WebElement branch : branchList) {
            Branch currentBranch = this.parseBranchDetails(branch);
            if (currentBranch != null && currentBranch.getName().equals(branchName)) {
                branchDetails = currentBranch;
                break;
            }
        }

        return branchDetails;
    }

    public boolean editBranch(Branch existingBranch, String newBranchName, String newBranchCode, Boolean saveChanges) {
        boolean isEdited = Boolean.FALSE;

        for (WebElement branch : branchList) {
            Branch currentBranch = this.parseBranchDetails(branch);
            if (currentBranch != null && currentBranch.equals(existingBranch)) {
                WebDriverUtils.click(webdriver, currentBranch.getEditButton());

                branchNameEdit.sendKeys(newBranchName);
                branchNameEdit.sendKeys(newBranchCode);
//
//                new Actions(webdriver).sendKeys(branchNameCreation, newBranchName).perform();
//                new Actions(webdriver).sendKeys(branchCodeCreation, newBranchCode).perform();

                if (saveChanges) {
                    confirmSaveButton.click();
                    isEdited = Boolean.TRUE;
                } else {
                    confirmCancelChangesButton.click();
                }

            }
        }
        return isEdited;
    }

    public boolean deleteBranch(Branch branchTobeDeleted) {
        boolean isDeleted = Boolean.FALSE;
        System.out.println(branchList.size());
        for (WebElement branch : branchList) {
            System.out.println(branch.getText());
            Branch currentBranch = this.parseBranchDetails(branch);
            if (currentBranch != null && currentBranch.equals(branchTobeDeleted)) {
                System.out.println(currentBranch.getDeleteButton());
                WebDriverUtils.click(webdriver, currentBranch.getDeleteButton());
                isDeleted = Boolean.TRUE;
                break;
            }
        }
        return isDeleted;
    }

    public List<Branch> listofBranchesDisplayed() {
        List<Branch> returnBranchNames = new ArrayList<>();

        for (WebElement branch : branchList) {
            returnBranchNames.add(this.parseBranchDetails(branch));
        }
        return returnBranchNames;
    }

    public void searchBranch(String searchQuery) {
        searchBranchQuery.sendKeys(searchQuery);
        searchButton.click();
    }

    public String getErrorMsgExceedingCharLimit() {
        return errMsgCreateBranchWindow.findElement(By.className("help-block ng-scope")).getText();
    }

    public String getErrorMsgMinChar() {
        return errMsgCreateBranchWindow.findElement(By.className("help-block ng-scope")).getText();
    }

    public String getErrorMsgInvalidBranchCode() {
        return errMsgCreateBranchWindow.findElement(By.className("help-block ng-scope")).getText();
    }
}
