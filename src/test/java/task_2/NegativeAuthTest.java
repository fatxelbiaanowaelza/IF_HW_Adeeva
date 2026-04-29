package task_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task_2.basetest.BaseTest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class NegativeAuthTest extends BaseTest {

    @Test
    @DisplayName("Негатив: Логин с несуществующим пользователем")
    void testLoginUserNotFound() {
        Map<String, String> wrongUser = new HashMap<>();
        wrongUser.put("username", "nonexistent_user_12345");
        wrongUser.put("password", credentials.get("password"));

        authSteps.login(wrongUser)
                .statusCode(401)
                .body(equalTo("not found"));
    }

    @Test
    @DisplayName("Негатив: Логин с неверным паролем")
    void testLoginWrongPassword() {
        Map<String, String> wrongPass = new HashMap<>();
        wrongPass.put("username", credentials.get("username"));
        wrongPass.put("password", "Abcdef456");

        authSteps.login(wrongPass)
                .statusCode(401)
                .body(equalTo("not right pass"));
    }

    @Test
    @DisplayName("Негатив: Выход с неверным токеном")
    void testLogoutInvalidToken() {
        String fakeToken = "df6e0d2c-c0f6-4cb6-bf99-000000000000";

        authSteps.logout(fakeToken)
                .statusCode(401)
                .body(equalTo("not found"));
    }
}