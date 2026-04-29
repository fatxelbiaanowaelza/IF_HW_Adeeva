package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestConstants;

public class LoginTest extends WebHooks {

    @Test
    @DisplayName("Авторизация пользователя")
    void loginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);
    }
}