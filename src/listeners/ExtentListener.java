package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

public class ExtentListener implements ITestListener {

    private static ExtentReports exReports;
    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    private static final String FILE_PATH = "TestExecutionReport.html";
    private static final String OUTPUT_FOLDER = "./Reports/";

    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException exception) {
                System.out.println("Input path exception: " + exception);
            }

        }
        exReports = new ExtentReports();
        ExtentSparkReporter htmlReport = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_PATH);
        htmlReport.config().setReportName("Automation testing Results");
        exReports.attachReporter(htmlReport);
        exReports.setSystemInfo("System", "Windows");
        exReports.setSystemInfo("Author", "Ritish");
        return exReports;

    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is finished!"));
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println((methodName + " passed!"));
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        String methodName = result.getMethod().getMethodName();

        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(BaseTest.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        String methodName = result.getMethod().getMethodName();
        test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(BaseTest.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
