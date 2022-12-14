package page_objects;

import org.openqa.selenium.WebDriver;

// Класс страницы ленты заказов
public class OrderFeedPage extends BasePage {

    // URL страницы ленты заказов
    private final static String ORDER_FEED_PAGE_URL = getMainPageUrl() + "feed";

    // Конструктор класса
    public OrderFeedPage(WebDriver driver) {
        super(driver);
    }

    // Геттер возвращает значение URL страницы ленты заказов
    public static String getOrderFeedPageUrl() {
        return ORDER_FEED_PAGE_URL;
    }
}
