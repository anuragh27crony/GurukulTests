package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage extends PreLoginNavBar {
    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.NAME, using = "login")
    private WebElement loginName;


    @FindBy(how = How.NAME, using = "email")
    private WebElement emailid;

    @FindBy(how = How.NAME, using = "password")
    private WebElement loginPassword;

    @FindBy(how = How.NAME, using = "confirmPassword")
    private WebElement confirmLoginPassword;

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement registerButton;

    @FindBy(how = How.TAG_NAME, using = "a")
    private WebElement loginPageLink;


    public void doRegistration(String username, String emailId, String password, String confirmPassword) {
        loginName.sendKeys(username);
        emailid.sendKeys(emailId);
        loginPassword.sendKeys(password);
        confirmLoginPassword.sendKeys(confirmPassword);
        registerButton.click();
    }

    public void navigateToLoginPage() {
        loginPageLink.click();
    }
}
