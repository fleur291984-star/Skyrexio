package tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static user.UserFactory.*;

@Epic("Авторизация")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {
    final SelenideElement pageTitle = $x("//*[text() = 'Подтверждение почты']");

    @Story("Успешный вход в систему")
    @Severity(CRITICAL)
    @Test
    public void projectOpen() {
        loginPage
                .openPage()
                .login(withValidData());
        pageTitle.shouldHave(text("Подтверждение почты"), Duration.ofSeconds(6));
    }

    @Story("Валидация полей при входе")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "incorrectData")
    public void checkIncorrectLogin(User user, String errorMsg) {
        loginPage
                .openPage()
                .fillLoginForm(user)
                .checkErrorMsg(errorMsg);
    }

    @DataProvider(name = "incorrectData")
    public Object[][] fillLoginForm() {
        return new Object[][]{
                {withIncorrectEmailUser(), "Некорректный формат e-mail"},
                {withEmptyLogin(), "Введите e-mail"},
                {withEmptyPassword(), "Введите пароль"},
        };
    }

    @Story("Проверка работы ссылки Забыли пароль?")
    @Severity(CRITICAL)
    @Test
    public void linkClick() {
        loginPage
                .openPage()
                .linkCheckPass();
    }

    @Story("Проверка работы ссылки Регистрация")
    @Severity(CRITICAL)
    @Test
    public void linkRegistrationClick() {
        loginPage
                .openPage()
                .registration();
    }
}
