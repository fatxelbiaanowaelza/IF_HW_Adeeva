package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;

public class IssuesPage {
    private final SelenideElement tasksLink = $x("//a[contains(text(),'Посмотреть все задачи и фильтры')]").as("Ссылка 'Посмотреть все задачи и фильтры'");
    private final SelenideElement allTasksButton = $x("//a[text()='Все задачи']").as("Кнопка 'Все задачи'");
    private final SelenideElement taskCounter = $x("//div[@class='showing']/span").as("Счетчик задач");
    private final SelenideElement createTask = $x("//a[@id='create_link']").as("Кнопка 'Создать' (кнопка для открытия формы создания задачи)");
    private final SelenideElement nameTask = $x("//input[@id='summary']").as("Поле 'Тема'");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']").as("Кнопка 'Создать' (внутри создание задачи)");

    @Step("Открыть список всех задач")
    public void openAllIssues() {
        tasksLink.shouldBe(visible, enabled).click();
        allTasksButton.shouldBe(visible, enabled).click();
    }

    @Step("Получить общее количество задач")
    public int getTotalNumber() {
        String text = taskCounter.shouldBe(visible, Duration.ofSeconds(20)).getText();
        return Integer.parseInt(text.split(" из ")[1]);
    }

    @Step("Открыть форму создания задачи")
    public void openCreateIssueForm() {
        createTask.shouldBe(visible, enabled).click();
    }

    @Step("Ввести название задачи: {summary}")
    public void enterIssueSummary(String summary) {
        nameTask.shouldBe(visible).setValue(summary);
    }

    @Step("Подтвердить создание задачи")
    public void submitIssue() {
        createButton.shouldBe(visible, enabled).click();
    }

    @Step("Создать задачу с названием: {summary}")
    public void createIssue(String summary) {
        openCreateIssueForm();
        enterIssueSummary(summary);
        submitIssue();

        allTasksButton.shouldBe(visible, enabled).click();
        refresh();
    }
}