package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс страницы личного аккаунта
public class PersonalAccountPage extends BasePage {

    // URL страницы личного аккаунта
    private final static String PERSONAL_ACCOUNT_PAGE_URL = getMainPageUrl() + "account/profile";

    // Локатор поля c именем пользователя
    private static final By NAME_FIELD = By.xpath(".//label[text()='Имя']/parent::*/input");
    // Локатор поля с логином пользователя
    private static final By LOGIN_FIELD = By.xpath(".//label[text()='Логин']/parent::*/input");
    // Локатор кнопки выхода из личного кабинета
    private static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");

    // Конструктор
    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    // Геттер возвращает значение URL страницы личного аккаунта
    public static String getPersonalAccountPageUrl() {
        return PERSONAL_ACCOUNT_PAGE_URL;
    }

    // Геттер возвращает локатор поля с именем пользователя
    public By getNameFieldLocator() {
        return NAME_FIELD;
    }

    // Геттер возвращает локатор поля с логином пользователя
    public By getLoginFieldLocator() {
        return LOGIN_FIELD;
    }

    // Геттер возвращает локатор кнопки выхода из личного кабинета
    public By getExitButtonLocator() {
        return EXIT_BUTTON;
    }

    // Метод возвращает значение атрибута "value" у элемента с данным локатором
    public String checkAttributeValue(By locator) {
        String attributeValue = driver.findElement(locator).getAttribute("value");

        System.out.println(driver.findElement(locator).getAttribute("value"));

        return attributeValue;
    }

}
