package pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskSearchPageTest extends BaseTest {

    @Test
    void taskVerificationTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("AT6", "Qwerty123");

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.goToTestProject();

        IssuesPage issuesPage = new IssuesPage(driver);

        issuesPage.createIssueTask(
                "Test issue",
                "Проверка корректности счётчика"
        );
        TaskSearchPage taskSearchPage = new TaskSearchPage(driver);
        taskSearchPage.searchAndPrepareTask("TestSeleniumATHomework");

        String actualStatus = taskSearchPage.getTaskStatus();
        String expectedStatus = "СДЕЛАТЬ";
        Assertions.assertEquals(expectedStatus, actualStatus,
                String.format(" Статус задачи '%s' не соответствует ожидаемому '%s'", actualStatus, expectedStatus));

        String actualVersion = taskSearchPage.getTaskVersion();
        Assertions.assertEquals("Version 2.0", actualVersion,
                "Версия в поле 'Исправить в версиях' не соответствует ожидаемой");
    }
}
