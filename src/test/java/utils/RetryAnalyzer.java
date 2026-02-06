package utils;
/*
Handle flaky APIs
Avoid false CI failures
 */

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int maxRetry = 2;


    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            return true;
        }
        return false;
    }
}
