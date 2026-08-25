package org.example;

import org.example.pages.PaymentDetailsPage;
import org.example.pages.CardDetailsPage;
import org.example.pages.TopUpPage; // ⬅️ ВАЖНО: Добавь этот импорт!
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotNull;

public class TopUpPageTest {

    private WebDriver driver;

    // ✅ ЭТАП ИНИЦИАЛИЗАЦИИ: Запускается ПЕРЕД каждым тестом
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe"); // ⚠️ Замени на реальный путь
        driver = new ChromeDriver();
        driver.get("http://your-site-url.com"); // ⚠️ Замени на реальный URL страницы пополнения
    }

    // ✅ ТВОЙ СТАРЫЙ ТЕСТ (немного упрощён, теперь использует поле driver)
    @Test
    public void testTopUpFlow() {
        PaymentDetailsPage paymentPage = new PaymentDetailsPage(driver);
        paymentPage.enterAmount("500");
        paymentPage.clickContinue();

        String summary = paymentPage.getSummaryAmountText();
        assertNotNull(summary, "Сумма не отобразилась");

        CardDetailsPage cardPage = new CardDetailsPage(driver);
        cardPage.enterCardNumber("4111111111111111");
        cardPage.enterExpiryDate("12/25");
        cardPage.enterCvv("123");

        String iconsText = cardPage.getPaymentIconsText();
        assertNotNull(iconsText, "Иконки платежных систем не найдены");


    }

    // ✅ НОВЫЙ ТЕСТ: Сюда вставляем твою логику проверки выбора типа платежа
    @Test
    public void shouldSelectPaymentType() {
        // Создаём объект страницы
        TopUpPage topUpPage = new TopUpPage(driver);

        // Вызываем метод выбора типа платежа
        topUpPage.selectPaymentType("Internet");

        // Вызываем метод получения плейсхолдера
        String placeholder = topUpPage.getPlaceholderText("amount");

        // Проверка: результат не должен быть null
        assertNotNull(placeholder, "Плейсхолдер не должен быть null");
    }
}
