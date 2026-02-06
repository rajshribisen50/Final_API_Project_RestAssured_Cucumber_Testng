package base;
/*
Why?

Avoid duplicate setup

Central auth & headers
 */

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class BaseTest {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }
}