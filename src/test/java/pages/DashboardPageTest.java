package pages;

import org.junit.jupiter.api.Test;
import util.TestConstants;

public class DashboardPageTest extends BaseTest {

    @Test
    void loginAndGoToProjectTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.goToTestProject();
    }
}
