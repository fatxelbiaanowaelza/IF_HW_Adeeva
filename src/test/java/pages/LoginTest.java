package pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoginTest extends BaseTest {

    @Test
    void loginTest() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login("AT6", "Qwerty123");
        Assertions.assertTrue(driver.getCurrentUrl().contains("Dashboard"));
    }
}