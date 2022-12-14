package tests;

import data_generator.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page_objects.*;

import static page_objects.BasePage.getMainPageUrl;
import static page_objects.LoginPage.getLoginPageUrl;
import static page_objects.RegistrationPage.getRegistrationPageUrl;

// Параметризованный класс для тестирования успешной регистрации пользователя
@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {
    private MainPage mainPage;
    private HeaderPage headerPage;
    private LoginPage loginPage;

    private int minLengthPswd;
    private int maxLengthPswdNotInclsv;

    public RegistrationTest(int minLengthPswd, int maxLengthPswdNotInclsv) {
        this.minLengthPswd = minLengthPswd;
        this.maxLengthPswdNotInclsv = maxLengthPswdNotInclsv;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {6, 7}, // Пароль минимальной длины 6 символов
                {30, 31}, // Пароль большой длины  - 30 символов
                {7, 30} // Пароль произвольной длины от 7 до 29 символов
        };
    }

    @Before
    public void start() {
        super.implicitlyWait(10);
        headerPage = new HeaderPage(driver); // Создаем экземпляр класса заголовка страницы
        headerPage.open(getMainPageUrl()); // Открываем главную страницу в браузере
        headerPage.clickField(headerPage.getPersonalAccountButton()); // Кликаем по кнопке входа в личный кабинет

        loginPage = new LoginPage(driver); // Создаем экземпляр класса страницы логина
        loginPage.waitPageLoad(getLoginPageUrl()); // Явное ожидание загрузки страницы логина
        loginPage.clickField(loginPage.getRegistrationLink()); // Кликаем по ссылке "Зарегистрироваться"

        loginPage.waitPageLoad(getRegistrationPageUrl()); // Явное ожидание загрузки страницы регистрации
    }

    @Test
    @Tag("Positive")
    @DisplayName("Unique user can be registered")
    @Description("Проверяет, что можно зарегистрировать уникального пользователя. " +
            "При успешной регистрации приложение переходит на страницу логина. " +
            "Под зарегистрированным пользователем можно авторизоваться.")
    public void checkUniqueUserCanBeRegistered() {

        // Создаем экземпляр класса страницы регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Генерируем уникального пользователя
        User user = UserGenerator.getUniqueUser(minLengthPswd,maxLengthPswdNotInclsv);
        // Регистрируем пользователя:
        registrationPage.enterData(registrationPage.getNameFieldLocator(), user.getName()); // Вводим имя
        registrationPage.enterData(registrationPage.getEmailFieldLocator(), user.getEmail()); // Вводим емейл
        registrationPage.enterData(registrationPage.getPasswordFieldLocator(), user.getPassword()); // Вводим пароль
        registrationPage.clickField(registrationPage.getRegisterButtonLocator()); // Кликаем по кнопке регистрации

        // Явное ожидание загрузки страницы логина
        loginPage.waitPageLoad(getLoginPageUrl());
        // Проверяем, что перешли на страницу логина
        Assert.assertEquals("After successful registration, the application should go to the login page.",
                getLoginPageUrl(), driver.getCurrentUrl());

        loginPage.registeredUserLogIn(user); // Авторизуемся под зарегистрированным пользователем

        // Проверяем, что перешли на главную страницу
        Assert.assertEquals("After successful authorization, the application should go to the main page.",
                getMainPageUrl(), driver.getCurrentUrl());

        mainPage = new MainPage(driver); // Создаем экземпляр класса главной страницы
        // Проверяем, что после входа в аккаунт на главной странице отображается кнопка "Оформить заказ".
        Assert.assertTrue("After successful authorization the button \"Оформить заказ\" should be visible.",
                mainPage.isElementVisible(mainPage.getOrderButtonLocator()));
    }
}
