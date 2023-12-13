package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Constants;

import static utilities.ExcelReader.password;
import static utilities.ExcelReader.username;

public class LoginPageTests extends BaseTest {
    @Test(priority = 1)
    public void loginPage_verifyLoginPageTitle() {
        Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void loginPage_verifyLogin() {
        loginPage.logInToApp(username, password);
        Assert.assertTrue(loginPage.isUserLoggedIn());
    }
}
