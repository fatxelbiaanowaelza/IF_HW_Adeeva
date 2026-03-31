package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssuesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By allIssuesLink = By.xpath("//a[contains(text(),'Посмотреть все задачи и фильтры')]");
    private By allTasksLink = By.xpath("//a[@class='filter-link' and contains(text(),'Все задачи')]");
    private By counterLocator = By.xpath("//div[@class='showing']/span");
    private By createLink = By.id("create_link");
    private By summaryField = By.id("summary");
    private By iframe = By.id("mce_0_ifr");
    private By descriptionBody = By.id("tinymce");
    private By createIssueBtn = By.id("create-issue-submit");

    public IssuesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openAllIssues() {
        wait.until(ExpectedConditions.elementToBeClickable(allIssuesLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(allTasksLink)).click();
    }

    public int getTasksCount() {
        WebElement counterSpan = wait.until(
                ExpectedConditions.visibilityOfElementLocated(counterLocator)
        );

        String text = counterSpan.getText();
        return Integer.parseInt(text.split("из")[1].trim());
    }

    public void createIssue(String summaryText, String descriptionText) {

        wait.until(ExpectedConditions.elementToBeClickable(createLink)).click();

        WebElement summary = wait.until(
                ExpectedConditions.visibilityOfElementLocated(summaryField));
        summary.sendKeys(summaryText);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));

        WebElement body = wait.until(
                ExpectedConditions.visibilityOfElementLocated(descriptionBody));
        body.clear();
        body.sendKeys(descriptionText);

        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.elementToBeClickable(createIssueBtn)).click();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void createIssueTask(String summaryText, String descriptionText) {
        openAllIssues();
        getTasksCount();
        createIssue(summaryText, descriptionText);
        refresh();
        getTasksCount();
    }
}
