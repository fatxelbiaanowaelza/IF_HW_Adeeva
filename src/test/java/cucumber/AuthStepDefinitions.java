package cucumber;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import task_2.api.AuthApiClient;
import task_2.context.AuthContext;
import task_2.dto.UserDto;
import task_2.steps.AuthSteps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static task_2.constants.AuthCredentials.*;
import static task_2.constants.AuthUrn.*;

public class AuthStepDefinitions {

    private final AuthContext context = new AuthContext();
    private final AuthSteps authSteps = new AuthSteps(new AuthApiClient());

    @Дано("email пользователя {string}")
    @Step("Установка email: {0}")
    public void userEmail(String email) {

        context.email = switch (email) {
            case TEST_EMAIL_KEY -> TEST_EMAIL;
            case FAKE_USER_KEY -> FAKE_USER;
            default -> email;
        };

        Allure.addAttachment(EMAIL, context.email);
    }

    @И("пароль пользователя {password}")
    @Step("Установка пароля")
    public void userPassword(MaskedString maskedPassword) {

        String rawPassword = maskedPassword.getValue();

        context.password = switch (rawPassword) {
            case TEST_PASSWORD_KEY -> TEST_PASSWORD;
            case WRONG_PASSWORD_KEY -> WRONG_PASSWORD;
            default -> rawPassword;
        };

        Allure.step(MASKED_PASSWORD);
    }

    @Дано("невалидный токен {string}")
    @Step("Установка невалидного токена: {0}")
    public void invalidToken(String invalidToken) {

        context.token = switch (invalidToken) {
            case INVALID_TOKEN_KEY -> INVALID_TOKEN;
            default -> invalidToken;
        };

        Allure.addAttachment(TOKEN, MASKED_TOKEN);
    }

    @Дано("авторизованный пользователь")
    @Step("Подготовка авторизованного пользователя")
    public void authorizedUser() {

        context.email = TEST_EMAIL;
        context.password = TEST_PASSWORD;

        context.user = new UserDto(context.email, context.password);

        Allure.step("Регистрация пользователя", () -> {
            context.response = authSteps.register(context.user, REGISTER.getValue());
        });

        Allure.step("Логин и получение токена", () -> {
            context.token = authSteps.loginAndExtractToken(context.user, LOGIN.getValue());
            Allure.addAttachment(TOKEN, context.token);
        });

        assertNotNull(context.token, TOKEN_NULL_MESSAGE);
        assertFalse(context.token.isBlank(), TOKEN_EMPTY_MESSAGE);
    }

    @Когда("пользователь отправляет запрос на регистрацию")
    @Step("Отправка POST запроса на регистрацию")
    public void userSendsRegistrationRequest() {

        context.user = new UserDto(context.email, context.password);
        context.response = authSteps.register(context.user, REGISTER.getValue());

        String responseBody = context.response.extract().body().asString();
        Allure.addAttachment(RESPONSE_BODY, maskPasswordInResponse(responseBody));
    }

    @Когда("пользователь отправляет запрос на логин")
    @Step("Отправка POST запроса на логин")
    public void userSendsLoginRequest() {

        context.user = new UserDto(context.email, context.password);

        Allure.addAttachment(LOGIN_EMAIL, context.email);
        Allure.addAttachment(LOGIN_PASSWORD, HIDDEN_PASSWORD);

        AuthSteps.LoginResult result =
                authSteps.loginWithToken(context.user, LOGIN.getValue());

        context.response = result.response;
        context.token = result.token;

        String responseBody = context.response.extract().body().asString();
        Allure.addAttachment(RESPONSE_BODY, maskPasswordInResponse(responseBody));

        if (context.token != null) {
            Allure.addAttachment(EXTRACTED_TOKEN, context.token);
        }
    }

    @Когда("пользователь отправляет запрос на logout")
    @Step("Отправка POST запроса на logout")
    public void userSendsLogoutRequest() {

        Allure.addAttachment(USED_TOKEN, context.token);

        context.response = authSteps.logout(context.token, LOGOUT.getValue());

        String responseBody = context.response.extract().body().asString();
        Allure.addAttachment(RESPONSE_BODY, responseBody);
    }

    @Когда("пользователь отправляет запрос на logout с текущим токеном")
    @Step("Отправка POST запроса на logout с указанным токеном")
    public void userSendsLogoutRequestWithCurrentToken() {

        Allure.addAttachment(USED_TOKEN, context.token);

        context.response = authSteps.logout(context.token, LOGOUT.getValue());

        String responseBody = context.response.extract().body().asString();
        Allure.addAttachment(RESPONSE_BODY, responseBody);
    }

    @Тогда("статус код ответа должен быть {int}")
    @Step("Проверка статус кода: {0}")
    public void responseStatusCodeShouldBe(int statusCode) {
        context.response.statusCode(statusCode);
    }

    @Тогда("должен вернуться токен")
    @Step("Проверка получения токена")
    public void tokenShouldBeReturned() {

        assertNotNull(context.token, TOKEN_NULL_MESSAGE);
        assertFalse(context.token.isBlank(), TOKEN_EMPTY_MESSAGE);

        Allure.addAttachment(RECEIVED_TOKEN, context.token);
    }

    private String maskPasswordInResponse(String responseBody) {

        if (responseBody == null) {
            return null;
        }

        return responseBody.replaceAll(
                "(?i)(\"password\"\\s*:\\s*\")([^\"]*)(\")",
                "$1***$3"
        );
    }
}