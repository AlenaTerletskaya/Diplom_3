package page_objects;

import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы логина
public class LoginPage extends BasePage {

    // URL страницы логина
    private final static String LOGIN_PAGE_URL = getMainPageUrl() + "login";
    // Локатор ссылки "Зарегистрироваться"
    private static final By REGISTRATION_LINK = By.cssSelector("[href='/register']");
    // Локатор кнопки "Войти"
    private static final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
    // Локатор поля ввода емейла
    private static final By EMAIL_FIELD = By.xpath(".//label[text()='Email']/parent::*/input");
    // Локатор поля ввода пароля
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");

    // Конструктор класса
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Геттер возвращает значение URL страницы логина
    public static String getLoginPageUrl() {
        return LOGIN_PAGE_URL;
    }

    // Геттер возвращает локатор ссылки "Зарегистрироваться"
    public By getRegistrationLink() {
        return REGISTRATION_LINK;
    }

    // Геттер возвращает локатор кнопки "Войти"
    public By getEnterButton() {
        return ENTER_BUTTON;
    }

    // Геттер возвращает локатор поля ввода емейла
    public By getEmailFieldLocator() {
        return EMAIL_FIELD;
    }

    // Геттер возвращает локатор поля ввода пароля
    public By getPasswordFieldLocator() {
        return PASSWORD_FIELD;
    }

    // Метод авторизует зарегистрированного пользователя на странице логина
    @Step("Authorizing a registered user on the login page")
    public void registeredUserLogIn(User registeredUser) {
        enterData(getEmailFieldLocator(), registeredUser.getEmail()); // Вводим емейл
        enterData(getPasswordFieldLocator(), registeredUser.getPassword()); // Вводим пароль
        clickField(getEnterButton()); // Кликаем по кнопке входа
        waitPageLoad(getMainPageUrl()); // Явное ожидание загрузки главной страницы
    }
}
