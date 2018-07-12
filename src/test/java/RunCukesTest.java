import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = "stepdefs",
        plugin = {"pretty", "html:target/cucumberReports/"})
public class RunCukesTest {
}
