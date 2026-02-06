package stepdefinitions;

import config.ConfigReader;

import constants.Endpoints;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TokenManager;

import static io.restassured.RestAssured.given;


public class PaypalOrder {

    private Response response;



    @Given("I have base URL FOR Order Api")
    public void iHaveBaseURLFOROrderApi() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");

    }

    @When("I have valid request payload for create order")
    public void iHaveValidRequestPayloadForCreateOrder(DataTable dataTable)
    {
        Map<String, String> data = dataTable.asMaps().get(0);
        // ---------- Amount ----------
        Map<String, Object> amount = new HashMap<>();
        amount.put("currency_code", data.get("currency_code"));
        amount.put("value", data.get("value"));

        // ---------- Purchase Unit ----------
        Map<String, Object> purchaseUnit = new HashMap<>();
        purchaseUnit.put("amount", amount);

        List<Map<String, Object>> purchaseUnits = new ArrayList<>();
        purchaseUnits.add(purchaseUnit);

        // ---------- Experience Context ----------
        Map<String, Object> experienceContext = new HashMap<>();
        experienceContext.put("return_url", data.get("return_url"));
        experienceContext.put("cancel_url", data.get("cancel_url"));
        experienceContext.put("user_action", data.get("user_action"));

        Map<String, Object> paypal = new HashMap<>();
        paypal.put("experienceContext", experienceContext);

        Map<String, Object> paymentSource = new HashMap<>();
        paymentSource.put("paypal", paypal);
        // ---------- Final Payload ----------
        Map<String, Object> payload = new HashMap<>();
        payload.put("intent", data.get("intent"));
        payload.put("payment_source", paymentSource);
        payload.put("purchase_units", purchaseUnits);

        response =
                given()
                        .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                        .contentType("application/json")
                        .body(payload)
                        .when()
                        .post(Endpoints.Create_order);

    }

    @Then("I should get success response message for paypal")
    public void iShouldGetSuccessResponseMessageForPaypal() {
      response.then().assertThat().statusCode(200);
    }
}
