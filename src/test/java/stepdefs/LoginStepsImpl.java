package stepdefs;

import PageObjects.LoginPage;
import PageObjects.PreLoginNavBar;
import PageObjects.UserNavBar;
import Utils.WebDriverUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginStepsImpl {
    private WebDriver webDriver = null;
    private UserNavBar userHomepage = null;
    private PreLoginNavBar preLoginNavBar = null;
    private LoginPage loginPage = null;
    private static String appHomeURL = "http://localhost:8080";

    private static String validUsername = "admin";
    private static String validPassword = "admin";
    private static String invalidUsername = "auiwhfwiuef";
    private static String invalidPassword = "iosajdioasjd";


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private String ErrMsgInvalidCredentials = "Authentication failed! Please check your credentials and try again.";
    private String SuccessfulLoginWelcomeMsg = "You are logged in as user \"admin\".";

    @Before
    public void beforeScenario() {
        this.webDriver = WebDriverUtils.initializeDriver("firefox");
        this.webDriver.get(appHomeURL);

        this.userHomepage = new UserNavBar(webDriver);
        this.preLoginNavBar = new PreLoginNavBar(webDriver);
        this.loginPage = new LoginPage(webDriver);
    }

    @After
    public void afterScenario() {
        this.webDriver.close();
    }

    @Given("^User navigates to Gurukul Homepage$")
    public void userNavigatesToGurukulHomepage() {
        preLoginNavBar.navigateToHomePage();
    }

    @Given("^User selects Login$")
    public void userSelectsLogin() {
        preLoginNavBar.navigateToLoginPage();
    }

    @When("^User fills valid username and password$")
    public void userFillsValidUsernameAndPassword() {
        loginPage.dologin(validUsername, validPassword, Boolean.TRUE);
    }

    @When("^User fills invalid username and password$")
    public void userFillsInvalidUsernameAndPassword() {
        loginPage.dologin(invalidUsername, invalidUsername, Boolean.TRUE);
    }


    @When("^User fills password with empty Username$")
    public void userFillsPasswordWithEmptyUsername() {
        loginPage.dologin("", validPassword, Boolean.TRUE);
    }


    @When("^User fills Valid Username with empty password$")
    public void userFillsValidUsernameWithEmptyPassword() {
        loginPage.dologin(validUsername, "", Boolean.TRUE);
    }


    @Then("^Login should be successful$")
    public void loginShouldBeSuccessful() {
        assertThat(SuccessfulLoginWelcomeMsg, equalTo(userHomepage.getUserWelcomeMessage()));
    }

    @Then("^User should see UserNavigationBar$")
    public void userShouldSeeUserNavigationBar() {
        assertThat(userHomepage.isUserNavBarAvailable(), equalTo(Boolean.TRUE));
    }


    @Then("^Login should not be successful$")
    public void loginShouldNotBeSuccessfull() {
        userHomepage.getUserWelcomeMessage();
        thrown.expect(NoSuchElementException.class);
    }

    @Then("^Error message for Invalid Credentials should be displayed$")
    public void errorMessageForInvalidCredentialsShouldBeDisplayed() {
        assertThat(loginPage.getErrorMsgInvalidCredentials(), equalTo(ErrMsgInvalidCredentials));
    }
}
