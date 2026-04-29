package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSearchPageTest extends WebHooks {

    @Test
    @DisplayName("Проверка статуса задачи TestSeleniumATHomework")
    void taskVerificationTest() {

        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickTestProject();

        TaskSearchPage taskSearchPage = new TaskSearchPage();
        taskSearchPage.searchTask(TestConstants.TASK_NAME);

        assertEquals(
                TestConstants.TASK_STATUS_TODO.trim(),
                taskSearchPage.getTaskStatus().trim()
        );
        assertEquals(
                TestConstants.EXPECTED_VERSION.trim(),
                taskSearchPage.getFixVersion().trim()
        );
    }
}
