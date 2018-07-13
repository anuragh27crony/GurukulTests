package stepdefs;

import PageObjects.Entities.BranchPage;
import PageObjects.Entities.StafffPage;
import PageObjects.Entities.ViewBranchPage;
import PageObjects.LoginPage;
import PageObjects.PreLoginNavBar;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

public class BaseStepsImpl {
    protected WebDriver webDriver = null;
    protected UserNavBar userHomepage = null;
    protected PreLoginNavBar preLoginNavBar = null;
    protected LoginPage loginPage = null;
    protected BranchPage branchPage = null;
    protected ViewBranchPage viewBranchPage = null;
    protected StafffPage staffPage = null;
    private static String appHomeURL = "http://localhost:8080";

    protected static String validUsername = "admin";
    protected static String validPassword = "admin";

    @Before
    public void beforeScenario() {
        System.out.println("Entering Before Scenario");
        this.webDriver = WebDriverUtils.initializeDriver("firefox");
        this.webDriver.get(appHomeURL);

        this.userHomepage = new UserNavBar(webDriver);
        this.preLoginNavBar = new PreLoginNavBar(webDriver);
        this.loginPage = new LoginPage(webDriver);
        this.branchPage = new BranchPage(webDriver);
        this.viewBranchPage = new ViewBranchPage(webDriver);
        this.staffPage = new StafffPage(webDriver);
        System.out.println("Exiting Before Scenario");
    }

    @After
    public void afterScenario() {
        this.webDriver.close();
    }

    @Given("^User Login successfully$")
    public void userLoginSuccessfully() {
        preLoginNavBar.navigateToLoginPage();
        loginPage.dologin(validUsername, validPassword, Boolean.TRUE);
    }
}
