package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static util.TestConstants.STATUS_DONE;
import static util.TestConstants.STATUS_IN_PROGRESS;

public class CreateBugPage {

    private final SelenideElement createTask = $x("//a[@id='create_link']").as("Кнопка 'Создать' (кнопка для открытия формы создания задачи)");
    private final SelenideElement typeTask = $x("//input[@id='issuetype-field']").as("Поле 'Тип задачи'");
    private final SelenideElement nameTask = $x("//input[@id='summary']").as("Поле 'Тема'");
    private final SelenideElement visualButton = $x("//button[@class='aui-button' and text()='Визуальный']").as("Кнопка 'Визуальный'");
    private final SelenideElement iframe = $x("//div[@id='description-wiki-edit']//iframe[contains(@id,'mce_')]").as("Iframe для поля 'Описание'");
    private final SelenideElement bodyIframe = $x("//body[@id='tinymce']").as("Поле описания' ");
    private final SelenideElement versionSelect = $x("//*[@id='fixVersions']").as("Выпадающий список версий");
    private final SelenideElement priorityField = $x("//*[@id='priority-field']").as("Поле 'Приоритет'");
    private final SelenideElement envField = $x("//*[@id='environment-field']");
    private final SelenideElement severityField = $x("//*[@id='customfield_10400']").as("Поле 'Серьезность'");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']").as("Кнопка 'Создать' (внутри создание задачи)");
    private final SelenideElement workButton = $x("//*[@id='action_id_21']").as("Кнопка 'В работе'");
    private final SelenideElement taskButton = $x("//a[@id='find_link']").as("Кнопка 'Задачи'");
    private final SelenideElement businessProcess = $x("//a[@id='opsbar-transitions_more']").as("Кнопка 'Бизнес-процесс'");
    private final SelenideElement doneButton = $x("//aui-item-link[@id='action_id_31']").as("Кнопка 'Выполнено'");
    private final SelenideElement status = $x("//span[@id='status-val']").as("Статус задачи");
    private final SelenideElement myOpenTasks = $x("//a[@id='filter_lnk_my_lnk']").as("мои открытые задачи");

    @Step("Создание бага")
    public void createBug(String name, String description, String environment, String severity) {

        createTask.shouldBe(visible).click();
        typeTask.shouldBe(visible).setValue("Bug").pressEnter();
        nameTask.shouldBe(visible).setValue(name);

        if (visualButton.exists()) {
            visualButton.click();
        }

        switchTo().frame(iframe);
        bodyIframe.shouldBe(visible).setValue(description);
        switchTo().defaultContent();
        versionSelect.click();
        priorityField.click();

        if (envField.exists()) {
            envField.click();
            envField.selectOptionContainingText(environment);
        }

        severityField.click();
        severityField.selectOptionContainingText(severity);
        createButton.shouldBe(visible).click();
    }

    @Step("Перевести задачу в статус 'ГОТОВО'")
    public void moveTaskToDone() {
        taskButton.shouldBe(visible).click();
        myOpenTasks.shouldBe(visible).click();
        businessProcess.shouldBe(visible).click();
        workButton.shouldBe(visible).click();
        status.shouldHave(text(STATUS_IN_PROGRESS), Duration.ofSeconds(10));
        businessProcess.shouldBe(visible).click();
        doneButton.shouldBe(visible).click();
        status.shouldHave(text(STATUS_DONE), Duration.ofSeconds(10));
    }
}

