package cucumberOption;
import io.cucumber.junit.Cucumber;
import org.junit.AfterClass;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/thuanTestResource", 
glue = {"stepDefinitions", "cucumberOption"},
		monochrome = true, 
		dryRun = false, 
		plugin = { "pretty", "json:target/site/thuannttest.json",
				"html:target/site/cucumber-html-report.html" 
		}, 
		snippets = CucumberOptions.SnippetType.CAMELCASE, 
				tags = "@apiTest")
public class ApiRunner {

	@AfterClass
	public static void setup() {
		
	}
}
