package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class TransactionsPage {

    private WebDriver driver = Driver.getDriver();

    public TransactionsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "TransactionTransactionType")
    public WebElement transTypeSelector;

    @FindBy (id = "TransactionCategoryName")
    public WebElement transCategoryNameBox;

    @FindBy (id = "TransactionDescription")
    public WebElement transDescriptionBox;

    @FindBy (id = "TransactionAmount")
    public WebElement transAmountBox;

    @FindBy (id = "TransactionAccountId")
    public WebElement transAccountSelector;

    @FindBy (id = "TransactionPayee")
    public WebElement transPayeeBox;

    @FindBy (id = "submitDone")
    public WebElement submitNDoneBtn;

    @FindBy(id="TransactionNote")
    public WebElement transAddNoteField;

}
