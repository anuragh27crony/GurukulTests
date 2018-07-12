package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends PreLoginNavBar {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.ID, using = "username")
    private WebElement loginName;

    @FindBy(how = How.ID, using = "password")
    private WebElement loginPassword;

    @FindBy(how = How.ID, using = "rememberMe")
    private WebElement loginRememberCredentials;

    @FindBy(how = How.XPATH, using = "//button[text()='Authenticate']")
    private WebElement loginButton;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Register a new account")
    private WebElement registrationPageLink;

    @FindBy(how = How.LINK_TEXT, using = "Did you forget your password?")
    private WebElement forgotPasswordPageLink;


    public void dologin(String username, String password, Boolean disableAutomaticLogin) {
        loginName.sendKeys(username);
        loginPassword.sendKeys(password);

        if (disableAutomaticLogin)
            loginRememberCredentials.click();

        loginButton.click();
    }


    public void navigateToRegistrationPage() {
        registrationPageLink.click();
    }

    public void navigateToForgotPasswordPage() {
        forgotPasswordPageLink.click();
    }


    public String getErrorMsgInvalidCredentials() {
        return "Authentication failed! Please check your credentials and try again.";
    }

}
