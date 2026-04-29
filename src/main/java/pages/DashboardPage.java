package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement projectsMenu = $x("//a[@id='browse_link']").as("Меню 'Проекты'");
    private final SelenideElement testProject = $x("//a[@id='admin_main_proj_link_lnk']").as("Проект Test(TEST)");

    @Step("Открыть меню 'Проекты'")
    public void openProjectsMenu() {
        projectsMenu.shouldBe(visible, enabled).click();
    }

    @Step("Нажать на проект 'Test'")
    public void openTestProject() {
        testProject.shouldBe(visible, enabled).click();
    }

    @Step("Перейти в проект Test")
    public void clickTestProject() {
        openProjectsMenu();
        openTestProject();
    }
}