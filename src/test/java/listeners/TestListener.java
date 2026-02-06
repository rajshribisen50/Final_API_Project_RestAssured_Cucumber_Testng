/*Why?
Automatic report generation
*/
package listeners;
import com.aventstack.extentreports.*;
import org.testng.*;
public class TestListener implements ITestListener {


    static ExtentReports extent = new ExtentReports();
    static ExtentTest test;


    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }


    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }


    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }


    public void onFinish(ITestContext context) {
        extent.flush();
    }
}