package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"html:target/cucumber-report", "json:target/cucumber.json"},
        features = "features",
        glue = "step_def",
        tags = "@smoke",
        dryRun = false
)

public class CukesRunner {

}
