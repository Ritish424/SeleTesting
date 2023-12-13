package factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private ChromeOptions chromeOptions;
    private EdgeOptions edgeOptions;
    private FirefoxOptions firefoxOptions;
    private Properties prop;

    public OptionsManager(Properties prop){
        this.prop=prop;

    }
    public ChromeOptions getChromeOptions(){
        chromeOptions= new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--remote-allow-origins=*");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            chromeOptions.addArguments("--incognito");
        }
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions(){
        firefoxOptions= new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            firefoxOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            firefoxOptions.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))) {
            firefoxOptions.setCapability("browserName", "firefox");
            firefoxOptions.setBrowserVersion(prop.getProperty("browserVersion").trim());
        }
        return firefoxOptions;
    }
    public EdgeOptions getEdgeOptions(){
        edgeOptions = new EdgeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            edgeOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            edgeOptions.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))) {
            edgeOptions.setCapability("browserName", "edge");
            edgeOptions.setBrowserVersion(prop.getProperty("browserVersion").trim());
        }
        return edgeOptions;
    }
}
