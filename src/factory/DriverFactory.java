package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties properties;
    OptionsManager optionsManager;

    public Properties initProperties() {
        try {
            String configFilePath = System.getProperty("user.dir") + "/Resources/config.properties";
            FileInputStream inputFile = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(inputFile);
        } catch (IOException ex) {
            System.out.println("Properties File Error: " + ex.toString());
        }
        return properties;
    }

    public WebDriver intiDriver(Properties prop) {
        String browserName = prop.getProperty("browser");
        optionsManager = new OptionsManager(prop);
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(optionsManager.getChromeOptions());
                break;
            case "firefox":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    init_remoteDriver("firefox");
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(optionsManager.getFirefoxOptions());

                }
                break;
            case "edge":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    init_remoteDriver("edge");
                } else {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(optionsManager.getEdgeOptions());
                }
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));
        return driver;
    }

    private void init_remoteDriver(String browserName) {
        try {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    driver = new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionsManager.getChromeOptions());
                    break;
                case "firefox":
                    driver = new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionsManager.getFirefoxOptions());
                    break;
                case "edge":
                    driver = new RemoteWebDriver(new URL(properties.getProperty("huburl")), optionsManager.getEdgeOptions());
                    break;

                default:
                    System.out.println("Wrong browser info....can not run on grid remote machine....");
                    break;
            }

        } catch (MalformedURLException ex) {
            System.out.println("Remote URL Error: " + ex.toString());
        }
    }

}
