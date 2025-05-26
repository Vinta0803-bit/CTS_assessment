package runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/resources/features/TodoFeature.feature"},
        glue = {"step_definitions"},
        plugin = {"pretty","html:target/cucumber-reports.html", "json:target/cucumber-reports.json"},
        monochrome = true
)

public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}
