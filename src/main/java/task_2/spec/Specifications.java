package task_2.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.baseURI;

public class Specifications {

    public static RequestSpecification requestSpec(String url) {
        baseURI = url;

        return new RequestSpecBuilder()
                .setContentType("application/json")
                .build();
    }
}