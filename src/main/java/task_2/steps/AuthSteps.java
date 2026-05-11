package task_2.steps;

import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import task_2.api.AuthApiClient;
import task_2.dto.UserDto;

public class AuthSteps {

    private final AuthApiClient api;

    public AuthSteps(AuthApiClient api) {
        this.api = api;
    }

    public ValidatableResponse register(UserDto user, String urn) {
        return api.postUserByUrn(user, urn)
                .log().all();
    }

    public ValidatableResponse login(UserDto user, String urn) {
        return api.postUserByUrn(user, urn)
                .log().all();
    }

    public ValidatableResponse logout(String token, String urn) {
        return api.logout(token, urn)
                .log().all();
    }

    public String loginAndExtractToken(UserDto user, String urn) {
        Response response = api.postUserByUrn(user, urn)
                .log().all()
                .extract()
                .response();

        String body = response.asString();

        if (body.contains("token :")) {
            String token = body.split("token :")[1].trim();
            return token;
        }

        throw new RuntimeException("Не удалось извлечь token из ответа:\n" + body);
    }

    public LoginResult loginWithToken(UserDto user, String urn) {
        Response response = api.postUserByUrn(user, urn)
                .log().all()
                .extract()
                .response();

        String body = response.asString();
        String token = null;

        if (body.contains("token :")) {
            token = body.split("token :")[1].trim();
        }

        return new LoginResult(response.then(), token);
    }

    public static class LoginResult {
        public final ValidatableResponse response;
        public final String token;

        public LoginResult(ValidatableResponse response, String token) {
            this.response = response;
            this.token = token;
        }
    }
}