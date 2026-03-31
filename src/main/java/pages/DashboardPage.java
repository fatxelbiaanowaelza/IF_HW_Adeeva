package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;
    private By projectsMenu = By.id("browse_link");
    private By testProject = By.id("admin_main_proj_link_lnk");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToTestProject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        WebElement projectsMenuElement = wait.until(
                ExpectedConditions.elementToBeClickable(projectsMenu)
        );
        projectsMenuElement.click();

        WebElement testProjectElement = wait.until(
                ExpectedConditions.elementToBeClickable(testProject)
        );

        actions.moveToElement(testProjectElement).perform();

        testProjectElement.click();
    }
}