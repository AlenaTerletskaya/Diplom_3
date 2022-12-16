package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
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
        // Код для Chrome браузера:
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
/**
        // Код для Яндекс браузера:
        // Нужно создать системную переменную "YANDEX_DRIVER_PATH", в которой указать путь к Chrome драйверу.
        // Например: YANDEX_DRIVER_PATH=C:\Program Files\Webdriver\chromedriver106.exe
        // Версия драйвера должна соответствовать Яндекс браузеру.
        System.setProperty("webdriver.chrome.driver", System.getenv("YANDEX_DRIVER_PATH"));
        ChromeOptions options = new ChromeOptions();
        // Нужно создать системную переменную "YANDEX_BROWSER_PATH", в которой указать путь к Яндекс браузеру.
        // Например: YANDEX_BROWSER_PATH=C:\Users\User\AppData\Local\Yandex\YandexBrowser\Application\browser.exe
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        driver = new ChromeDriver(options);

        Assert.assertNotNull("Create a system variable \"YANDEX_DRIVER_PATH\", " +
                "in which specify the path to the Chrome driver", System.getenv("YANDEX_DRIVER_PATH"));
        Assert.assertNotNull("Create a system variable \"YANDEX_BROWSER_PATH\", " +
                "in which specify the path to Yandex browser", System.getenv("YANDEX_BROWSER_PATH"));
*/
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
