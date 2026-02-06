package stepdefinitions;

import config.ConfigReader;
import constants.Endpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.products.PostProducts;
import specs.RequestSpecFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class productClass {

    private Response response;
    private List<Integer> createdIds = new ArrayList<>();

    /* ============================
       COMMON BASE URL
       ============================ */

    @Given("I have the base URL set for products")
    public void setBaseUrlForProducts() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @Given("I have the base URL set for Createproducts")
    public void setBaseUrlForCreateProducts() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    /* ============================
       GET PRODUCTS
       ============================ */

    @When("I GET the post with endpoint")
    public void getProducts() {
        response = RequestSpecFactory.jsonRequest()
                .when()
                .get(Endpoints.Get_fakerestapi);
    }

    @Then("I should be receive status code {int}")
    public void validateGetStatusCode(int statusCode) {
        response.then()
                .log().all()
                .statusCode(statusCode);
    }

    /* ============================
       CREATE PRODUCTS (MULTIPLE ROWS)
       ============================ */

    @When("I create new product with data:")
    public void createNewProducts(DataTable dataTable) {

        // Convert DataTable → List of Maps
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> data : rows) {

            // Create POJO from table row
            PostProducts product = new PostProducts(
                    Integer.parseInt(data.get("id")),
                    data.get("title"),
                    Double.parseDouble(data.get("price")),
                    data.get("description"),
                    data.get("category"),
                    data.get("image")
            );

            // POST request
            response = RequestSpecFactory.jsonRequest()
                    .body(product)
                    .when()
                    .post(Endpoints.AddUser_POST);

            // Validate each POST
            response.then()
                    .log().all()
                    .statusCode(201);
            int createdId = response.jsonPath().getInt("id");
            createdIds.add(createdId);
        }
    }

    @Then("I should be receive statuscode {int}")
    public void validatePostStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Given("I have the base URL set for getid")
    public void iHaveTheBaseURLSetForGetid() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");

    }
    @When("I will hit endoint with path parameter")
    public void iWillHitEndointWithCapturedId() {
        for (Integer id : createdIds) {
            response = RequestSpecFactory.jsonRequest()  // assign to class variable
                    .pathParam("id", id)
                    .get(Endpoints.Get_fakerestapi_path);

            // Validate immediately if you want
            response.then().log().all().statusCode(200);
        }
    }

    @Then("I should be receive status code")
    public void iShouldBeReceiveStatusCode() {
        // Now this works, because 'response' is assigned
       // response.then().log().all().statusCode(200);
    }


    @Given("I have the base URL set for update")
    public void iHaveTheBaseURLSetForUpdate() {
       RestAssured.baseURI = ConfigReader.get("baseUrl");

    }

    @When("I update product where id is {int}")
    public void iUpdateProductWhereIdIs(int id, DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);

        PostProducts updatedProduct = new PostProducts(
                Integer.parseInt(data.get("id")),
                data.get("title"),
                Double.parseDouble(data.get("price")),
                data.get("description"),
                data.get("category"),
                data.get("image")
        );

        response = RequestSpecFactory.jsonRequest()
                .pathParam("id", id)
                .body(updatedProduct)
                .when()
                .put(Endpoints.update_product);
    }



    @Then("I should receive successful status")
    public void iShouldReceiveSuccessfulStatus() {
        response.then().log().all().statusCode(200);
    }
}
