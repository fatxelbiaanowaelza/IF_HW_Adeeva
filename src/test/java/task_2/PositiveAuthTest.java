package task_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import task_2.steps.AuthSteps;

import java.io.InputStream;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PositiveAuthTest {

    private static AuthSteps authSteps;
    private static Map<String, String> credentials;
    private static String token;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws Exception {
        authSteps = new AuthSteps();
        try (InputStream inputStream = PositiveAuthTest.class.getClassLoader()
                .getResourceAsStream("user.json")) {
            credentials = objectMapper.readValue(inputStream, Map.class);
        }
        System.out.println("Credentials: " + credentials);
    }

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
        System.out.println("Token: " + token);
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