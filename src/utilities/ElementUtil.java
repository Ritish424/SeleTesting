package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;
    private Robot robot;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    //https://github.com/cgjangid/selenium-pom-framework
    public WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("Element could not be located with given locator " + locator);
        }
        return element;
    }

    public void clickAction(WebElement element) {
        element.click();
    }

    public void submitForm(WebElement element) {
        element.submit();
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String returnText(WebElement element) {
        return element.getText();
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void selectDropDownValueByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public void selectDropDownValueByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public List<String> returnDropDownValues(WebElement element) {
        List<String> dropDownValues = new ArrayList<String>();
        Select select = new Select(element);
        List<WebElement> dropDownList = select.getOptions();
        for (WebElement elementList : dropDownList) {
            dropDownValues.add(elementList.getText());
        }
        return dropDownValues;
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Alert waitForAlertPresence() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public WebElement waitForRefreshedElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public WebElement waitForElementWithFluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public String returnPageTitle() {
        return driver.getTitle();
    }

    public void hoverOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void hitEnter() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exception) {
            System.out.println("Key event exception " + exception);
        }
    }

    public void hitBackSpace() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        } catch (Exception exception) {
            System.out.println("Key event exception " + exception);
        }
    }

    public void hitDelete() {
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
        } catch (Exception exception) {
            System.out.println("Key event exception " + exception);
        }
    }

    public void jsExecutorClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}
