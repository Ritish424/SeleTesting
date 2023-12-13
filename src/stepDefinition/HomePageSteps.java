package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import utilities.Constants;

public class HomePageSteps {
    WebDriver driver=ApplicationHooks.driver;
    public HomePage homePage = new HomePage(driver);

    @Given("user is on home page")
    public void user_is_on_home_page() {
        System.out.println(homePage.getHomePageTitle());
    }

    @When("user gets home page title")
    public void user_gets_page_title() {
        System.out.println(homePage.getHomePageTitle());
    }

    @Then("Page title should be \"Your Store\"")
    public void page_title_should_be_your_store() {
        Assert.assertEquals(homePage.getHomePageTitle(), Constants.HOME_PAGE_TITLE);
    }

    @When("user search for iphone")
    public void user_search_for_iphone() {
        homePage.searchForItem("iphone");
    }

    @Then("iphone results should display")
    public void iphone_results_should_display() {
        Assert.assertEquals(homePage.getSearchedItem(), Constants.HOME_PAGE_SEARCH_CRITERIA);
    }
}
