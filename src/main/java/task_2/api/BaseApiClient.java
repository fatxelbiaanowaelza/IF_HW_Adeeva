package task_2.api;


import io.restassured.RestAssured;

public abstract class BaseApiClient {

    protected static final String BASE_URL = "http://localhost:8080";
    static {
        RestAssured.baseURI = BASE_URL;
    }
}