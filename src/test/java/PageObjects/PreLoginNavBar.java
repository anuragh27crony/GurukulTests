package PageObjects;

import Utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PreLoginNavBar extends BasePageObject {
    private WebDriver webdriver = null;

    public PreLoginNavBar(WebDriver webdriver) {
        super(webdriver);
        this.webdriver = webdriver;
    }


    @FindBy(how = How.LINK_TEXT, using = "Home")
    private WebElement homePageLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Account")
    private WebElement accountDropdownList;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Authenticate")
    private WebElement loginPageLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Register")
    private WebElement registrationPageLink;

    public void navigateToHomePage() {
        webdriver.get("http://localhost:8080");
    }

    public void navigateToLoginPage() {
        System.out.println("Before Login");
        WebDriverUtils.click(this.webdriver, accountDropdownList);
        loginPageLink.click();
        System.out.println("Now on to Login");
    }

    public void navigateToRegistrationPage() {
        WebDriverUtils.click(this.webdriver, accountDropdownList);
        registrationPageLink.click();
    }

}
