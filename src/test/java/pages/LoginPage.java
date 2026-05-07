package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import user.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    final SelenideElement userInput = $(By.id("email"));
    final SelenideElement passInput = $(By.id("password"));
    final SelenideElement submitButton = $x("//*[@type='submit']");
    final SelenideElement lucideEye = $x("//*[@class = 'lucide lucide-eye tw-cursor-pointer']");
    final SelenideElement submitButtonColor = $x("//*[@type='submit']");
    final SelenideElement pageTitle = $x("//*[text() = 'Подтверждение почты']");
    final SelenideElement linkForgotPass = $x("//*[text() = 'Забыли пароль?']");
    final SelenideElement pageTitleFogotPass = $x("//*[text() = 'Восстановить пароль']");
    final SelenideElement linkRegistration = $x("//*[text() = 'Регистрация']");
    final SelenideElement pageTitleRegistration = $x("//*[text() = 'Создайте свой аккаунт']");
    final SelenideElement errorMsg = $x("//div[@data-state='delayed-open']");

    @Step("Открытие сайта")
    public LoginPage openPage() {
        open("login");
        return this;
    }

    @Step("Авторизация пользователем User")
    public LoginPage login(User user) {
        fillInLoginField(user.getLogin());
        fillInPassField(user.getPassword());
        lucideEye.click();
        submitButtonColor.shouldHave(cssValue("background-color", "rgba(15, 23, 42, 1)"));
        submitButton.click();
        pageTitle.shouldHave(text("Подтверждение почты"), Duration.ofSeconds(10));

        return this;
    }

    @Step("Негативные проверки входа")
    public LoginPage fillLoginForm(User user) {
        userInput.click();
        fillInLoginField(user.getLogin());
        passInput.click();
        fillInPassField(user.getPassword());

        return this;
    }

    @Step("Ввести логин: {login}")
    public LoginPage fillInLoginField(String login) {
        userInput.setValue(login);

        return this;
    }

    @Step("Ввести пароль")
    public LoginPage fillInPassField(String password) {
        passInput.setValue(password);

        return this;
    }

    @Step("Получить текст ошибки")
    public LoginPage checkErrorMsg(String expectedText) {
        actions().moveToElement(submitButton).moveByOffset(1, 1).perform();
        errorMsg.shouldBe(visible, Duration.ofSeconds(6))
                .shouldHave(text(expectedText));

        return this;
    }

    public LoginPage linkCheckPass() {
        open("login");
        linkForgotPass.click();
        pageTitleFogotPass.shouldHave(text("Восстановить пароль"), Duration.ofSeconds(6));
        return this;
    }

    public LoginPage registration() {
        open("login");
        linkRegistration.click();
        pageTitleRegistration.shouldBe(visible);
        return this;
    }
}
