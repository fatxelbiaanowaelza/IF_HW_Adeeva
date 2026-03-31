package pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DashboardPageTest extends BaseTest {
    @Test
    void loginAndGoToProjectTest() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login("AT6", "Qwerty123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goToTestProject();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(driver.getCurrentUrl().contains("TEST"));
    }
}
