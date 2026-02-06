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
import pojo.UsersPojo;
import specs.RequestSpecFactory;
import utils.ScenarioContext;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class UserClass {

    //GET USERS
    private Response response;

    @Given("I have base URL for user get api")
    public void i_have_base_url_for_user_get_api() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I fetch all the user data from get call")
    public void iFetchAllTheUserDataFromGetCall() {
        response = RequestSpecFactory.jsonRequest()
                .get(Endpoints.get_users);
    }

    @Then("I should be get success status code for use fetch")
    public void iShouldBeGetSuccessStatusCodeForUseFetch() {
        response.then().log().all();
    }

    // Create new User

    @Given("I have base URL for create user api")
    public void iHaveBaseURLForCreateUserApi() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I create new user with data:")
    public void iCreateNewUserWithData(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> data : rows) {

            UsersPojo user = new UsersPojo();
            user.setId(Integer.parseInt(data.get("id")));
            user.setUsername(data.get("username"));
            user.setEmail(data.get("email"));
            user.setPassword(data.get("password"));

            response = RequestSpecFactory.jsonRequest()
                    .body(user)   // ✅ SEND BODY
                    .post(Endpoints.get_users);

            response.then().log().all();

            int userId = response.jsonPath().getInt("id"); // ✅ CORRECT EXTRACTION
            ScenarioContext.addUserId(userId);

            response.then()
                    .statusCode(201)
                    .body("id", equalTo(userId));

            System.out.println("Created user id: " + userId);



        }


    }

    @Then("I should get success response")
    public void iShouldGetSuccessResponse() {
      response.then().log().all();
    }

    //Get single user details
    @Given("I have base URL for get created user")
    public void iHaveBaseURLForGetCreatedUser() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have to get user details for fetched id")
    public void iHaveToGetUserDetailsForFetchedId() {
        for(Integer id : ScenarioContext.getUserIds()){
            response = RequestSpecFactory.jsonRequest()
                    .pathParam("id", id)
                    .get(Endpoints.get_singleuser);

            System.out.println("Fetching user id: " + id);

    }}

    @Then("I have to get success response for fetched data")
    public void iHaveToGetSuccessResponseForFetchedData() {
     response.then().log().all();
    }
//Update User
    @Given("I have base URL to update user")
    public void iHaveBaseURLToUpdateUser() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have to update request body:")
    public void iHaveToUpdateRequestBody(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps();

        for (Map<String, String> data : rows) {

            UsersPojo updatedUser = new UsersPojo();
            updatedUser.setId(Integer.parseInt(data.get("id")));
            updatedUser.setUsername(data.get("username"));
            updatedUser.setEmail(data.get("email"));
            updatedUser.setPassword(data.get("password"));

            for (Integer id : ScenarioContext.getUserIds()) {

                response = RequestSpecFactory.jsonRequest()
                        .pathParam("id", id)
                        .body(updatedUser)   // ✅ SEND BODY
                        .put(Endpoints.get_singleuser);

                response.then().statusCode(200).log().all();
            }
        }


    }

    @Then("I have to get success response after update")
    public void iHaveToGetSuccessResponseAfterUpdate() {
        response.then().log().all();
    }

    @Given("I have base URL for delete")
    public void iHaveBaseURLForDelete() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I have to delete request")
    public void iHaveToDeleteRequest() {

        for(Integer deleteid : ScenarioContext.getUserIds()){
        response = RequestSpecFactory.jsonRequest().pathParam("id",deleteid)
                .delete(Endpoints.get_singleuser);
    }}

    @Then("I have to get success response after delete")
    public void iHaveToGetSuccessResponseAfterDelete() {
        response.then().log().all();
    }
}