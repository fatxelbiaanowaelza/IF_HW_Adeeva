package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']").as("поле для ввода логина");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("поле для ввода пароля");
    private final SelenideElement loginButton = $x("//input[@id='login']").as("кнопка авторизации");

    public void login(String username, String password) {
        loginInput.shouldBe(visible).setValue(username);
        passwordInput.shouldBe(visible).setValue(password);
        loginButton.shouldBe(visible, enabled).click();
    }
}
