package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Класс главной страницы MainPage
public class MainPage extends BasePage {

    // Локатор кнопки "Оформить заказ"
    private static final By ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");
    // Локатор кнопки "Войти в аккаунт"
    private static final By ENTER_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    // xpath для локатора кнопки "Булки"
    private static final String BUNS_BUTTON_XPATH = ".//span[text()='Булки']";
    // xpath для локатора кнопки "Соусы"
    private static final String SAUCES_BUTTON_XPATH = ".//span[text()='Соусы']";
    // xpath для локатора кнопки "Начинки"
    private static final String FILLINGS_BUTTON_XPATH = ".//span[text()='Начинки']";
    // Часть xpath для локатора подсветки по кнопкой
    private static final String ILLUMINATION_XPATH =
            "/parent::div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']";

    // Конструктор класса
    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Геттер возвращает локатор кнопки "Оформить заказ"
    public By getOrderButtonLocator() {
        return ORDER_BUTTON;
    }

    // Геттер возвращает локатор кнопки "Войти в аккаунт"
    public static By getEnterAccountButtonLocator() {
        return ENTER_ACCOUNT_BUTTON;
    }

    // Геттер возвращает xpath для локатора кнопки "Булки"
    public static String getBunsButtonXpath() {
        return BUNS_BUTTON_XPATH;
    }

    // Геттер возвращает xpath для локатора кнопки "Соусы"
    public static String getSaucesButtonXpath() {
        return SAUCES_BUTTON_XPATH;
    }

    // Геттер возвращает xpath для локатора кнопки "Начинки"
    public static String getFillingsButtonXpath() {
        return FILLINGS_BUTTON_XPATH;
    }

    // Метод возвращает родительский элемент для элемента с данным локатором
    public WebElement getParentElement(By locator) {
        WebElement element = driver.findElement(locator);
        WebElement parent = element.findElement(By.xpath("./.."));
        return parent;
    }

    // Метод возвращает локатор подсветки по xpath кнопки, которая подсвечивается
    public By getIlluminationLocator(String xpathButtonString) {
        return By.xpath(xpathButtonString + ILLUMINATION_XPATH);
    }
}
