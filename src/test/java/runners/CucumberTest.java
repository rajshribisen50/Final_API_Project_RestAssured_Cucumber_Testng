package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {
                "src/test/resources/features/PaypalAuth.feature",
                "src/test/resources/features/PaypalOrder.feature",
                "src/test/resources/features/products.feature",
                "src/test/resources/features/payment.feature"
        },
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:reports/cucumber-report.html"
        }
)
public class CucumberTest extends AbstractTestNGCucumberTests {
}