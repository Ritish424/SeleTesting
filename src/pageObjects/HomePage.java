package pageObjects;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ElementUtil;

public class HomePage {
    ElementUtil elementUtil;
    private WebDriver driver;
    @FindBy(xpath = "//*[text()='Currency']")
    private WebElement currency;
    @FindBy(xpath = "//*[@name='EUR']")
    private WebElement euro;
    @FindBy(xpath = "//*[text()='€']")
    private WebElement euroSymbol;
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccount;
    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']/li")
    private WebElement menuList;
    @FindBy(xpath = "//*[@id='search']//input[@name='search']")
    private WebElement searchBox;
    @FindBy(xpath = "//*[@class='btn btn-default btn-lg']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id='content']//h1[text()='Search - iphone']")
    private WebElement searchedItem;
    @FindBy(xpath = "//*[@title='iPhone' and @class='img-responsive']")
    private WebElement iphoneImage;
    @FindBy(xpath = "//ul[@class='dropdown-menu pull-right']/li/p[@class='text-center']")
    private WebElement cartMenu;
    @FindBy(id = "cart-total")
    private WebElement cartTotal;

    public HomePage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle() {
        return elementUtil.returnPageTitle();
    }

    public void clickOnMyAccount() {
        elementUtil.waitForElementToBeClickable(myAccount);
        elementUtil.clickAction(myAccount);
        elementUtil.waitForElementToBeClickable(menuList);
    }

    public void changeCurrencyToEuro() {
        elementUtil.waitForElementToBeClickable(currency);
        elementUtil.clickAction(currency);
        elementUtil.waitForElementToBeClickable(euro);
        elementUtil.clickAction(euro);
    }

    public String getCurrency() {
        elementUtil.waitForElementToBeClickable(euroSymbol);
        return elementUtil.returnText(euroSymbol);
    }

    public void searchForItem(String searchItem) {
        elementUtil.waitForElementToBeClickable(searchBox);
        elementUtil.enterText(searchBox, searchItem);
        elementUtil.waitForElementToBeClickable(searchButton);
        elementUtil.clickAction(searchButton);
        elementUtil.waitForElementToBeClickable(iphoneImage);
    }

    public String getSearchedItem() {
        elementUtil.waitForElementToBeClickable(searchedItem);
        return elementUtil.returnText(searchedItem);
    }

    public String getCartItems() {
        elementUtil.clickAction(cartTotal);
        elementUtil.waitForElementToBeClickable(cartMenu);
        return elementUtil.returnText(cartMenu);
    }
}
