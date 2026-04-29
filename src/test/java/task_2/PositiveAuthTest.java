package task_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import task_2.basetest.BaseTest;

import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PositiveAuthTest extends BaseTest {

    private static String token;

    @Test
    @Order(1)
    @DisplayName("1. Регистрация пользователя")
    void testRegistration() {
        authSteps.register(credentials)
                .statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("2. Успешный логин и получение токена")
    void testLoginSuccess() {
        token = authSteps.getTokenAfterLogin(credentials);
        Assertions.assertNotNull(token, "Токен не должен быть null");
    }

    @Test
    @Order(3)
    @DisplayName("3. Успешный выход из учетки")
    void testLogoutSuccess() {
        authSteps.logout(token)
                .statusCode(200)
                .body(equalTo("success logout"));
    }
}