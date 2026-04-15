package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TaskSearchPage {
    private final SelenideElement findLink = $x("//a[@id='find_link']").as("кнопка 'Задачи и фильтры'");
    private final SelenideElement newSearchLink = $x("//a[@id='issues_new_search_link_lnk']").as("ссылка 'Поиск задач'");
    private final SelenideElement searcherQuery = $x("//input[@id='searcher-query']").as("Поле 'Содержит текст'");
    private final SelenideElement taskLink = $x("//*[@data-id='-4']").as("Ссылка 'Все задачи'");
    private final SelenideElement searchButton = $x("//button[@class='aui-button aui-button-primary search-button' and contains(text(),'Поиск')]").as("кнопка 'Поиск'");
    private final SelenideElement statusElement = $x("//span[contains(@class,'jira-issue-status-lozenge')]").as("статус задачи");
    private final SelenideElement fixVersions = $x("//span[@id='fixfor-val']//a[contains(text(),'Version 2.0')]").as("версия задачи");

    public void searchTask(String taskName) {
        findLink.shouldBe(visible, enabled).click();
        newSearchLink.shouldBe(visible, enabled).click();
        searcherQuery.shouldBe(visible).setValue(taskName);
        searchButton.shouldBe(visible, enabled).click();
        taskLink.shouldBe(visible).click();
    }

    public String getTaskStatus() {
        return statusElement.shouldBe(visible).getText();
    }
    public String getFixVersion() {
        return fixVersions.shouldBe(visible).getText();
    }

    public boolean isVersionPresent(String version) {
        SelenideElement versionLink = $x("//a[contains(text(),'" + version + "')]").as("ссылка версии " + version);
        return versionLink.shouldBe(visible).isDisplayed();
    }
}
