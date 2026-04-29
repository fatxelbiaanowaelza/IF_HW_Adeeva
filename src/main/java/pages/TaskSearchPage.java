package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;

public class TaskSearchPage {

    private final SelenideElement findLink = $x("//a[@id='find_link']").as("кнопка 'Задачи и фильтры'");
    private final SelenideElement newSearchLink = $x("//a[@id='issues_new_search_link_lnk']").as("ссылка 'Поиск задач'");
    private final SelenideElement searcherQuery = $x("//input[@id='searcher-query']").as("Поле 'Содержит текст'");
    private final SelenideElement searchButton = $x("//button[@class='aui-button aui-button-primary search-button' and contains(text(),'Поиск')]").as("кнопка 'Поиск'");
    private final SelenideElement statusElement = $x("//span[@id='status-val']").as("Статус задачи");
    private final SelenideElement version = $x("//span[@id='fixVersions-field']").as("Исправить в версиях");

    @Step("Поиск задачи: {taskName}")
    public void searchTask(String taskName) {
        findLink.shouldBe(visible).click();
        newSearchLink.shouldBe(visible).click();
        searcherQuery.setValue(taskName);
        searchButton.click();
        refresh();
    }

    @Step("Получить статус задачи")
    public String getTaskStatus() {
        return statusElement.shouldBe(visible).getText();
    }

    @Step("Получить версию исправления")
    public String getFixVersion() {
        return version.shouldBe(visible, Duration.ofSeconds(25)).getText();
    }
}