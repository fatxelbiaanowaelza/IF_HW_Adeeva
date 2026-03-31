package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static util.TestConstants.ERROR;
import static util.TestConstants.EXPECTED_STATUS;

public class CreateBug {

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

    public void createBug(String taskName, String description, String environment, String severity) {
        refresh();
        createTask.shouldBe(visible, enabled).click();
        typeTask.shouldBe(visible, enabled).click();
        typeTask.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        typeTask.setValue(ERROR);
        typeTask.shouldHave(value(ERROR));
        typeTask.pressEnter();
        nameTask.shouldBe(visible, enabled).setValue(taskName);
        nameTask.shouldHave(value(taskName));
        visualButton.shouldBe(visible, enabled).click();
        switchTo().frame(iframe);
        bodyIframe.shouldBe(visible).setValue(description);
        bodyIframe.shouldHave(text(description));
        switchTo().defaultContent();
        versionSelect.shouldBe(visible, enabled).click();
        priorityField.shouldBe(visible).click();

        if (envField.exists()) {
            envField.shouldBe(visible).click();
            $x("//option[contains(text(),'" + environment + "')]").shouldBe(visible).click();
        }

        severityField.shouldBe(visible).click();
        $x("//option[contains(text(),'" + severity + "')]").shouldBe(visible).click();
    }

    public String getTaskStatus() {
        createButton.shouldBe(visible, enabled).click();
        workButton.shouldBe(visible, enabled).click();
        taskButton.shouldBe(Condition.visible).click();
        myOpenTasks.shouldBe(Condition.visible).click();
        businessProcess.shouldBe(Condition.visible).click();
        doneButton.shouldBe(Condition.visible).click();
        return status.shouldHave(text(EXPECTED_STATUS), Duration.ofSeconds(10)).getText();
    }
}
