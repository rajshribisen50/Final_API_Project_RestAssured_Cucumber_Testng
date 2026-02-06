package tests;


import base.BaseTest;
import constants.Endpoints;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.CreatePaymentRequest;
import pojo.CreatePaymentResponse;
import pojo.PostReqPojo;
import pojo.postResPojo;
import specs.RequestSpecFactory;
import specs.ResponseSpecFactory;
import utils.RetryAnalyzer;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PaymentE2ETest extends BaseTest {
    //PostRequest

    static int postId;
    static final int EXISTING_POST_ID = 1;

    @Test
    public void getExistingPostTest() {

        postResPojo response =
                given()
                        .pathParam("id", EXISTING_POST_ID)
                        .log().all()
                        .when()
                        .get(Endpoints.Get_POST)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(postResPojo.class);

        Assert.assertEquals(response.getId(), EXISTING_POST_ID);
        Assert.assertNotNull(response.getTitle());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getUserId() > 0);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void createPostTest() {

        PostReqPojo request =
                new PostReqPojo(1, "API Automation", "POST request using Rest Assured");

        postResPojo response =
                RequestSpecFactory.jsonRequest()
                        .body(request)
                        .when()
                        .post(Endpoints.AddUser_POST)
                        .then()
                        .spec(ResponseSpecFactory.statusCode(201))
                        .extract()
                        .as(postResPojo.class);

        postId = response.getId();   // ✅ correct
        Assert.assertNotNull(postId);


    }





}