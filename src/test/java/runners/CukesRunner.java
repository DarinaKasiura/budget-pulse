package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"pretty",
                "html:target/cucumber-report", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "step_def",
        tags = "@add",
        dryRun = false
)

public class CukesRunner {

}
