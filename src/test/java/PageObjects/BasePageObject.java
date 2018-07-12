package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageObject {
    public BasePageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
