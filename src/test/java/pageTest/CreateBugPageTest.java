package pageTest;

import hooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.CreateBugPage;
import page.DashboardPage;
import page.LoginPage;
import page.TaskSearchPage;
import util.TestConstants;

public class CreateBugPageTest extends WebHooks {

    @Test
    @DisplayName("Полный сценарий: создание задачи и перевод в статус ГОТОВО")
    void fullScenarioTest() {

        LoginPage loginPage = new LoginPage();
        loginPage.login(TestConstants.USERNAME, TestConstants.PASSWORD);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickTestProject();

        TaskSearchPage taskSearchPage = new TaskSearchPage();
        taskSearchPage.searchTask(TestConstants.TASK_NAME);

        CreateBugPage createBug = new CreateBugPage();
        createBug.createBug(
                TestConstants.BUG_NAME,
                TestConstants.BUG_DESCRIPTION,
                TestConstants.ENVIRONMENT,
                TestConstants.SEVERITY
        );
        createBug.moveTaskToDone();
    }
}
