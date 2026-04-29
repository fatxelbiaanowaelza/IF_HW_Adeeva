package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssuePageTest extends WebHooks {

    @Test
    @DisplayName("Проверка увеличения счетчика задач при создании новой задачи")
    void loginAndGoToProjectTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickTestProject();

        IssuesPage issuesPage = new IssuesPage();
        issuesPage.openAllIssues();


        int beforeTotal = issuesPage.getTotalNumber();
        issuesPage.createIssue(TestConstants.ISSUE_NAME);
        int afterTotal = issuesPage.getTotalNumber();

        assertEquals(beforeTotal + 1, afterTotal);
    }
}
