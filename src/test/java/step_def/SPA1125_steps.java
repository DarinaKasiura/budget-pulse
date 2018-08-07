package step_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.TransactionsPage;
import utilities.BrowserUtils;
import utilities.Config;
import utilities.Driver;

public class SPA1125_steps {

    WebDriver driver;
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    TransactionsPage transPage = new TransactionsPage();

    @Given("I logged in to the application")
    public void i_logged_in_to_the_application() {
        driver = Driver.getDriver();
        driver.get(Config.getProperty("url"));
        loginPage.login();
    }

    @When("I navigate to Recent Transactions")
    public void i_navigate_to_Recent_Transactions() throws InterruptedException{
        homePage.dashboardButton.click();
        homePage.cashFlow.click();
        // scroll down to the element to be visible
        BrowserUtils.waitFor(2);
        BrowserUtils.scrollDownToElement(driver);
        BrowserUtils.waitFor(2);
    }

    @Then("I should be able to add a note to a Transaction")
    public void i_should_be_able_to_add_a_note_to_a_Transaction() {
        // click on toggle button and addNote button
        homePage.toggle.click();
        homePage.addNoteButton.click();
        // switch to a new tab
        String title = "\n" +
                "\t\t\tTransactions | \t\t\tBudgetPulse\t\t";
        BrowserUtils.switchToWindow(title);
        // add a note and save it
        transPage.transAddNoteField.clear();
        String noteToAdd = "Cybertek payment";
        transPage.transAddNoteField.sendKeys(noteToAdd);
        transPage.submitNDoneBtn.click();
    }
}
