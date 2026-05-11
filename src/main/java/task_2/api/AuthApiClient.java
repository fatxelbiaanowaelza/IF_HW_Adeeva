package task_2.api;

import io.restassured.response.ValidatableResponse;
import task_2.dto.UserDto;

import static io.restassured.RestAssured.given;

public class AuthApiClient extends BaseApiClient {

    public ValidatableResponse postUserByUrn(UserDto user, String urn) {
        return given()
                .baseUri(BASE_URL)
                .basePath(urn)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post()
                .then();
    }

    public ValidatableResponse logout(String token, String urn) {
        return given()
                .baseUri(BASE_URL)
                .basePath(urn)
                .header("Authorization", token)
                .get()
                .then();
    }
}