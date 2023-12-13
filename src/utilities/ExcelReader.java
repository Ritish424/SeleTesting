package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static utilities.Constants.SHEET_NAME;

public class ExcelReader {
    static Workbook book;
    static Sheet sheet;
    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/Resources/TestData.xlsx";
    public static String username;
    public static String password;
    public static String testData;

    public static LinkedHashMap<String, String> getLoginCredentials(String testSheet, String testCase) {
        LinkedHashMap<String, String> credentials = new LinkedHashMap<>();
        LinkedList<String> data = new LinkedList<>();
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);

        } catch (FileNotFoundException exception) {
            System.out.println("Input File Exception" + exception);
        }
        try {
            book = new XSSFWorkbook(file);
            String value;
            int noOfSheets = book.getNumberOfSheets();
            for (int i = 0; i < noOfSheets; i++) {
                if (book.getSheetName(i).equalsIgnoreCase(testSheet)) {
                    sheet = book.getSheetAt(i);
                    for (int rowNumber = 0; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
                        XSSFRow row = (XSSFRow) sheet.getRow(rowNumber);
                        if (row.getCell(1).getStringCellValue().equalsIgnoreCase(testCase)) {
                            for (int columnNumber = 0; columnNumber < row.getLastCellNum(); columnNumber++) {
                                XSSFCell cell = row.getCell(columnNumber);
                                value = cell.getStringCellValue();
                                data.add(value);
                            }
                        }
                    }
                }
            }
        } catch (IOException exception) {
            System.out.println("Excel Workbook Exception: " + exception);
        }
        if (data.isEmpty()) {
            credentials.put("username", "ritishkommana24@gmail.com");
            credentials.put("password", "Ritish@424");

        } else {
            credentials.put("username", data.get(2));
            credentials.put("password", data.get(3));
            if (data.size() > 4) {
                credentials.put("testData", data.get(4));
            }
        }
        return credentials;
    }

    public static void getTestCredentials(String methodName) {
        LinkedHashMap<String, String> credentials;
        credentials = getLoginCredentials(SHEET_NAME, methodName);
        username = credentials.get("username");
        password = credentials.get("password");
        if (credentials.containsKey("testData")) {
            testData = credentials.get("testData");
        }
    }
}
