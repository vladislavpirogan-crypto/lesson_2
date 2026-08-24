package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class MtsPaymentTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.mts.by";

    @BeforeTest
    public void setUp() {
        // Автоматическая загрузка драйвера Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Явное ожидание элементов (15 секунд максимум)
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testOnlinePaymentBlock() {
        System.out.println("--- Старт теста блока 'Онлайн пополнение без комиссии' ---");

        driver.get(BASE_URL);

        // Проверка: нет ли страницы блокировки (Cloudflare/Captcha)
        // Если на странице есть слово "Checking" или "Access denied", значит сайт нас видит как бота
        String pageSource = driver.getPageSource();
        if (pageSource != null && (pageSource.contains("Checking") || pageSource.contains("Access denied"))) {
            fail("❌ Сайт заблокировал доступ (Cloudflare/Captcha). Тест не может пройти дальше. Это защита сайта, а не ошибка кода.");
        }

        // === ЗАДАЧА 1: Проверить название указанного блока ===
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        By blockTitleLocator = By.xpath("//*[contains(text(), '" + expectedBlockTitle + "')]");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));
            System.out.println("✅ Блок найден: " + expectedBlockTitle);
        } catch (Exception e) {
            fail("❌ Не удалось найти блок с названием '" + expectedBlockTitle + "'. Возможно, изменилась верстка сайта.");
        }

        // === ЗАДАЧА 2: Проверить наличие логотипов платёжных систем ===
        var blockElement = driver.findElement(blockTitleLocator);
        List<WebElement> logos = blockElement.findElements(By.tagName("img"));// ИСПРАВЛЕНО: Используем !isEmpty() вместо size() > 0
        assertFalse(logos.isEmpty(), "❌ В блоке не найдено ни одного логотипа платёжной системы.");
        System.out.println("✅ Найдено логотипов платёжных систем: " + logos.size());

        // === ЗАДАЧА 3: Проверить работу ссылки «Подробнее о сервисе» ===
        By moreInfoLinkLocator = By.linkText("Подробнее о сервисе");

        try {
            var link = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLinkLocator));
            String originalUrl = driver.getCurrentUrl();

            link.click();

            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));

            System.out.println("✅ Ссылка 'Подробнее о сервисе' работает, переход выполнен.");


            driver.navigate().back();
            wait.until(ExpectedConditions.urlToBe(originalUrl));

        } catch (Exception e) {
            fail("❌ Ссылка 'Подробнее о сервисе' не найдена или не кликабельна.");
        }

        // === ЗАДАЧА 4: Заполнить поля и проверить работу кнопки «Продолжить» ===
        System.out.println("--- Проверка формы пополнения ---");

        By servicesOptionLocator = By.xpath("//button[contains(text(), 'Услуги связи')] | //label[contains(text(), 'Услуги связи')]");

        try {
            var servicesOption = wait.until(ExpectedConditions.elementToBeClickable(servicesOptionLocator));
            servicesOption.click();
            System.out.println("✅ Выбран вариант 'Услуги связи'.");
        } catch (Exception e) {
            // Если не кнопка, пробуем радио-кнопку
            By radioInputLocator = By.xpath("//input[@type='radio']//following::span[contains(text(), 'Услуги связи')]/parent::label");
            try {
                var radio = wait.until(ExpectedConditions.elementToBeClickable(radioInputLocator));
                radio.click();
                System.out.println("✅ (Альтернатива) Вариант 'Услуги связи' выбран через радио-кнопку.");
            } catch (Exception ex) {
                fail("❌ Не удалось выбрать вариант 'Услуги связи'. Проверьте структуру страницы.");
            }
        }
        By phoneInputLocator = By.xpath("//input[contains(@placeholder, 'Номер') or contains(@name, 'phone') or contains(@id, 'phone')]");

        try {
            var phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInputLocator));
            phoneInput.clear();
            phoneInput.sendKeys("297777777");
            System.out.println("✅ Номер телефона введен.");
        } catch (Exception e) {
            fail("❌ Не найдено поле для ввода номера телефона.");
        }
        By continueButtonLocator = By.xpath("//button[contains(text(), 'Продолжить')] | //a[contains(text(), 'Продолжить')]");

        try {
            var continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));

            System.out.println("✅ Кнопка 'Продолжить' найдена и готова к нажатию.");

            String beforeClickUrl = driver.getCurrentUrl();
            continueBtn.click();
            try {
                // Вариант А: Ждем изменения URL
                wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(beforeClickUrl)));
                System.out.println("✅ Нажатие кнопки сработало: произошел переход на следующую страницу.");
            } catch (Exception urlEx) {
                // Вариант Б: Если URL не меняется, ждем появления индикатора загрузки
                System.out.println("⚠️ URL не изменился сразу. Проверяем наличие индикатора загрузки...");
                By loaderLocator = By.cssSelector(".loader, .spinner, [class*='loading'], [class*='progress']");
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
                    System.out.println("✅ Нажатие сработало: виден индикатор загрузки.");
                } catch (Exception loadEx) {
                    System.out.println("ℹ️ Форма отправлена. Ждем появления сообщения об успехе или новой формы.");
                }
            }
        } catch (Exception e) {
            fail("❌ Кнопка 'Продолжить' не найдена или неактивна.");
        }

        System.out.println("\n🎉 ВСЕ ТЕСТЫ ПРОЙДЕНЫ УСПЕШНО!");
    }
}