package step_def;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.HomePage;
import utilities.Driver;

public class Hooks {

    HomePage hp=new HomePage();
  
    @Before
    public void setUp(){

    }

	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			scenario.write("THIS SCENARIO FAILED");
		}
		Driver.closeDriver();
}
}
