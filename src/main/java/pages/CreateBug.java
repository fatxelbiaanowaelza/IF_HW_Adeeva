package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateBug {

    private WebDriver driver;
    private WebDriverWait wait;

    public CreateBug(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void executeWorkflow() {
        sleep(2000);

        WebElement issuesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("find_link")));
        issuesButton.click();
        sleep(2000);

        WebElement searchIssuesLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("issues_new_search_link_lnk")));
        searchIssuesLink.click();
        sleep(2000);

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searcher-query")));
        searchInput.clear();
        searchInput.sendKeys("TestSeleniumATHomework");
        sleep(2000);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='aui-button aui-button-primary search-button' and contains(text(),'Поиск')]")
        ));
        searchButton.click();
        sleep(2000);

        WebElement createIssueLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        createIssueLink.click();
        sleep(2000);

        WebElement issueTypeField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("issuetype-field")));
        issueTypeField.click();
        sleep(500);
        issueTypeField.sendKeys("Ошибка");
        issueTypeField.sendKeys(Keys.ENTER);
        sleep(500);

        WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        String uniqueSummary = "Проверка отсутствия функционала отображения пароля на странице авторизации ";
        summary.sendKeys(uniqueSummary);
        sleep(2000);

        WebElement visualTabDescription = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[text()='Визуальный']")));
        if (!"true".equals(visualTabDescription.getAttribute("aria-pressed"))) {
            visualTabDescription.click();
            sleep(500);
        }

        WebElement descriptionIframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[text()='Описание']/following-sibling::div//iframe")));
        driver.switchTo().frame(descriptionIframe);

        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        body.clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = arguments[1];", body,
                "При вводе пароля на странице авторизации Jira отсутствует иконка («глаз»), позволяющая пользователю временно увидеть вводимые символы. Это снижает удобство использования и увеличивает вероятность ошибки при вводе пароля.<br><br>" +
                        "Шаги проверки:<br>" +
                        "1. Открыть страницу авторизации Jira.<br>" +
                        "2. Перейти к полю ввода пароля.<br>" +
                        "3. Начать ввод пароля.<br><br>" +
                        "<strong>Фактический результат:</strong><br>" +
                        "Поле пароля скрывает вводимые символы. Иконка для отображения пароля отсутствует.<br><br>" +
                        "<strong>Ожидаемый результат:</strong><br>" +
                        "В поле ввода пароля присутствует иконка «глаз», при нажатии на которую пароль становится видимым."
        );
        driver.switchTo().defaultContent();
        sleep(3000);

        fillEnvironment();
        fillFixVersions();
        fillPriority();
        fillLabels();
        fillSeverity();
        submitIssue();
        updateStatuses();
    }

    private void fillEnvironment() {
        WebElement environmentVisualTab = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[text()='Окружение']/following-sibling::div//button[text()='Визуальный']")));
        if (!"true".equals(environmentVisualTab.getAttribute("aria-pressed"))) {
            environmentVisualTab.click();
            sleep(2000);
        }

        WebElement environmentIframe = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[text()='Окружение']/following-sibling::div//iframe")));
        driver.switchTo().frame(environmentIframe);

        WebElement environmentBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        environmentBody.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = arguments[1];", environmentBody,
                "Браузер: Google Chrome<br>" +
                        "ОС: Windows 11"
        );
        driver.switchTo().defaultContent();
    }

    private void fillFixVersions() {
        WebElement fixVersionsField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fixVersions")));
        Select select = new Select(fixVersionsField);
        select.selectByValue("10001");
    }

    private void fillPriority() {
        WebElement priorityField = wait.until(ExpectedConditions.elementToBeClickable(By.id("priority-field")));
        priorityField.click();
        WebElement priorityInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("priority-field")));
        priorityInput.click();
        sleep(500);
        priorityInput.clear();
        priorityInput.sendKeys("Medium");
        priorityInput.sendKeys(Keys.ENTER);
    }

    private void fillLabels() {
        WebElement labelsField = wait.until(ExpectedConditions.elementToBeClickable(By.id("labels-textarea")));
        labelsField.click();
        labelsField.sendKeys("bugs");
        labelsField.sendKeys(Keys.ENTER);
    }

    private void fillSeverity() {
        WebElement severityField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customfield_10400")));
        Select selectSeverity = new Select(severityField);
        selectSeverity.selectByVisibleText("S0 Тривиальный/Trivial");
    }

    private void submitIssue() {
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='create-issue-submit' and @value='Создать']")));
        createButton.click();
        sleep(3000);
    }

    private void updateStatuses() {
        try {
            WebElement reportedByMeLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[@data-id='-2']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reportedByMeLink);
            sleep(2000);

            WebElement inProgressStatus = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), 'В работе')]")));
            inProgressStatus.click();
            sleep(1000);

            WebElement businessProcess = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), 'Бизнес-процесс')]")));
            businessProcess.click();

            WebElement completedStatus = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='trigger-label' and contains(text(), 'Исполнено')]")));
            completedStatus.click();
            sleep(1000);

            WebElement completedButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("issue-workflow-transition-submit")));
            completedButton.click();
            sleep(1000);

            WebElement businessProcess1 = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(), 'Бизнес-процесс')]")));
            businessProcess1.click();

            WebElement confirmed = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='trigger-label' and contains(text(), 'Подтверждено')]")));
            confirmed.click();
            sleep(1000);

            WebElement confirmedButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("issue-workflow-transition-submit")));
            confirmedButton.click();
            sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}