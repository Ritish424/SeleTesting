package stepDefinition;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks {
    protected static WebDriver driver;
    private Properties properties;
    private DriverFactory driverFactory;

    public static String getReportConfigPath() {
        String reportConfigPath = System.getProperty("user.dir") + "/src/test/resources/extent-config.xml";
        return reportConfigPath;
    }

    @Before(order = 0)
    public void getProperty() {
        driverFactory = new DriverFactory();
        properties = driverFactory.initProperties();
    }

    @Before(order = 1)
    public void launchBrowser() {
        driver = driverFactory.intiDriver(properties);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.close();
        driver.quit();
    }
}
