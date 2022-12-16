package tests;

import data_generator.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_objects.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static page_objects.RegistrationPage.getRegistrationPageUrl;

// Класс для тестирования регистрации пользователя c некорректным паролем
public class RegistrationInvalidPswdTest extends BaseTest {

    private RegistrationPage registrationPage;

    @Before
    public void start() {
        super.implicitlyWait(10);
        registrationPage = new RegistrationPage(driver); // Создаем экземпляр класса страницы регистрации
        registrationPage.open(getRegistrationPageUrl()); // Открываем страницу регистрации в браузере
    }

    @Test
    @Tag("Negative")
    @DisplayName("User can not be registered with a password of less than 6 characters. An error is displayed.")
    @Description("Проверяет, что нельзя зарегистрировать пользователя с паролем менее 6 символов. " +
            "При попытке регистрации поле пароля подсвечивается, выводится ошибка. ")
    public void checkRegisterUserPswdLessSixCharacters_expectError() {
        // Генерируем уникального пользователя с некорректным паролем (5 символов)
        User user = UserGenerator.getUniqueUser(5,6);
        // Регистрируем пользователя:
        registrationPage.enterData(registrationPage.getNameFieldLocator(), user.getName()); // Вводим имя
        registrationPage.enterData(registrationPage.getEmailFieldLocator(), user.getEmail()); // Вводим емейл
        registrationPage.enterData(registrationPage.getPasswordFieldLocator(), user.getPassword()); // Вводим пароль
        registrationPage.clickField(registrationPage.getRegisterButtonLocator()); // Кликаем по кнопке регистрации
        // Проверяем, что отображается ошибка "Некорректный пароль"
        Assert.assertTrue(
                "When entering an invalid password, an error should be displayed: \"Некорректный пароль\"",
                registrationPage.isElementVisible(registrationPage.getPasswordError()));
        // Проверяем, что поле ввода пароля подсвечено красным
        Assert.assertTrue(
               "When entering an invalid password, the password field should be highlighted in red",
                registrationPage.isElementVisible(registrationPage.getPasswordStatusError()));
    }
}
