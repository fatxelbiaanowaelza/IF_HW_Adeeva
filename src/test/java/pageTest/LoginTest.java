package pageTest;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import util.TestConstants;

public class LoginTest extends WebHooks {

    @Test
    @DisplayName("Авторизация пользователя")
    void loginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);
        loginPage.verifyLoginSuccess();
    }
}