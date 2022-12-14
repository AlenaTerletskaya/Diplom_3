package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page_objects.HeaderPage;
import page_objects.LoginPage;
import page_objects.PersonalAccountPage;
import page_objects.RegistrationPage;

import static page_objects.BasePage.getMainPageUrl;
import static page_objects.HeaderPage.getPersonalAccountButton;
import static page_objects.LoginPage.getLoginPageUrl;
import static page_objects.OrderFeedPage.getOrderFeedPageUrl;
import static page_objects.PersonalAccountPage.getPersonalAccountPageUrl;

// Параметризованный класс для тестирования перехода в личный кабинет
@RunWith(Parameterized.class)
public class GoToPersonalAccountTest extends BaseTest {

    private String url; // URL страницы в параметрах
    private User user; // Пользователь с данными
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private PersonalAccountPage accountPage;
    private HeaderPage headerPage;

    // Конструктор
    public GoToPersonalAccountTest(String url) {
        this.url = url;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {getMainPageUrl()}, // URL главной страницы
                {getOrderFeedPageUrl()} // URL страницы ленты заказов
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
    }

    @After
    public void finish() {
        accountPage.clickField(accountPage.getExitButtonLocator()); // Нажимаем на кнопку выхода из личного кабинета
    }

    @Test
    @Tag("Positive")
    @DisplayName("An authorized user can go to the personal account")
    @Description("Проверяет, что авторизованный пользователь может войти в личный кабинет: " +
            "по кнопке «Личный кабинет» на странице главной / ленты заказов.")
    public void checkAuthorizedUserCanGoToPersonalAccount() {

        headerPage = new HeaderPage(driver); // Создаем экземпляр заголовка страницы

        // Переходим на страницу, указанную в параметрах
        headerPage.open(url);

        headerPage.clickField(getPersonalAccountButton()); // Кликаем по кнопке входа в личный кабинет
        headerPage.waitPageLoad(getPersonalAccountPageUrl()); // Явное ожидание загрузки страницы личного кабинета

        accountPage = new PersonalAccountPage(driver); // Создаем экземпляр страницы личного кабинета
        // Получаем значение из поля "Имя"
        String name = accountPage.checkAttributeValue(accountPage.getNameFieldLocator());
        // Проверяем, что имя совпадает с именем пользователя
        Assert.assertEquals("The value in the \"Имя\" field must match the user name.",
                user.getName(), name);

        // Получаем значение из поля "Логин"
        String login = accountPage.checkAttributeValue(accountPage.getLoginFieldLocator());
        // Проверяем, что логин совпадает с емейлом пользователя
        Assert.assertEquals("The value in the \"Логин\" field must match the user email.",
                user.getEmail(), login);
    }

}
