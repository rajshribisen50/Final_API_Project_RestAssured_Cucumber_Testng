package stepdefinitions;

import config.ConfigReader;
import constants.Endpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utils.TokenManager;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PaypalAuth {

    Response response;

    // =======================
    // GET ACCESS TOKEN
    // =======================
    @Test
    @Given("I have base url for test auth paypal")
    public void iHaveBaseUrlForTestAuthPaypal() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have request to get access token with following data:")
    public void iHaveRequestToGetAccessTokenWithFollowingData(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String grantType = rows.get(0).get("grant_type");

        response =
                given()
                        .auth().preemptive().basic(
                                ConfigReader.get("clientId"),
                                ConfigReader.get("clientSecret"))
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("grant_type", grantType)
                        .when()
                        .post("/v1/oauth2/token");
    }

    @Then("I should get success response message")
    public void iShouldGetSuccessResponseMessage() {

        response.then()
                .statusCode(200)
                .body("access_token", notNullValue())
                .log().all();

        String token = response.jsonPath().getString("access_token");

        // ✅ SAVE TOKEN GLOBALLY
        TokenManager.setAccessToken(token);
    }

    // =======================
    // GET USER INFO
    // =======================

    @Given("I have valid base url to get user info")
    public void iHaveValidBaseUrlToGetUserInfo() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have get valid get request data")
    public void iHaveGetValidGetRequestData() {

        response =
                given()
                        .baseUri(ConfigReader.get("baseUrl"))
                        .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                        .queryParam("schema", "paypalv1.1")
                        .when()
                        .get(Endpoints.get_user_info);
    }

    @Then("I should get valid success response")
    public void iShouldGetValidSuccessResponse() {
        response.then().statusCode(200).log().all();
    }

    // =======================
    // GENERATE CLIENT TOKEN
    // =======================

    @Given("I have valid base url to generate client token")
    public void iHaveValidBaseUrlToGenerateClientToken() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have valid request to generate client token")
    public void iHaveValidRequestToGenerateClientToken() {

        String customerId = String.valueOf(System.currentTimeMillis());
        System.out.println("customerId: " + customerId);

        response =
                given()
                        .baseUri(ConfigReader.get("baseUrl"))
                        .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                        .contentType("application/json")
                        .body("{ \"customer_id\": \"" + customerId + "\" }")
                        .when()
                        .post(Endpoints.GENERATE_CLIENT_TOKEN);
    }

    @Then("I should get valid success response for client token")
    public void iShouldGetValidSuccessResponseForClientToken() {
        response.then().statusCode(200).log().all();
    }
}
