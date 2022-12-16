package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_objects.LoginPage;
import page_objects.PersonalAccountPage;
import page_objects.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static page_objects.HeaderPage.getPersonalAccountButton;
import static page_objects.LoginPage.getLoginPageUrl;
import static page_objects.PersonalAccountPage.getPersonalAccountPageUrl;

// Класс для тестирования выхода из аккаунта
public class LogOutTest extends BaseTest {

    private User user; // Пользователь с данными
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private PersonalAccountPage accountPage;

    @Before
    public void start() {
        super.implicitlyWait(10); // Неявное ожидание
        registrationPage = new RegistrationPage(driver); // Создаем экземпляр страницы регистрации
        user = registrationPage.registerNewUser(); // Регистрируем нового пользователя
        loginPage = new LoginPage(driver); // Создаем экземпляр страницы логина
        loginPage.waitPageLoad(getLoginPageUrl()); // Явное ожидание загрузки страницы логина
        loginPage.registeredUserLogIn(user); // Авторизуемся под зарегистрированным пользователем
        loginPage.clickField(getPersonalAccountButton()); // Кликаем по кнопке входа в личный кабинет
        loginPage.waitPageLoad(getPersonalAccountPageUrl()); // Явное ожидание загрузки страницы личного кабинета
    }

    @Test
    @Tag("Positive")
    @DisplayName("An authorized user can log out of his account")
    @Description("Проверяет, что авторизованный пользователь может выйти из своего аккаунта " +
            "по кнопке \"Выйти\" в личном кабинете. Приложение переходит на страницу авторизации.")
    public void checkAuthorizedUserCanLogOut() {
        accountPage = new PersonalAccountPage(driver); // Создаем экземпляр страницы личного кабинета
        accountPage.clickField(accountPage.getExitButtonLocator()); // Нажимаем на кнопку выхода из личного кабинета
        accountPage.waitPageLoad(getLoginPageUrl()); // Явное ожидание загрузки страницы логина
        // Проверяем, что перешли на страницу логина
        Assert.assertEquals("The application should go to the login page.",
                loginPage.getLoginPageUrl(), driver.getCurrentUrl());
    }
}
