package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaskSearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By issuesButton = By.id("find_link");
    private By searchIssuesLink = By.id("issues_new_search_link_lnk");
    private By searchInput = By.id("searcher-query");
    private By searchButton = By.xpath("//button[@class='aui-button aui-button-primary search-button' and contains(text(),'Поиск')]");
    private By statusElement = By.xpath("//span[contains(@class,'jira-issue-status-lozenge')]");
    private By versionLink = By.xpath("//a[text()='Version 2.0']");

    public TaskSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchAndPrepareTask(String taskName) {
        wait.until(ExpectedConditions.elementToBeClickable(issuesButton)).click();
        sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(searchIssuesLink)).click();
        sleep(1000);

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(taskName);
        sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        sleep(1000);
    }

    public String getTaskStatus() {
        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(statusElement));
        return status.getText().trim();
    }

    public String getTaskVersion() {
        WebElement version = wait.until(ExpectedConditions.visibilityOfElementLocated(versionLink));
        return version.getText().trim();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}