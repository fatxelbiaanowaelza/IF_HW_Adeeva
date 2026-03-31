package pages;

import org.junit.jupiter.api.Test;
import util.TestConstants;

public class LoginTest extends BaseTest {

    @Test
    void loginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);
    }
}