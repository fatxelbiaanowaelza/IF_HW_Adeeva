package task_2.api;

import io.restassured.response.ValidatableResponse;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AuthApiClient extends BaseApiClient {

    private static final String REGISTER_URL = "/api/register";
    private static final String LOGIN_URL = "/api/login";
    private static final String LOGOUT_URL = "/api/logout";

    public ValidatableResponse postRegister(Map<String, String> credentials) {
        return given()
                .body(credentials)
                .when()
                .post(REGISTER_URL)
                .then();
    }

    public ValidatableResponse postLogin(Map<String, String> credentials) {
        return given()
                .body(credentials)
                .when()
                .post(LOGIN_URL)
                .then();
    }

    public ValidatableResponse postLogout(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(LOGOUT_URL)
                .then();
    }
}