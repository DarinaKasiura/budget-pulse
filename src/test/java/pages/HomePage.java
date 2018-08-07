package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;


public class HomePage {

    private WebDriver driver = Driver.getDriver();

    public HomePage() {
        PageFactory.initElements(driver, this);
    }
  
@FindBy(xpath = "//a[@href='/dashboard']")
    public WebElement dashboardButton;

    @FindBy(id = "link_manage_accounts")
    public WebElement manageMyAccountsButton;

    @FindBy(xpath = "//h2[contains(text(),'Transfer Funds ')]")
    public WebElement transferFundsButton;

    @FindBy(id = "TransactionFromAccountId")
    public WebElement selectTransferFromAccountMenu;

    @FindBy(id = "TransactionToAccountId")
    public WebElement selectTransferToAccountMenu;

    @FindBy(id = "TransferTransactionDescription")
    public WebElement transferTransactionDescriptionInputBox;

    @FindBy(id = "TransferTransactionAmount")
    public WebElement transferTransactionAmountInputBox;

    @FindBy(xpath = "//a[@id='toggle_details_table']")
    public WebElement transactionDetailsLink;

    @FindBy(xpath = "//dl[@class='checking-container']//a[@class='single-account-view']")
    public WebElement checkingAccountLink;

    @FindBy(xpath = "//div[@id='accounts_import']/a")
    public WebElement importOrExportDataLink;

    @FindBy(xpath = "//a[@id='btn_add_account']/span")
    public WebElement addAccountButton;

    @FindBy(xpath = "//select[@id='AccountAccountTypeId']")
    public WebElement accountTypeMenu;

    @FindBy(xpath = "//input[@name='data[Account][name]']")
    public WebElement accountNameInputBox;

    @FindBy(xpath = "//input[@name='data[Account][starting_balance]']")
    public WebElement openingBalanceInputBox;

    @FindBy(xpath = "//div[@class='notify']")
    public WebElement addAccountErrorAlertBox;
    @FindBy(xpath = "(//a[@href='#'])[3]")
    public WebElement startLoginBtn;

    @FindBy (linkText = "Budget")
    public WebElement budgetLink;

    @FindBy (linkText = "Dashboard")
    public WebElement dashboardLink;

    @FindBy (id = "select_budget_type")
    public WebElement overviewSelector;

    @FindBy (xpath = "//div[@id='panel_budget']//div[@class='capsule capsule-neutral capsule-first']")
    public WebElement budgetedMoney;

    @FindBy (linkText = "Add New")
    public WebElement addNewLinkRecentTrans;
    
    @FindBy(xpath = "(//div[@class='box-header'])[1]")
	public WebElement recentTransaction;

	@FindBy(name = "transaction_details_month")
	public WebElement customSelect;

	@FindBy(name = "data[Trans][from_date]")
	public WebElement fromDate;

	@FindBy(name = "data[Trans][to_date]")
	public WebElement toDate;

	@FindBy(xpath = "//button[@class='submit-button']")
	public WebElement submitBtn;

    @FindBy(linkText="Cash Flow")
    public WebElement cashFlow;

    @FindBy(xpath="//form[@id='TransactionDeleteForm']/table/tbody/tr[1]/td[1]//a")
    public WebElement toggle;

    @FindBy(xpath="//tr[3]//li[3]")
    public WebElement addNoteButton;



    @FindBy(xpath="//a[@rel='panel_budget_link']")
    public WebElement budgetDash;

    @FindBy(xpath = "//select[@id='select_budget_type']")
    public WebElement defaultSelection;

    @FindBy(xpath = "(//div[@class='dashboard_panel_content']//div/span)[1]")
    public WebElement budgeted;

    @FindBy(xpath = "(//div[@class='dashboard_panel_content']//div/span)[2]")
    public  WebElement actualExpenses;

    @FindBy(xpath = "(//div[@class='dashboard_panel_content']//div/span)[3]")
    public  WebElement upcomingExpenses;

    @FindBy(xpath = "//a[@accesskey='3']")
    public WebElement logout;

    public String checkDefaultSelection(WebElement selection){
        Select s=new Select(selection);
        return s.getFirstSelectedOption().getText();
    }

}
