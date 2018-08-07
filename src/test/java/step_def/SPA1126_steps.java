package step_def;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.Select;
import pages.AccountsPage;
import pages.HomePage;
import utilities.BrowserUtils;

import static org.testng.Assert.assertTrue;

public class SPA1126_steps {

    HomePage homePage = new HomePage();
    AccountsPage accountsPage = new AccountsPage();

    @When("I navigate to Manage my Accounts")
    public void i_navigate_to_Manage_my_Accounts() {
        homePage.dashboardButton.click();
        // scroll down to the element to be visible
        BrowserUtils.scrollDownToElement(homePage.manageMyAccountsButton);
        BrowserUtils.waitFor(2);
        homePage.manageMyAccountsButton.click();
    }

    @And("I try to transfer funds to the same account")
    public void i_try_to_transfer_funds_to_the_same_account() {
        // switch a tab
        String title = "\n" +
                "\t\t\tAccounts | \t\t\tBudgetPulse\t\t";
        BrowserUtils.switchToWindow(title);
        // scroll down to the element to be visible
        BrowserUtils.scrollDownToElement(accountsPage.transferFundsButton);
        BrowserUtils.waitFor(2);
        accountsPage.transferFundsButton.click();
        // select account from which to do transfer
        Select fromAccount = new Select(accountsPage.selectFromAccountOption);
        //  Random random = new Random();
        fromAccount.selectByIndex(1);
        // select account to which send a transfer
        Select toAccount = new Select(accountsPage.selectToAccountOption);
        toAccount.selectByIndex(1);
        // leave a description
        String description = "July transfer";
        accountsPage.transferDescription.clear();
        accountsPage.transferDescription.sendKeys(description);
        // amount of money to transfer
        accountsPage.transferAmount.sendKeys("100");
        // submit
        accountsPage.transferFundsSubmitButton.click();
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        // verify error message is displayed
        BrowserUtils.waitFor(2);
        assertTrue(accountsPage.transferErrorMessage.isDisplayed());
    }
}
