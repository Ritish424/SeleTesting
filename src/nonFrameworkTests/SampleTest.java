package nonFrameworkTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class SampleTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static JSONParser parser;

    public static void main(String[] args) throws IOException, ParseException {
        parser = new JSONParser();

        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //SVG Element example & CSS of element
        /*driver.get("https://petdiseasealerts.org/alerts-map/#/");
        handleSVGElement();
        checkCSSOfElement();
        getScreenshotOfElement();
        getPageScreenshot();*/
        //Read and Write Excel File
        /*readExcelFile();
        writeToExcelFile();*/
        //Read data from Json file
        /*readJSONFile();*/
        //Broken Links and Broken Images
        /*driver.get("https://demoqa.com/broken");
        List<WebElement> list = driver.findElements(By.tagName("a"));
        for (WebElement element : list) {
            String url = element.getAttribute("href");
            verifyURL(url);
        }
        List<WebElement> imageList = driver.findElements(By.tagName("img"));
        for (WebElement element : imageList) {
            verifyImage(element);
        }*/

        driver.get("https://www.letskodeit.com/practice");
        handleHiddenElement();

        driver.quit();
    }

    public static void handleSVGElement() {
        List<WebElement> states = driver.findElements(By.xpath("//*[local-name()='g' and @id='states']/*[local-name()='g' and @class='state']/*[local-name()='path' and @class='child']"));
        for (WebElement e : states) {
            try {
                if (e.getAttribute("name").equals("Virginia")) {
                    e.click();
                    Thread.sleep(5000);
                    driver.findElement(By.xpath("//a[text()='United States']")).click();
                    Thread.sleep(5000);
                }
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
    }

    public static void checkCSSOfElement() {
        //Check decoration of element
        driver.findElement(By.xpath("//a[text()='About']")).click();
        WebElement donation = driver.findElement(By.xpath("//u[text()='Sign Up for Pet Disease Alerts Now']"));
        System.out.println("Decoration of donate link: " + donation.getCssValue("text-decoration"));
        //Get background color of element
        WebElement signUp = driver.findElement(By.xpath("//a[text()='Sign up']"));
        String colorRGB = signUp.getCssValue("background-color");
        String colorHex = Color.fromString(colorRGB).asHex();
        System.out.println("RGB value of Sign Up button: " + colorRGB);
        System.out.println("Hex code of Sign Up button: " + colorHex);
    }

    public static void getScreenshotOfElement() {
        driver.findElement(By.xpath("//a[text()='Alert Maps']")).click();
        WebElement map = driver.findElement(By.xpath("//*[@id='map-svg']"));
        File source = map.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("D:\\Projects\\SeleTesting\\Screenshot\\map.png"));
        } catch (IOException exception) {
            System.out.println("Screenshot file exception: " + exception);
        }
    }

    public static void getPageScreenshot() {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("D:\\Projects\\SeleTesting\\Screenshot\\FullPage.png"));
        } catch (IOException exception) {
            System.out.println("Screenshot file exception: " + exception);
        }
    }

    public static void readExcelFile() throws IOException {
        File file = new File("D:\\Projects\\SeleTesting\\Resources\\TestData.xlsx");
        FileInputStream input = new FileInputStream(file);
        XSSFWorkbook book = new XSSFWorkbook(input);
        XSSFSheet sheet = book.getSheet("Sample");
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        System.out.println("**** Total number of rows are: " + rowCount + 1);
        for (int i = 0; i <= rowCount; i++) {
            int cellCount = sheet.getRow(i).getLastCellNum();
            System.out.println("**** Total cells of row-" + i + " are: " + cellCount);
            for (int j = 0; j < cellCount; j++) {
                System.out.print(sheet.getRow(i).getCell(j).getStringCellValue() + " ");
            }
            System.out.println("\n");
        }
        book.close();
    }

    public static void writeToExcelFile() throws IOException {
        File file = new File("D:\\Projects\\SeleTesting\\Resources\\TestData.xlsx");
        FileInputStream input = new FileInputStream(file);
        XSSFWorkbook book = new XSSFWorkbook(input);
        XSSFSheet sheet = book.getSheet("Sample");
        int totalRows = sheet.getLastRowNum();
        String[] data = {"Test Module 3", "Test New Case 3", "New User", "Password@123", "Test details of module"};
        for (int i = 0; i < data.length; i++) {
            XSSFCell cell = sheet.getRow(totalRows + 1).createCell(i);
            cell.setCellValue(data[i]);
        }
        FileOutputStream output = new FileOutputStream(file);
        book.write(output);
        book.close();
        output.close();
    }

    public static void readJSONFile() throws IOException, ParseException {
        JSONArray array = (JSONArray) parser.parse(new FileReader("D:\\Projects\\SeleTesting\\Resources\\data.json"));
        for (Object object : array) {
            JSONObject person = (JSONObject) object;
            String name = (String) person.get("name");
            System.out.println("\nName of the person: " + name);
            String job = (String) person.get("job");
            System.out.println("Job: " + job);
            String city = (String) person.get("city");
            System.out.println("City: " + city);
            JSONArray cars = (JSONArray) person.get("cars");
            System.out.println("Cars: ");
            for (Object car : cars) {
                System.out.print(car + " ");
            }
        }
    }

    public static void verifyURL(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() >= 400) {
                System.out.println("Link: " + link + " is broken");
            } else {
                System.out.println("Link: " + link + " is good");
            }
        } catch (Exception exception) {
            System.out.println("URL Exception: " + exception);
        }
    }

    public static void verifyImage(WebElement image) {
        if (image.getAttribute("naturalWidth").equals("0")) {
            System.out.println("Image: " + image.getAttribute("outerHTML") + " is broken");
        } else System.out.println("Image: " + image.getAttribute("outerHTML") + " is good");
    }

    public static void handleHiddenElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.findElement(By.id("hide-textbox")).click();
        js.executeScript("document.getElementById('displayed-text').value='Selenium';");
        String hiddenValue = (String) js.executeScript("return document.getElementById('displayed-text').value");
        System.out.println("Hidden text box value: " + hiddenValue);
    }
}
