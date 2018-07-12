package PageObjects.Entities;


import POJO.Staff;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ViewStaffPage extends UserNavBar {
    private WebDriver webdriver = null;

    public ViewStaffPage(WebDriver webdriver) {
        super(webdriver);
        this.webdriver = webdriver;
    }


    @FindBy(how = How.XPATH, using = "//button/*[text()='Back']")
    private WebElement backButton;

    @FindBy(how = How.TAG_NAME, using = "input")
    private List<WebElement> staffDetails;

    @FindBy(how = How.TAG_NAME, using = "h2")
    private WebElement staffID;


    public Staff getStaffDetails() {
        Staff staffData = null;
        if (WebDriverUtils.isElementVisible(webdriver, staffID)) {
            System.out.println("Inside If condition");
            String id = staffID.getText().split(" ")[1];
            System.out.println(staffID.getText());
            staffData = new Staff(Integer.parseInt(id), staffDetails.get(0).getText(), staffDetails.get(1).getText());
        }
        WebDriverUtils.click(webdriver, backButton);
        return staffData;
    }

}
