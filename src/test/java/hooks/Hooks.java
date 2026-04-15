package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp() {
        log.info("Открываем браузер");

        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 25000;

        Selenide.open(ConfProperties.getProperty("base.url"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        log.info("Закрываем браузер");
        Selenide.closeWebDriver();
    }
}
