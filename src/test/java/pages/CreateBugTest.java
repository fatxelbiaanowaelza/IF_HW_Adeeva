package pages;

import org.junit.jupiter.api.Test;
import util.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateBugTest extends BaseTest {
    @Test
    void createBugTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.goToTestProject();

        IssuesPage issuesPage = new IssuesPage();
        issuesPage.openAllIssues();

        TaskSearchPage taskSearchPage = new TaskSearchPage();
        taskSearchPage.searchTask(TestConstants.TASK_NAME);

        CreateBug createBug = new CreateBug();
        createBug.createBug(
                TestConstants.BUG_NAME,
                TestConstants.BUG_DESCRIPTION,
                TestConstants.ENVIRONMENT,
                TestConstants.SEVERITY
        );
        String status = createBug.getTaskStatus();
        assertEquals(TestConstants.EXPECTED_STATUS, status);
    }
}
