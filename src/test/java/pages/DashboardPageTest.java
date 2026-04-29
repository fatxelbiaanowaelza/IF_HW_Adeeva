package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestConstants;

public class DashboardPageTest extends WebHooks {

    @Test
    @DisplayName("Открытие проекта Test")
    void loginAndGoToProjectTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickTestProject();
    }
}
