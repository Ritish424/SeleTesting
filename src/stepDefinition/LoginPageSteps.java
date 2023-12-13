package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import utilities.Constants;

import java.util.List;
import java.util.Map;

import static utilities.ExcelReader.password;
import static utilities.ExcelReader.username;

public class LoginPageSteps {

    WebDriver driver = ApplicationHooks.driver;
    public LoginPage loginPage = new LoginPage(driver);

    @Given("user is on login page")
    public void user_is_on_login_page() {
        String loginTitle = loginPage.getLoginPageTitle();
    }

    @When("user gets login page title")
    public void user_gets_login_page_title() {
        String loginTitle = loginPage.getLoginPageTitle();
    }

    @Then("Page title should be \"Account Login\"")
    public void Page_title_should_be_Account_Login() {
        Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE);
    }

    @When("user enter {word} and {word} and click on login button")
    public void user_enter_username_and_password_and_click_on_login_button(String username, String password) {
        loginPage.logInToApp(username, password);
    }

    @Then("user should login to application")
    public void user_should_login_to_application() {
        Assert.assertTrue(loginPage.isUserLoggedIn());
    }

    @When("user enter below details in register page")
    public void userEnterBelowDetailsInRegisterPage(DataTable dataTable) {
        List<Map<String, String>> userDetails = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> users : userDetails) {
            String fName = users.get("firstName");
            String lName = users.get("lastName");
            String email = users.get("email");
            String phone = users.get("phone");
            String password = users.get("password");
            String confirmPassword = users.get("confirmPassword");
            loginPage.registerNewUser(fName, lName, email, phone, password, confirmPassword);
        }

    }

    @Then("user should get registration error")
    public void userShouldGetRegistrationError() {
        Assert.assertEquals(loginPage.getRegisterErrorMessage(), Constants.LOGIN_PAGE_REGISTER_ERROR_MESSAGE);
    }

    @When("^user enter \"(.*)\" and \"(.*)\" and click on login$")
    public void userEnterLoginCredentialsAndClickOnLogin(String userName, String password) {
        loginPage.logInToApp(userName, password);
    }
}
