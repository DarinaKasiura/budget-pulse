package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AccountsPage {

    private WebDriver driver = Driver.getDriver();
    public AccountsPage (){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[@id='sidebar']//a[contains(@class, 'box-header-action box-toggler')]")
    public WebElement transferFundsButton;

    @FindBy(id="TransactionFromAccountId")
    public WebElement selectFromAccountOption;

    @FindBy(id="TransactionToAccountId")
    public WebElement selectToAccountOption;

    @FindBy(id="TransferTransactionDescription")
    public WebElement transferDescription;

    @FindBy(id="TransferTransactionAmount")
    public WebElement transferAmount;

    @FindBy(xpath="//form[@id='transfer_funds']//button")
    public WebElement transferFundsSubmitButton;

    @FindBy(id="transfer_funds_errors")
    public WebElement transferErrorMessage;

    @FindBy(xpath="//table/tbody/tr[11]/td[1]//a")
    public WebElement toggleOfAccountDFGSD;

    @FindBy(xpath="//table/tbody/tr[12]/td[3]//a")
    public WebElement deleteAccountDFGSDButton;

    @FindBy(id="manage_accounts_header")
    public WebElement manageAccountsHeader;

    @FindBy(id="cancelDialog")
    public WebElement cancelDeleteAccountPopUp;


}
