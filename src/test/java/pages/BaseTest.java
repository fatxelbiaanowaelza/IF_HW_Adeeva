package pages;

import com.codeborne.selenide.Configuration;
import config.ConfProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.browser = "chrome";

        Configuration.pageLoadTimeout = 15_000;

    }

    @BeforeEach
    public void initBrowser() {
        open(ConfProperties.getProperty("base.url"));
        webdriver().driver().getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {
        closeWebDriver();
    }
}