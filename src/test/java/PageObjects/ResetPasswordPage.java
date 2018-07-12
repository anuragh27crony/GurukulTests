package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResetPasswordPage extends PreLoginNavBar {
    public ResetPasswordPage(WebDriver webdriver) {
        super(webdriver);
    }

    @FindBy(how = How.NAME, name = "email")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, xpath = "//button[text()='Reset Password']")
    private WebElement resetPasswordButton;


    public void resetPassword(String emailID) {
        emailInput.sendKeys(emailID);
        resetPasswordButton.click();
    }


}
