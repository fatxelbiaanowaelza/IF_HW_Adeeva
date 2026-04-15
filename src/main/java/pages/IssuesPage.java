package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class IssuesPage {
    private final SelenideElement tasksLink = $x("//a[contains(text(),'Посмотреть все задачи и фильтры')]").as("Ссылка 'Посмотреть все задачи и фильтры'");
    private final SelenideElement allTasksButton = $x("//a[text()='Все задачи']").as("Кнопка 'Все задачи'");
    private final SelenideElement taskCounter = $x("//span[contains(text(), 'из')]").as("Счетчик задач");
    private final SelenideElement createTask = $x("//a[@id='create_link']").as("Кнопка 'Создать' (кнопка для открытия формы создания задачи)");
    private final SelenideElement nameTask = $x("//input[@id='summary']").as("Поле 'Тема'");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']").as("Кнопка 'Создать' (внутри создание задачи)");

    public void openAllIssues() {
        tasksLink.shouldBe(visible, enabled).click();
        allTasksButton.shouldBe(visible, enabled).click();
    }

    public int getTotalNumber() {
        String text = taskCounter.shouldBe(visible, Duration.ofSeconds(10)).getText();
        return Integer.parseInt(text.split(" из ")[1]);
    }

    public void createIssue(String summary) {
        createTask.shouldBe(visible, enabled).click();
        nameTask.shouldBe(visible).setValue(summary);
        nameTask.shouldHave(value(summary));
        createButton.shouldBe(visible, enabled).click();
        allTasksButton.shouldBe(visible, enabled).click();
    }
}