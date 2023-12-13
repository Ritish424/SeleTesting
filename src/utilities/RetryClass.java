package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryClass implements IRetryAnalyzer {
    private int counter = 0;
    private int maxRetryCounter = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < maxRetryCounter) {
            counter++;
            return true;
        }
        return false;
    }
}
