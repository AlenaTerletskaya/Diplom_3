package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import page_objects.LoginPage;
import page_objects.MainPage;
import page_objects.RegistrationPage;

import static page_objects.BasePage.getMainPageUrl;
import static page_objects.HeaderPage.getPersonalAccountButton;
import static page_objects.LoginPage.getLoginPageUrl;
import static page_objects.MainPage.getEnterAccountButtonLocator;
import static page_objects.OrderFeedPage.getOrderFeedPageUrl;
import static page_objects.PasswordRecoveryPage.getEnterLinkLocatorOnPswdPage;
import static page_objects.PasswordRecoveryPage.getPasswordRecoveryPageUrl;
import static page_objects.RegistrationPage.getEnterLinkLocatorOnRgstrPage;
import static page_objects.RegistrationPage.getRegistrationPageUrl;

// Параметризованный класс для тестирования входа в аккаунт
@RunWith(Parameterized.class)
public class LogInTest extends BaseTest {

    private String url; // URL страницы в параметрах
    private By locator; // Локатор кнопки/ссылки в параметрах
    private User user; // Зарегистрированный пользователь
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    // Конструктор
    public LogInTest(String url, By locator) {
        this.url = url;
        this.locator = locator;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                // 0) Вход по кнопке «Войти в аккаунт» на главной странице
                {getMainPageUrl(), getEnterAccountButtonLocator()},
                // 1) Вход через кнопку «Личный кабинет» на главной странице
                {getMainPageUrl(), getPersonalAccountButton()},
                // 2) Вход через кнопку «Личный кабинет» на странице ленты заказов
                {getOrderFeedPageUrl(), getPersonalAccountButton()},
                // 3) Вход через ссылку "Войти" на странице регистрации
                {getRegistrationPageUrl(), getEnterLinkLocatorOnRgstrPage()},
                // 4) Вход через ссылку "Войти" на странице восстановления пароля
                {getPasswordRecoveryPageUrl(), getEnterLinkLocatorOnPswdPage()}
        };
    }

    @Before
    public void start() {
        super.implicitlyWait(10); // Неявное ожидание

        registrationPage = new RegistrationPage(driver); // Создаем экземпляр страницы регистрации
        user = registrationPage.registerNewUser(); // Регистрируем нового пользователя
    }

    @Test
    @Tag("Positive")
    @DisplayName("A registered user can log in")
    @Description("Проверяет, что зарегистрированный пользователь может войти в аккаунт: " +
            "по кнопке «Войти в аккаунт» на главной странице; " +
            "по кнопке «Личный кабинет» на странице главной / ленты заказов; " +
            "через ссылку \"Войти\" на странице регистрации / восстановления пароля.")
    public void checkRegisteredUserCanLogIn() {

        // Создаем экземпляры страниц:
        mainPage = new MainPage(driver); //  главной
        loginPage = new LoginPage(driver); // логина

        // Переходим на страницу логина по кнопке/ссылке на странице, указанным в параметрах
        mainPage.open(url); // Открываем страницу с URL из параметров
        mainPage.clickField(locator); // Кликаем по кнопке c локатором из параметров

        loginPage.waitPageLoad(getLoginPageUrl()); // Явное ожидание загрузки страницы логина
        // Проверяем, что перешли на страницу логина
        Assert.assertEquals("The application should go to the login page.",
                loginPage.getLoginPageUrl(), driver.getCurrentUrl());

        // Логинимся под зарегистрированным пользователем
        loginPage.registeredUserLogIn(user);

        // Проверяем, что перешли на главную страницу
        Assert.assertEquals("After successful authorization, the application should go to the main page.",
                getMainPageUrl(), driver.getCurrentUrl());

        // Проверяем, что после входа в аккаунт на главной странице отображается кнопка "Оформить заказ".
        Assert.assertTrue("After successful authorization the button \"Оформить заказ\" should be visible.",
                mainPage.isElementVisible(mainPage.getOrderButtonLocator()));
    }
}
