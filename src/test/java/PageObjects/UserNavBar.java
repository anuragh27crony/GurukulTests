package PageObjects;

import Utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UserNavBar extends BasePageObject {
    private WebDriver webdriver = null;

    public UserNavBar(WebDriver webdriver) {
        super(webdriver);
        this.webdriver = webdriver;
    }


    @FindBy(how = How.LINK_TEXT, using = "Home")
    private WebElement homePageLink;


    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Entities")
    private WebElement entitiesDropdownList;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Branch")
    private WebElement branchPageLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Staff")
    private WebElement staffPageLink;


    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Account")
    private WebElement accountDropdownList;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Settings")
    private WebElement settingsPageLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Password")
    private WebElement passwordPageLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Sessiongs")
    private WebElement sessionsPageLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Log out")
    private WebElement logoutLink;

    @FindBy(how = How.CLASS_NAME, using = "alert alert-success ng-scope ng-binding")
    private WebElement welcomeMessage;


    public void navigateToStaffPage() {
        WebDriverUtils.click(webdriver, entitiesDropdownList);
        staffPageLink.click();
    }

    public void navigateToBranchPage() {
        WebDriverUtils.click(webdriver, entitiesDropdownList);
        branchPageLink.click();
    }

    public void navigateToUserSettingsPage() {
        WebDriverUtils.click(webdriver, accountDropdownList);
        settingsPageLink.click();
    }

    public void navigateToChangePasswordPage() {
        WebDriverUtils.click(webdriver, accountDropdownList);
        passwordPageLink.click();
    }

    public void navigateToChangeSessopmsPage() {
        WebDriverUtils.click(webdriver, accountDropdownList);
        sessionsPageLink.click();
    }

    public void logoutApplication() {
        WebDriverUtils.click(webdriver, accountDropdownList);
        logoutLink.click();
    }

    public String getUserWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public boolean isUserNavBarAvailable() {
        return entitiesDropdownList.isDisplayed();
    }

}
