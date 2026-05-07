package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

@Owner("Марина Парфенова")
public class BaseTest {

    LoginPage loginPage;

    @Step("Открытие браузера")
    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 6000;
        Configuration.baseUrl = "https://app.skyrexio.com/";

        loginPage = new LoginPage();
    }

    @Step("Закрытие браузера")
    @AfterMethod
    public void close() {
        clearBrowserCache();
        Selenide.closeWebDriver();
    }
}
