package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Родительский класс с базовыми полями и методами, которые наследуют классы других страниц
public class BasePage {

    protected final WebDriver driver; // Поле driver
    // URL главной страницы сайта
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    // Локатор слоев div, которые могут перекрывать другие элементы
    private static final By DIV_MODAL_OVERLAY = By.cssSelector("div.Modal_modal_overlay__x2ZCr");

    // Конструктор класса
    public BasePage(WebDriver driver) {
        this.driver = driver; // Инициализируем поле driver
    }

    // Статический метод возвращает значение URL главной страницы сайта
    public static String getMainPageUrl() {
        return MAIN_PAGE_URL;
    }

    // Метод открывает в браузере страницу с указанным URL
    public void open(String url) {
        driver.get(url);
        waitPageLoad(url);
    }

    // Метод кликает по полю c определенным локатором
    public void clickField(By locator) {
        driver.findElement(locator).click();
    }

    // Метод заполняет поле c определенным локатором данными
    public void enterData(By locator, String data) {
        driver.findElement(locator).sendKeys(data);
    }

    // Метод проверяет отображение элемента с определенным локатором
    public boolean isElementVisible(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    // Геттер возвращает локатор слоев div, которые могут перекрывать другие элементы
    public static By getDivModalOverlay() {
        return DIV_MODAL_OVERLAY;
    }

    // Явное ожидание кликабельности элемента с данным локатором
    public void waitElementToBeClicable(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(elementLocator)));
    }

    // Явное ожидание загрузки страницы и невидимости перекрывающих элементов
    public void waitPageLoad(String url) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(url));
        // Явное ожидание, когда все перекрывающие элементы перестанут отображаться
        waitAllElementToBeInvisible(getDivModalOverlay());
    }

    // Явное ожидание отображения элемента с данным локатором
    public void waitElementToBeVisible(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    // Явное ожидание отображения элемента с данным локатором
    public void waitElementPresence(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    // Явное ожидание, когда элемент с данным локатором перестанет отображаться
    public void waitElementToBeInvisible(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    // Явное ожидание, когда элементы с данным локатором перестанут отображаться
    public void waitAllElementToBeInvisible(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(elementLocator)));
    }
}
