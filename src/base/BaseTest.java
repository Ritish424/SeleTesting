package base;

import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import static utilities.ExcelReader.getTestCredentials;

public class BaseTest {

    public static Properties prop;
    public static WebDriver driver;
    public static String methodName;
    public HomePage homePage;
    public LoginPage loginPage;
    DriverFactory driverFactory;

    @BeforeMethod
    public void setUp(Method method) {
        driverFactory = new DriverFactory();
        prop = driverFactory.initProperties();
        driver = driverFactory.intiDriver(prop);

        methodName = method.getName();
        getTestCredentials(methodName);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/Screenshot/" + methodName + "_" + System.currentTimeMillis() + ".png";
        System.out.println("user directory: " + System.getProperty("user.dir"));
        System.out.println("screenshot path: " + path);
        File destination = new File(path);
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException exception) {
            System.out.println("Screenshot file exception: " + exception);
        }
        return path;
    }
}
