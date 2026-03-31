package pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateBugTest extends BaseTest {

    @Test
    void createBugTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

        taskSearchPage.getTaskStatus();

        CreateBug createBug = new CreateBug(driver);
        createBug.executeWorkflow();

        Thread.sleep(2000);

        WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(@class, 'jira-issue-status-lozenge-done')]")
        ));
        String actualStatus = statusElement.getText();
        Assertions.assertEquals("ГОТОВО", actualStatus);
    }
}
