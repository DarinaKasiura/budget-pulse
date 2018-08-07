package step_def;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AccountsPage;
import utilities.BrowserUtils;
import utilities.Driver;

import static org.testng.Assert.assertTrue;

public class SPA1133_steps {

    AccountsPage accountsPage = new AccountsPage();

    @When("I delete account")
    public void i_delete_account() {
        // scroll down to the element to be visible
        BrowserUtils.scrollDownToElement(accountsPage.manageAccountsHeader);
        BrowserUtils.waitFor(2);
        // choose dfgsd account and delete it
        accountsPage.toggleOfAccountDFGSD.click();
        accountsPage.deleteAccountDFGSDButton.click();
        // handle dialog pop up
        accountsPage.cancelDeleteAccountPopUp.click();
    }

    @Then("I should not see it displayed")
    public void i_should_not_see_it_displayed() {
        // check if DFGSD account is displayed
        WebElement dfgsd = Driver.getDriver().findElement(By.id("Account-name-253481"));
        assertTrue(dfgsd.isDisplayed());
    }
}
