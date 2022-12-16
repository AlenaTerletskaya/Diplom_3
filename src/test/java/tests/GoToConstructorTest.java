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
import page_objects.HeaderPage;
import page_objects.LoginPage;
import page_objects.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static page_objects.BasePage.getMainPageUrl;
import static page_objects.HeaderPage.*;
import static page_objects.LoginPage.getLoginPageUrl;
import static page_objects.PersonalAccountPage.getPersonalAccountPageUrl;

// Параметризованный класс для тестирования перехода в конструктор бургера
@RunWith(Parameterized.class)
public class GoToConstructorTest extends BaseTest {

    private By locator; // Локатор элемента из параметров
    private User user; // Пользователь с данными
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private HeaderPage headerPage;

    // Конструктор
    public GoToConstructorTest(By locator) {
        this.locator = locator;
    }

    @Parameterized.Parameters (name = "Тестовые данные - локатор кнопки: {0}")
    public static Object[][] getData() {
        return new Object[][] {
                {getLogo()}, // Локатор логотипа
                {getConstructorButton()} // Локатор кнопки "Конструктор"
        };
    }

    @Before
    public void start() {
        super.implicitlyWait(10); // Неявное ожидание
        registrationPage = new RegistrationPage(driver); // Создаем экземпляр страницы регистрации
        user = registrationPage.registerNewUser(); // Регистрируем нового пользователя
        loginPage = new LoginPage(driver); // Создаем экземпляр страницы логина
        loginPage.waitPageLoad(getLoginPageUrl()); // Явное ожидание загрузки страницы логина
        loginPage.registeredUserLogIn(user); // Авторизуемся под зарегистрированным пользователем
        // Явное ожидание кликабельности кнопки входа в личный кабинет
        loginPage.waitElementToBeClicable(getPersonalAccountButton());
        loginPage.clickField(getPersonalAccountButton()); // Кликаем по кнопке входа в личный кабинет
        loginPage.waitPageLoad(getPersonalAccountPageUrl()); // Явное ожидание загрузки страницы личного кабинета
    }

    @Test
    @Tag("Positive")
    @DisplayName("User can go to the burger constructor from his personal account")
    @Description("Проверяет, что из личного кабинета можно перейти в конструктор бургеров: " +
            "по клику на кнопку \"Конструктор\" и на логотип \"Stellar Burgers\".")
    public void check() {
        headerPage = new HeaderPage(driver); // Создаем экземпляр заголовка страницы
        loginPage.waitElementToBeClicable(locator); // Явное ожидание кликабельности кнопки
        headerPage.clickField(locator); // Кликаем по кнопке с локатором из параметров
        headerPage.waitPageLoad(getMainPageUrl()); // Явное ожидание загрузки главной страницы
        // Проверяем, что перешли на главную страницу
        Assert.assertEquals("The application should go to the main page.",
                getMainPageUrl(), driver.getCurrentUrl());
    }
}
