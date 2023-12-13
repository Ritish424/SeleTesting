package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Constants;

public class HomePageTests extends BaseTest {

    @Test(priority = 1)
    public void homePage_VerifyPageTitle() {
        Assert.assertEquals(homePage.getHomePageTitle(), Constants.HOME_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void homePage_VerifyChangeCurrencyToEuro() {
        homePage.changeCurrencyToEuro();
        Assert.assertEquals(homePage.getCurrency(), Constants.HOME_PAGE_CURRENCY);
    }

    @Test(priority = 3)
    public void homePage_VerifySearchForIphone() {
        homePage.searchForItem("iphone");
        Assert.assertEquals(homePage.getSearchedItem(), Constants.HOME_PAGE_SEARCH_CRITERIA);
    }

    @Test(priority = 4)
    public void homePage_VerifyShoppingCart() {
        Assert.assertEquals(homePage.getCartItems(), Constants.EMPTY_CART);
    }
}
