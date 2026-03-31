package pages;

import org.junit.jupiter.api.Test;
import util.TestConstants;

import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssuePageTest extends BaseTest {

    @Test
    void loginAndGoToProjectTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.goToTestProject();

        IssuesPage issuesPage = new IssuesPage();
        issuesPage.openAllIssues();

        int beforeTotal = issuesPage.getTotalNumber();
        issuesPage.createIssue(TestConstants.ISSUE_NAME);
        refresh();
        int afterTotal = issuesPage.getTotalNumber();

        assertEquals(beforeTotal + 1, afterTotal);
    }
}
