package steps;

import io.cucumber.java.ru.Когда;
import pages.LoginPage;
import config.ConfProperties;


public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Когда("я ввожу логин и пароль")
    public void enterLoginAndPassword() {
        String login = ConfProperties.getProperty("username");
        String password = ConfProperties.getProperty("password");
        loginPage.login(login, password);
    }
}