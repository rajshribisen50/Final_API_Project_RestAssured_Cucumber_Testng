package base;
/*
Why?

Avoid duplicate setup

Central auth & headers
 */

import config.ConfigReader;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class, listeners.TestListener.class})
public class BaseTest {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }
}