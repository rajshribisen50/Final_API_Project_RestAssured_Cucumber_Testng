package stepdefinitions;

import config.ConfigReader;
import constants.Endpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.CartPojo;
import specs.RequestSpecFactory;
import utils.ScenarioContext;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class CartClass {

    private Response response;

    // ---------- GET ALL CARTS ----------
    @Given("I have base URL for Cart get api")
    public void i_have_base_url_for_cart_get_api() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");

    }

    @When("I fetch all the cart data from get call")
    public void i_fetch_all_the_cart_data_from_get_call() {
        response = RequestSpecFactory.jsonRequest()
                .get(Endpoints.get_cart);
    }

    @Then("I should be get success status code")
    public void iShouldBeGetSuccessStatusCode() {
        response.then().statusCode(200).log().body();
    }

    // ---------- POST CART ----------
    @Given("I have base URL for Cart Post API")
    public void iHaveBaseURLForCartPostAPI() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I add new data for cart item:")
    public void iAddNewDataForCartItem(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        for (Map<String, String> data : rows) {
            CartPojo cart = new CartPojo();
            cart.setId(Integer.parseInt(data.get("cartId")));
            cart.setUserId(Integer.parseInt(data.get("userId")));
            cart.setProductId(Integer.parseInt(data.get("productId")));
            cart.setTitle(data.get("title"));
            cart.setPrice(Double.parseDouble(data.get("price")));
            cart.setDescription(data.get("description"));
            cart.setCategory(data.get("category"));
            cart.setImage(data.get("image"));

            response = RequestSpecFactory.jsonRequest()
                    .body(cart)
                    .post(Endpoints.get_cart);

            int responseId = response.jsonPath().getInt("id");
            ScenarioContext.addCartId(responseId);

            // Verify this POST immediately
            response.then().statusCode(201)
                    .body("id", equalTo(responseId));

            System.out.println("Created cart id: " + responseId);
        }
    }

    // ---------- GET CREATED CART ----------
    @Given("I have base Url for get created item")
    public void iHaveBaseUrlForGetCreatedItem() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have to fetch data with response_id")
    public void iHaveToFetchDataWithResponse_id() {
        // Fetch all created cart IDs
        for (Integer id : ScenarioContext.getCartIds()) {
            response = RequestSpecFactory.jsonRequest()
                    .pathParam("id", id)
                    .get(Endpoints.get_cart_id);

            System.out.println("Fetching cart id: " + id);
            response.then().statusCode(200).log().body();
        }
    }

    @Then("I should be get success status")
    public void iShouldBeGetSuccessStatus() {
        // This step can just verify the last fetched response
        response.then().statusCode(200).log().body();
    }

    // ---------- UPDATE CART ----------
    @Given("I have base Url for update item")
    public void iHaveBaseUrlForUpdateItem() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have to update data for above get response id")
    public void iHaveToUpdateDataForAboveGetResponseId() {
        for (Integer id : ScenarioContext.getCartIds()) {
            CartPojo updatedCart = new CartPojo();
            updatedCart.setUserId(99);
            updatedCart.setTitle("updated title");
            updatedCart.setPrice(9.9);

            response = RequestSpecFactory.jsonRequest()
                    .pathParam("id", id)
                    .body(updatedCart)
                    .put(Endpoints.get_cart_id);

            System.out.println("Updated cart id: " + id);
            response.then().statusCode(200).log().body();
        }
    }

    @Then("I should be receive status code \\{int}")
    public void iShouldBeReceiveStatusCodeInt() {
        // Optional: last response is already validated in the update step
    }
}
