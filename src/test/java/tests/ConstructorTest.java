package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import page_objects.MainPage;

import static page_objects.BasePage.getMainPageUrl;
import static page_objects.MainPage.*;

// Параметризованный класс для тестирования конструктора бургеров
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    private String xpathButton; // xpath локатора кнопки для клика
    private String xpathPreviousButton; // xpath локатора предыдущей кнопки, на которой была подсветка
    private MainPage mainPage;

    // Конструктор
    public ConstructorTest(String xpathButton, String xpathPreviousButton) {
        this.xpathButton = xpathButton;
        this.xpathPreviousButton = xpathPreviousButton;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                // Проверяем переход к разделу "Булки"
                {getBunsButtonXpath(), getSaucesButtonXpath()}, // xpath кнопки "Булки",  xpath кнопки "Соусы"
                // Проверяем переход к разделу "Соусы"
                {getSaucesButtonXpath(), getBunsButtonXpath()}, // xpath кнопки "Соусы", xpath кнопки "Булки"
                // Проверяем переход к разделу "Начинки"
                {getFillingsButtonXpath(), getBunsButtonXpath()} // xpath кнопки "Начинки", xpath кнопки "Булки"
        };
    }

    @Before
    public void start() {
        super.implicitlyWait(10); // Неявное ожидание

        mainPage = new MainPage(driver); // Создаем экземпляр гланой страницы
        mainPage.open(getMainPageUrl()); // Открываем главную страницу
    }

    @Test
    @Tag("Positive")
    @DisplayName("In the \"Constructor\" section there are transitions to the sections: " +
            "\"Buns\", \"Sauces\", \"Fillings\"")
    @Description("Проверяет, что в разделе \"Конструктор\" работают переходы к разделам: " +
            "\"Булки\", \"Соусы\", \"Начинки\".")
    public void checkInConstructorUserCanGoToSections() {

        // Если первый xpath из параметров соответствует кнопке "Булки",
        if (xpathButton.equals(getBunsButtonXpath())) {
            // Явное ожидание кликабельности кнопки "Соусы"
            mainPage.waitElementToBeClicable(By.xpath(xpathPreviousButton));
            // кликаем по кнопке "Соусы", чтобы освободить раздел "Булки" для перехода к нему.
            mainPage.clickField(By.xpath(xpathPreviousButton));
            // Явное ожидание, пока подсветка кнопки "Булки" перестанет отображаться
            mainPage.waitElementToBeInvisible(mainPage.getIlluminationLocator(xpathButton));
        }

        // Явное ожидание кликабельности кнопки из параметров
        mainPage.waitElementToBeClicable(By.xpath(xpathButton));
        mainPage.clickField(By.xpath(xpathButton)); // Кликаем по кнопке с xpath из параметров

        // Явное ожидание отображения подсветки под нажатой кнопкой
        mainPage.waitElementToBeVisible(mainPage.getIlluminationLocator(xpathButton));
        // Явное ожидание, пока подсветка предыдущей кнопки перестанет отображаться
        mainPage.waitElementToBeInvisible(mainPage.getIlluminationLocator(xpathPreviousButton));

        // Проверяем, что под нажатой кнопкой появилась подсветка
        Assert.assertEquals("A backlight should appear under the clicked button.",
                driver.findElement(mainPage.getIlluminationLocator(xpathButton)),
                mainPage.getParentElement(By.xpath(xpathButton)));

        // Проверяем, что под предыдущей нажатой кнопкой нет подсветки
        String notExpectedAttr = "tab_tab_type_current__2BEPc";
        String attribute = mainPage.getParentElement(By.xpath(xpathPreviousButton)).getAttribute("class");
        Assert.assertFalse("There should be no backlight under the previous menu button.",
                attribute.contains(notExpectedAttr));
    }
}
