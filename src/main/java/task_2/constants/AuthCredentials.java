package task_2.constants;

import task_2.config.ConfProperties;

public class AuthCredentials {

    public static final String TEST_EMAIL = ConfProperties.getProperty("username");
    public static final String TEST_PASSWORD = ConfProperties.getProperty("password");
    public static final String EMAIL = "Email";
    public static final String MASKED_PASSWORD = "Пароль установлен: *****";
    public static final String TOKEN = "Токен";
    public static final String MASKED_TOKEN = "*****";
    public static final String RESPONSE_BODY = "Тело ответа";
    public static final String LOGIN_EMAIL = "Email для логина";
    public static final String LOGIN_PASSWORD = "Password для логина";
    public static final String EXTRACTED_TOKEN = "Извлеченный токен";
    public static final String USED_TOKEN = "Используемый токен";
    public static final String RECEIVED_TOKEN = "Полученный токен";
    public static final String TOKEN_NULL_MESSAGE = "Токен не должен быть null";
    public static final String TOKEN_EMPTY_MESSAGE = "Токен не должен быть пустым";
    public static final String TEST_EMAIL_KEY = "TEST_EMAIL";
    public static final String FAKE_USER_KEY = "FAKE_USER";
    public static final String TEST_PASSWORD_KEY = "TEST_PASSWORD";
    public static final String WRONG_PASSWORD_KEY = "WRONG_PASSWORD";
    public static final String INVALID_TOKEN_KEY = "INVALID_TOKEN";
    public static final String HIDDEN_PASSWORD = "*****";
    public static final String FAKE_USER = "fake_user_123";
    public static final String WRONG_PASSWORD = "wrong_password_123";
    public static final String INVALID_TOKEN = "00000000-0000-0000-0000-000000000000";
}