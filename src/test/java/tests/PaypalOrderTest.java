package tests;

import config.ConfigReader;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.TokenManager;

import java.util.*;

import static io.restassured.RestAssured.given;

public class PaypalOrderTest {

    private Response response;
    private String orderid;
    private String confirmorderid;

    //CREATE ORDER
    @Test(dependsOnMethods = "tests.PaypalAuthTest.generateAccessToken")
    public void createPaypalOrder() {

        // Set base URI
        RestAssured.baseURI = ConfigReader.get("baseUrl");

        // ---------- Example Data ----------
        String intent = "CAPTURE";
        String returnUrl = "https://developer.paypal.com";
        String cancelUrl = "https://www.bing.com";
        String userAction = "PAY_NOW";
        String currencyCode = "USD";
        String value = "100.00";

        // ---------- Amount ----------
        Map<String, Object> amount = new HashMap<>();
        amount.put("currency_code", currencyCode);
        amount.put("value", value);

        // ---------- Purchase Unit ----------
        Map<String, Object> purchaseUnit = new HashMap<>();
        purchaseUnit.put("amount", amount);

        List<Map<String, Object>> purchaseUnits = new ArrayList<>();
        purchaseUnits.add(purchaseUnit);

        // ---------- Experience Context ----------
        Map<String, Object> experienceContext = new HashMap<>();
        experienceContext.put("return_url", returnUrl);
        experienceContext.put("cancel_url", cancelUrl);
        experienceContext.put("user_action", userAction);

        Map<String, Object> paypal = new HashMap<>();
        paypal.put("experience_context", experienceContext);

        Map<String, Object> paymentSource = new HashMap<>();
        paymentSource.put("paypal", paypal);

        // ---------- Final Payload ----------
        Map<String, Object> payload = new HashMap<>();
        payload.put("intent", intent);
        payload.put("payment_source", paymentSource);
        payload.put("purchase_units", purchaseUnits);

        // ---------- Send Request ----------
        response = given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .contentType("application/json")
                .body(payload)
                .when()
                .post(Endpoints.Create_order);

        // ---------- Validate Response ----------
        response.then().statusCode(200).log().all();
        System.out.println(response);
        orderid = response.then().statusCode(200).extract().path("id");
        System.out.println(orderid);
    }


    //Confirm payment source
    @Test(dependsOnMethods = "createPaypalOrder")

    public void confirmPaymentSource(){
        String number = "4111111111111111";
        String expiry = "2035-12";

        Map<String, Object> card = new HashMap<>();
        card.put("number", number);
        card.put("expiry", expiry);

        Map<String, Object> payment_Source = new HashMap<>();
        payment_Source.put("card", card);

        response = given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .contentType("application/json")
                .pathParam("id", orderid).log().all() // ✅ use the actual order ID
                .body(Map.of("payment_source", payment_Source))
                .when()
                .post(Endpoints.Confirm_Payament_Source);

        response.then().statusCode(200).log().all();
        confirmorderid = response.then().statusCode(200).extract().path("id");
        System.out.println("confirm order:"+confirmorderid);
    }
    //SHOW ORDER DETAILS
    @Test(dependsOnMethods ="confirmPaymentSource" )
    public void showOrderDetails(){
        response = given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .contentType("application/json")
                .pathParam("id", orderid).log().all()
                .when()
                .get(Endpoints.Show_Order_Details);

        response.then().statusCode(200).log().all();

    }

    //Update Order
    @Test
    public void updateOrder(){
        List<Map<String, Object>> updateorderDetails = new ArrayList<>();

        Map<String, Object> value = new HashMap<>();
        value.put("address_line_1","123 Townsend St");
        value.put("address_line_2","456 Townsend St");
        value.put("admin_area_1","CA");
        value.put("admin_area_2","US");
        value.put("postal_code","94107");
        value.put("country_code","US");

        Map<String, Object> paypal = new HashMap<>();
        paypal.put("op","add");
        paypal.put("path","/purchase_units/@reference_id=='default'/shipping/address");
        paypal.put("value", value);

        Map<String, Object> paypal1 = new HashMap<>();
        paypal1.put("op","add");
        paypal1.put("value", "03012022-3303-0");
        paypal1.put("path","/purchase_units/@reference_id=='default'/invoice_id");

        updateorderDetails.add(paypal);
        updateorderDetails.add(paypal1);

        response = given()
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .header("Content-Type", "application/json")
                .header("PayPal-Request-Id", UUID.randomUUID().toString()) // optional, unique id
                .body(updateorderDetails)
                .pathParam("id", orderid)
                .when()
                .patch(Endpoints.Update_Order);  // make sure this points to /v2/checkout/orders/{id}

        // ---------- Validate response ----------
        response.then().statusCode(204).log().all();






    }

}
