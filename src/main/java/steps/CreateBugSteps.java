package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.CreateBug;
import util.TestConstants;

public class CreateBugSteps {

    private CreateBug createBug = new CreateBug();

    @Когда("^я создаю тестовый баг$")
    public void createTestBug() {
        createBug.createBug(
                TestConstants.BUG_NAME,
                TestConstants.BUG_DESCRIPTION,
                TestConstants.ENVIRONMENT,
                TestConstants.SEVERITY
        );
        System.out.println(" Создан тестовый баг: " + TestConstants.BUG_NAME);
    }

    @Тогда("^статус бага соответствует 'ГОТОВО'$")
    public void verifyBugStatus() {
        String actualStatus = createBug.getTaskStatus();
        assert TestConstants.EXPECTED_STATUS.equals(actualStatus) :
                "Ожидался статус: " + TestConstants.EXPECTED_STATUS + ", получен: " + actualStatus;
        System.out.println(" Статус бага: " + actualStatus);
    }
}