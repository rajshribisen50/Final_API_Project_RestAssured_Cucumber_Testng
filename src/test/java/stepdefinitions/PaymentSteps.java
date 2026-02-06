package stepdefinitions;

import config.ConfigReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import pojo.PostReqPojo;
import pojo.postResPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class PaymentSteps {

    private Response response;
    private postResPojo postResponse;

    @Given("I have the base URL set for postsID")
    public void set_base_url() {
        RestAssured.baseURI = ConfigReader.get("baseUrl");
    }

    @When("I GET the post with ID {int}")
    public void get_post_by_id(int id) {
        response = given()
                .pathParam("id", id)
                .when()
                .get("/posts/{id}");

        postResponse = response.as(postResPojo.class);
    }

    @Then("I should receive status code {int}")
    public void validate_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain postId {int}")
    public void validate_post_id(int id) {
        assertThat(postResponse.getId(), equalTo(id));
    }

    @When("I create a post with userId {int}, title {string}, body {string}")
    public void create_post(int userId, String title, String body) {
        PostReqPojo request = new PostReqPojo(userId, title, body);

        response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/posts");

        postResponse = response.as(postResPojo.class);
    }

    @Then("the response should contain title {string}")
    public void validate_post_title(String title) {
        assertThat(postResponse.getTitle(), equalTo(title));
    }

    @Given("I have the base URL set for posts")
    public void iHaveTheBaseURLSetForPosts() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}
