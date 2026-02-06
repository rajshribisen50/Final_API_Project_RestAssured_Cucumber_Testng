package specs;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    public static RequestSpecification jsonRequest() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("baseUrl"))
                .setAuth(RestAssured.basic(
                        ConfigReader.get("apiKey"),
                        ConfigReader.get("apiSecret")))
                .setContentType(ContentType.JSON)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}
