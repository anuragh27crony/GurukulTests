package PageObjects.Entities;

import POJO.Staff;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StafffPage extends UserNavBar {
    private WebDriver webdriver = null;

    public StafffPage(WebDriver webdriver) {
        super(webdriver);
        this.webdriver = webdriver;
    }

    @FindBy(how = How.XPATH, using = "//button/*[text()='Create a new Staff']")
    private WebElement createStaffButton;

    @FindBy(how = How.NAME, using = "id")
    private WebElement staffID;

    @FindBy(how = How.NAME, using = "name")
    private WebElement staffName;

    @FindBy(how = How.NAME, using = "related_branch")
    private WebElement branchDDL;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Save']")
    private WebElement confirmSaveButton;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Cancel']")
    private WebElement confirmCancelChangesButton;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Delete']")
    private WebElement confirmDeleteButton;

    @FindBy(how = How.ID, using = "searchQuery")
    private WebElement searchStaffQuery;

    @FindBy(how = How.XPATH, using = "//button/*[text()='Search a Staff']")
    private WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//tbody/tr")
    private List<WebElement> staffListDisplayed;


    public void createNewStaff(String staffName, String branchName, boolean clickCancel) {
        createStaffButton.click();
        this.staffName.clear();
        this.staffName.sendKeys(staffName);
        if (null != branchName && !branchName.isEmpty())
            new Select(branchDDL).selectByVisibleText(branchName);

        if (clickCancel)
            WebDriverUtils.click(webdriver, confirmCancelChangesButton);
        else
            WebDriverUtils.click(webdriver, confirmSaveButton);

    }

    private Staff parseStaffDetails(WebElement StaffRow) {
        try {
            List<WebElement> staffDetails = StaffRow.findElements(By.tagName("td"));
            String id = staffDetails.get(0).getText();
            String name = staffDetails.get(1).getText();
            String branchName = staffDetails.get(2).getText();

            WebElement viewButton = StaffRow.findElement(By.xpath("//button/*[text()='View']"));
            WebElement editButton = StaffRow.findElement(By.xpath("//button/*[text()='Edit']"));
            WebElement deleteButton = StaffRow.findElement(By.xpath("//button/*[text()='Delete']"));
            return new Staff(Integer.parseInt(id), name, branchName, viewButton, editButton, deleteButton);
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public Staff viewStaffByName(String staffName) {
        Staff staffDetails = null;
        for (WebElement staff : staffListDisplayed) {
            Staff currentStaff = this.parseStaffDetails(staff);
            if (currentStaff != null && currentStaff.getName() == staffName) {
                staffDetails = currentStaff;
                WebDriverUtils.click(webdriver, currentStaff.getViewButton());
                break;
            }
        }

        return staffDetails;
    }

    public Staff getStaffDetailsByName(String staffName) {
        Staff staffDetails = null;
        for (WebElement staff : staffListDisplayed) {
            Staff currentStaff = this.parseStaffDetails(staff);
            if (currentStaff != null && currentStaff.getName() == staffName) {
                staffDetails = currentStaff;
                break;
            }
        }

        return staffDetails;
    }

    public boolean editStaff(Staff existingStaff, String newStaffName, String newBranchName, Boolean saveChanges) {
        boolean isEdited = Boolean.FALSE;

        for (WebElement staff : staffListDisplayed) {
            Staff currentStaff = this.parseStaffDetails(staff);
            if (currentStaff != null && currentStaff.equals(existingStaff)) {

                WebDriverUtils.click(webdriver, currentStaff.getEditButton());
                staffName.sendKeys(newStaffName);
                if (null != newBranchName && !newBranchName.isEmpty())
                    new Select(branchDDL).selectByVisibleText(newBranchName);

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

    public boolean deleteStaff(Staff staffTobeDeleted) {
        boolean isDeleted = Boolean.FALSE;

        for (WebElement staff : staffListDisplayed) {
            Staff currentStaff = this.parseStaffDetails(staff);
            if (currentStaff != null && currentStaff.equals(staffTobeDeleted)) {
                WebDriverUtils.click(webdriver, currentStaff.getDeleteButton());
                isDeleted = Boolean.TRUE;
            }
        }
        return isDeleted;
    }

    public List<Staff> listofStaffDisplayed() {
        List<Staff> staffList = new ArrayList<>();

        for (WebElement staff : staffListDisplayed) {
            staffList.add(this.parseStaffDetails(staff));
        }
        return staffList;
    }

    public void searchStaff(String searchQuery) {
        searchStaffQuery.sendKeys(searchQuery);
        searchButton.click();
    }
}
