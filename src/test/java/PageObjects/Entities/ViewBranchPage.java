package PageObjects.Entities;

import POJO.Branch;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ViewBranchPage extends UserNavBar {
    private WebDriver webdriver = null;

    public ViewBranchPage(WebDriver webdriver) {
        super(webdriver);
        this.webdriver = webdriver;
    }


    @FindBy(how = How.XPATH, using = "//button/*[text()='Back']")
    private WebElement backButton;

    @FindBy(how = How.TAG_NAME, using = "input")
    private List<WebElement> branchDetails;

    @FindBy(how = How.TAG_NAME, using = "h2")
    private WebElement branchID;


    public Branch getBranchDetails() {
        Branch branchData = null;
        if (WebDriverUtils.isElementVisible(webdriver, branchID)) {
            System.out.println("Inside If condition");
            String id = branchID.getText().split(" ")[1];
            System.out.println(branchID.getText());
            branchData = new Branch(Integer.parseInt(id), branchDetails.get(0).getText(), branchDetails.get(1).getText());
//            if (WebDriverUtils.isElementVisible(webdriver, branchDetails.get(0)) && WebDriverUtils.isElementVisible(webdriver, branchDetails.get(1)))
//                System.out.println("Inside second if");
//                branchData = new Branch(Integer.parseInt(id), branchDetails.get(0).getText(), branchDetails.get(1).getText());
        }
        WebDriverUtils.click(webdriver, backButton);
        return branchData;
    }

}
