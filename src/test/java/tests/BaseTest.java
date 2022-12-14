package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

// Базовый тестовый класс, в котором настраивается драйвер. Другие тестовые классы наследуются от базового.
public class BaseTest {

    protected WebDriver driver; // Объявляем переменную драйвера

    // Инициализируем драйвер в зависимости от того, где будем тестировать: в Google Chrome или Яндекс браузере.
    @Before
    public void setUp() {


        // Код для Яндекс браузера:
        // Указываем путь к Chrome драйверу той версии, которая соответствует Яндекс браузеру.
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Webdriver\\chromedriver106.exe");
        ChromeOptions options = new ChromeOptions();
        // Указываем путь к Яндекс браузеру
        options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);

        // Код для Chrome браузера:
        // driver = new ChromeDriver();
    }

    @After
    public void cleanUp() {
        // Закрываем сессию драйвера
        driver.quit();
    }

    // Неявное ожидание заданное количество секунд
    public void implicitlyWait(int numberOfSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(numberOfSeconds));
    }

}
