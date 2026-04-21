package task_2.steps;

import io.restassured.response.ValidatableResponse;
import task_2.api.AuthApiClient;
import java.util.Map;

public class AuthSteps {

    private final AuthApiClient authApi = new AuthApiClient();

    public ValidatableResponse register(Map<String, String> credentials) {
        return authApi.postRegister(credentials);
    }

    public ValidatableResponse login(Map<String, String> credentials) {
        return authApi.postLogin(credentials);
    }

    public ValidatableResponse logout(String token) {
        return authApi.postLogout(token);
    }

    public String getTokenAfterLogin(Map<String, String> credentials) {
        String response = authApi.postLogin(credentials)
                .extract()
                .asString();
        if (response.contains("token :")) {
            return response.split(":")[1].trim();
        }
        return response;
    }
}