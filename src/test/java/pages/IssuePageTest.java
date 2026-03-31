package pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IssuePageTest extends BaseTest {

    @Test
    void issueCountTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("AT6", "Qwerty123");

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.goToTestProject();

        IssuesPage issuesPage = new IssuesPage(driver);

        issuesPage.openAllIssues();

        int oldCount = issuesPage.getTasksCount();

        issuesPage.createIssue("Test issue", "Проверка корректности счётчика");

        issuesPage.refresh();

        int newCount = issuesPage.getTasksCount();

        Assertions.assertEquals(oldCount + 1, newCount,
                "Количество задач не увеличилось на 1!");
    }
}