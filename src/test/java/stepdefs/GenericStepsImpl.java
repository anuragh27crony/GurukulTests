package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GenericStepsImpl {

    @Given("^Sample Given$")
    public void samplegivendef() {
        System.out.println("Inside Sample Given");
    }


    @When("^Sample When$")
    public void samplewhendef() {
        System.out.println("Sample When Statement");
    }

    @Then("^Sample Then$")
    public void samplethendef() {
        System.out.println("Sample Then Statement");
    }
}
