package page_objects;

import data_generator.UserGenerator;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы регистрации
public class RegistrationPage extends BasePage{

    // URL страницы регистрации
    private final static String REGISTRATION_PAGE_URL = getMainPageUrl() + "register";

    // Локатор поля ввода имени
    private static final By NAME_FIELD = By.xpath(".//label[text()='Имя']/parent::*/input");
    // Локатор поля ввода емейла
    private static final By EMAIL_FIELD = By.xpath(".//label[text()='Email']/parent::*/input");
    // Локатор поля ввода пароля
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");
    // Локатор кнопки регистрации
    private static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    // Локатор ошибки при вводе некорректного пароля
    private static final By PASSWORD_ERROR = By.xpath(".//p[text()='Некорректный пароль']");
    // Локатор поля ввода пароля со статусом ошибки (подсвеченного красным)
    private static final By PASSWORD_STATUS_ERROR = By.cssSelector("div.input_status_error");
    // div.input_status_error:has(input[name='Пароль'])
    // Локатор ссылки "Войти"
    private static final By ENTER_LINK = By.cssSelector("[href='/login']");


    // Конструктор класса
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


    // Геттер возвращает значение URL страницы регистрации
    public static String getRegistrationPageUrl() {
        return REGISTRATION_PAGE_URL;
    }

    // Геттер возвращает локатор поля ввода имени
    public By getNameFieldLocator() {
        return NAME_FIELD;
    }

    // Геттер возвращает локатор поля ввода емейла
    public By getEmailFieldLocator() {
        return EMAIL_FIELD;
    }

    // Геттер возвращает локатор поля ввода пароля
    public By getPasswordFieldLocator() {
        return PASSWORD_FIELD;
    }

    // Геттер возвращает локатор кнопки регистрации
    public By getRegisterButtonLocator() {
        return REGISTER_BUTTON;
    }

    // Геттер возвращает локатор ошибки при вводе некорректного пароля
    public By getPasswordError() {
        return PASSWORD_ERROR;
    }

    // Геттер возвращает локатор поля ввода пароля со статусом ошибки (подсвеченного красным)
    public By getPasswordStatusError() {
        return PASSWORD_STATUS_ERROR;
    }

    // Геттер возвращает локатор ссылки "Войти"
    public static By getEnterLinkLocatorOnRgstrPage() {
        return ENTER_LINK;
    }

    // Метод регистрирует нового пользователя и возвращает его
    @Step("Registering a new user")
    public User registerNewUser() {

        User user = UserGenerator.getUniqueUser(6,16); // Генерируем уникального пользователя
        open(REGISTRATION_PAGE_URL); // Открываем страницу регистрации

        // Регистрируем пользователя:
        enterData(getNameFieldLocator(), user.getName()); // Вводим имя
        enterData(getEmailFieldLocator(), user.getEmail()); // Вводим емейл
        enterData(getPasswordFieldLocator(), user.getPassword()); // Вводим пароль
        clickField(getRegisterButtonLocator()); // Кликаем по кнопке регистрации

        return user;
    }
}
