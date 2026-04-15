package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.TaskSearchPage;
import util.TestConstants;

public class TaskSearchSteps {

    private final TaskSearchPage taskSearchPage = new TaskSearchPage();

    @Когда("^я выполняю поиск задачи 'TestSeleniumATHomework'$")
    public void searchTask() {
        taskSearchPage.searchTask(TestConstants.TASK_NAME);
        System.out.println("Поиск задачи: " + TestConstants.TASK_NAME);
    }

    @Тогда("^статус задачи 'СДЕЛАТЬ'$")
    public void verifyTaskStatus() {
        String actualStatus = taskSearchPage.getTaskStatus();
        assert TestConstants.EXPECTED_STATUS.equals(actualStatus) :
                "Статус: ожидалось " + TestConstants.EXPECTED_STATUS + ", получено " + actualStatus;
        System.out.println("Статус: " + actualStatus);
    }

    @Тогда("^присутствует версия 'Version 2.0'$")
    public void verifyVersionPresent() {
        assert taskSearchPage.isVersionPresent(TestConstants.EXPECTED_VERSION) :
                "Версия '" + TestConstants.EXPECTED_VERSION + "' не найдена";
        System.out.println("Версия: " + TestConstants.EXPECTED_VERSION);
    }
}