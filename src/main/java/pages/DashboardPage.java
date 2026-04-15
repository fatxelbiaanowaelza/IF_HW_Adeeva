package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement projectsMenu = $x("//a[@id='browse_link']").as("Меню 'Проекты'");
    private final SelenideElement testProject = $x("//a[@id='admin_main_proj_link_lnk']").as("Проект Test(TEST)");

    public void goToTestProject() {
        projectsMenu.shouldBe(interactable).click();
        testProject.shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
