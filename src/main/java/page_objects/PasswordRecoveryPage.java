package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы восстановления пароля
public class PasswordRecoveryPage extends BasePage {

    // URL страницы восстановления пароля
    private final static String PASSWORD_RECOVERY_PAGE_URL = getMainPageUrl() + "forgot-password";
    // Локатор ссылки "Войти"
    private static final By ENTER_LINK = By.cssSelector("[href='/login']");

    // Конструктор класса
    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    // Геттер возвращает значение URL страницы восстановления пароля
    public static String getPasswordRecoveryPageUrl() {
        return PASSWORD_RECOVERY_PAGE_URL;
    }

    // Геттер возвращает локатор ссылки "Войти"
    public static By getEnterLinkLocatorOnPswdPage() {
        return ENTER_LINK;
    }
}
