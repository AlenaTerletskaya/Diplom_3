package page_objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс заголовка страницы
public class HeaderPage extends BasePage {

    // Локатор кнопки входа в личный кабинет
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");
    // Локатор логотипа
    private static final By LOGO = By.cssSelector("div.AppHeader_header__logo__2D0X2");
    // Локатор кнопки "Конструктор"
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");


    // Конструктор класса
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    // Геттер возвращает локатор кнопки входа в личный кабинет
    public static By getPersonalAccountButton() {
        return PERSONAL_ACCOUNT_BUTTON;
    }

    // Геттер возвращает локатор логотипа
    public static By getLogo() {
        return LOGO;
    }

    // Геттер возвращает локатор кнопки "Конструктор"
    public static By getConstructorButton() {
        return CONSTRUCTOR_BUTTON;
    }
}
