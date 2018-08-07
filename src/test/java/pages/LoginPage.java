package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;
import utilities.Config;
import utilities.Driver;

public class LoginPage {

    private WebDriver driver = Driver.getDriver();

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='btn-slide']")
    public WebElement startLoginBtn;

    @FindBy(xpath = "//input[@id='login_username']")
    public WebElement eMailBox;

    @FindBy(xpath = "//input[@id='login_password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//a[@id='login-link']")
    public WebElement loginBtn;

    public void login() {
        startLoginBtn.click();
        BrowserUtils.waitForClickablility(eMailBox, 10);
        eMailBox.sendKeys(Config.getProperty("username"));
        passwordBox.sendKeys(Config.getProperty("password"));
        loginBtn.click();
    }
}
