package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.SetValueOptions.withText;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']").as("поле для ввода логина");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("поле для ввода пароля");
    private final SelenideElement loginButton = $x("//input[@id='login']").as("кнопка авторизации");

    @Step("Ввести логин {username}")
    public void enterUsername(String username) {
        loginInput.shouldBe(visible)
                .setValue(withText(username).sensitive());
    }

    @Step("Ввести пароль")
    public void enterPassword(@Param(mode = Parameter.Mode.MASKED) String password) {
        passwordInput.shouldBe(visible).setValue(withText(password).sensitive());
    }

    @Step("Нажать кнопку входа")
    public void clickLoginButton() {
        loginButton.shouldBe(visible, enabled).click();
    }

    @Step("Проверить, что пользователь авторизован")
    public void verifyLoginSuccess() {
        loginButton.shouldNotBe(visible);
    }

    @Step("Выполнить вход")
    public void login(String username,
                      @Param(mode = Parameter.Mode.MASKED) String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        verifyLoginSuccess();
    }
}

