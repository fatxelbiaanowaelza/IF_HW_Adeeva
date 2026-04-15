package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.DashboardPage;

public class DashboardSteps {

    private final DashboardPage dashboardPage = new DashboardPage();

    @Когда("^я перехожу в проект '(.*)'$")
    public void goToProject(String projectName) {
        dashboardPage.goToTestProject();
        System.out.println("Переход в проект: " + projectName);
    }

    @Тогда("^я вижу страницу проекта '(.*)'$")
    public void verifyProjectPage(String projectName) {
        System.out.println(" Страница проекта '" + projectName + "' открыта");
    }
}