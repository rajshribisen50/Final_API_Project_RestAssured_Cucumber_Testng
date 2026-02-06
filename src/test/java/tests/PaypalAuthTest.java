package tests;

import config.ConfigReader;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TokenManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PaypalAuthTest {

    private Response response;

    // =======================
    // 1️⃣ Generate Access Token
    // =======================
    @Test
    public void generateAccessToken() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");

        response = given()
                .auth().preemptive().basic(
                        ConfigReader.get("clientId"),
                        ConfigReader.get("clientSecret"))
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .when()
                .post(Endpoints.Auth_token);

        response.then()
                .statusCode(200)
                .body("access_token", notNullValue())
                .log().all();

        // ✅ Save token globally
        String token = response.jsonPath().getString("access_token");
        TokenManager.setAccessToken(token);
    }

    // =======================
    // 2️⃣ Generate Client Token
    // =======================
    @Test(dependsOnMethods = "generateAccessToken")
    public void generateClientToken() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
        String customerId = String.valueOf(System.currentTimeMillis());

        response = given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .contentType("application/json")
                .body("{\"customer_id\":\"" + customerId + "\"}")
                .when()
                .post(Endpoints.GENERATE_CLIENT_TOKEN);

        response.then().statusCode(200).log().all();
    }

    // =======================
    // 3️⃣ Get User Info
    // =======================
    @Test(dependsOnMethods = "generateAccessToken")
    public void getUserInfo() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");

        response = given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .queryParam("schema", "paypalv1.1")
                .when()
                .get(Endpoints.get_user_info);

        response.then().statusCode(200).log().all();
    }
}
