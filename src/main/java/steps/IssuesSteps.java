package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import pages.IssuesPage;
import util.TestConstants;

import static com.codeborne.selenide.Selenide.refresh;

public class IssuesSteps {

    private final IssuesPage issuesPage = new IssuesPage();
    private int countBefore;

    @Когда("^я создаю задачу с названием '(.*)'$")
    public void createIssue(String summary) {
        issuesPage.openAllIssues();

        countBefore = issuesPage.getTotalNumber();
        System.out.println(" Количество задач до создания: " + countBefore);

        issuesPage.createIssue(summary);
        System.out.println(" Создана задача: " + summary);
    }

    @Когда("я обновляю страницу")
    public void refreshPage() {
        refresh();
        System.out.println(" Страница обновлена");
    }

    @Тогда("количество задач увеличилось и больше {int}")
    public void verifyCountIncreased(int minCount) {
        int actualCount = issuesPage.getTotalNumber();
        System.out.println(" Количество задач после обновления: " + actualCount);
        System.out.println(" Было: " + countBefore + ", Стало: " + actualCount);

        assert actualCount == countBefore + 1 :
                "Ожидалось увеличение на 1, но было " + countBefore + ", стало " + actualCount;

        assert actualCount > minCount :
                "Количество задач (" + actualCount + ") не больше " + minCount;

        System.out.println(" " + TestConstants.ISSUE_NAME + " создана! Всего задач: " + actualCount);
    }
}