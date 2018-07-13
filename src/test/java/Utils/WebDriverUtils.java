package Utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WebDriverUtils {

    public static int totalWaitinSecs = 10000;
    public static int pollingTimeinMillis = 1000;


    private static Wait<WebDriver> initializeFluentDriver(WebDriver driver) {
        return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(totalWaitinSecs)).pollingEvery(Duration.ofMillis(pollingTimeinMillis));
    }

    public static boolean isElementVisible(WebDriver driver, WebElement webelement) {
        initializeFluentDriver(driver).until(ExpectedConditions.stalenessOf(webelement));
        return webelement.isDisplayed();

    }

    public static boolean isElementEnabled(WebDriver driver, WebElement webelement) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webelement);

        try {
            if (webelement.isEnabled()) {
                System.out.println("Element is enabled");
            }
        } catch (NoSuchElementException nse) {
            initializeFluentDriver(driver).until(ExpectedConditions.elementToBeClickable(webelement));
        }
        return webelement.isEnabled();
    }

    public static void click(WebDriver driver, WebElement webelement) {
        if (isElementEnabled(driver, webelement))
            new Actions(driver).click(webelement).perform();
    }

    public static WebDriver initializeDriver(String browserName) {
        String baseDir = System.getProperty("user.dir") + "/src/test/resources/Drivers/";
        String geckodriverProperty = "webdriver.gecko.driver";
        String chromedriverProperty = "webdriver.chrome.driver";

        if (browserName.equalsIgnoreCase("firefox")) {
//            System.setProperty(geckodriverProperty, baseDir + "geckodriver.exe");
            return new FirefoxDriver();
        } else {
            System.setProperty(chromedriverProperty, baseDir + "chromedriver.exe");
            return new ChromeDriver();
        }
    }

}
