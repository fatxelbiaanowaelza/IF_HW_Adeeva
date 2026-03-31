package pages;

import org.junit.jupiter.api.Test;
import util.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSearchPageTest extends BaseTest {

    @Test
    void taskVerificationTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.goToTestProject();

        IssuesPage issuesPage = new IssuesPage();
        issuesPage.openAllIssues();

        TaskSearchPage taskSearchPage = new TaskSearchPage();
        taskSearchPage.searchTask(TestConstants.TASK_NAME);

        assertEquals(TestConstants.EXPECTED_STATUS, taskSearchPage.getTaskStatus());
        assertTrue(taskSearchPage.isVersionPresent(TestConstants.EXPECTED_VERSION),
                "Версия '" + TestConstants.EXPECTED_VERSION + "' не найдена");
    }
}
