package pageObjects;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ElementUtil;

public class LoginPage {
    ElementUtil elementUtil;
    private WebDriver driver;
    @FindBy(xpath = "//*[@id='top-links']//ul//li//a//span[text()='My Account']")
    private WebElement myAccount;
    @FindBy(xpath = "//*[@class='dropdown-menu dropdown-menu-right']//li//a[text()='Login']")
    private WebElement loginDropDown;
    @FindBy(id = "input-email")
    private WebElement emailId;
    @FindBy(id = "input-password")
    private WebElement password;
    @FindBy(xpath = "//*[@class='btn btn-primary' and @type='submit']")
    private WebElement loginButton;
    @FindBy(xpath = "//*[text()='Edit Account']")
    private WebElement editAccount;
    @FindBy(xpath = "//div[@id='top-links']/ul/li/ul/li/a[text()='Register']")
    private WebElement register;
    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy(xpath = "//input[@name='telephone']")
    private WebElement telephone;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement registerPassword;
    @FindBy(xpath = "//input[@name='confirm']")
    private WebElement confirmPassword;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement continueRegister;
    @FindBy(xpath = "//*[text()='Warning: You must agree to the Privacy Policy!']")
    private WebElement registerErrorMessage;

    public LoginPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public void logInToApp(String userName, String passkey) {
        elementUtil.waitForElementToBeClickable(myAccount);
        elementUtil.clickAction(myAccount);
        elementUtil.waitForElementToBeClickable(loginDropDown);
        elementUtil.clickAction(loginDropDown);
        elementUtil.waitForElementToBeClickable(emailId);
        elementUtil.enterText(emailId, userName);
        elementUtil.waitForElementToBeClickable(password);
        elementUtil.enterText(password, passkey);
        elementUtil.waitForElementToBeClickable(loginButton);
        elementUtil.clickAction(loginButton);
    }

    public String getLoginPageTitle() {
        elementUtil.waitForElementToBeClickable(myAccount);
        elementUtil.clickAction(myAccount);
        elementUtil.waitForElementToBeClickable(loginDropDown);
        elementUtil.clickAction(loginDropDown);
        return elementUtil.returnPageTitle();
    }

    public boolean isUserLoggedIn() {
        return elementUtil.isElementDisplayed(editAccount);
    }

    public void registerNewUser(String fName, String lName, String mail, String phone, String passcode, String confirmPasscode) {
        elementUtil.waitForElementToBeClickable(myAccount);
        elementUtil.clickAction(myAccount);
        elementUtil.waitForElementToBeClickable(register);
        elementUtil.clickAction(register);
        elementUtil.waitForElementToBeClickable(firstName);
        elementUtil.enterText(firstName, fName);
        elementUtil.waitForElementToBeClickable(lastName);
        elementUtil.enterText(lastName, lName);
        elementUtil.enterText(email, mail);
        elementUtil.enterText(telephone, phone);
        elementUtil.enterText(password, passcode);
        elementUtil.enterText(confirmPassword, confirmPasscode);
        elementUtil.clickAction(continueRegister);
    }

    public String getRegisterErrorMessage() {
        return elementUtil.returnText(registerErrorMessage);
    }
}
