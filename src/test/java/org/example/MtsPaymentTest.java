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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Задача 1: Проверить, что блок «Онлайн пополнение без комиссии» отображается
    @Test
    public void testBlockTitleIsPresent() {
        driver.get(BASE_URL);

        String pageSource = driver.getPageSource();
        if (pageSource != null && (pageSource.contains("Checking") || pageSource.contains("Access denied"))) {
            fail("Сайт заблокировал доступ (Cloudflare/Captcha). Тест не может пройти дальше.");
        }

        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        By blockTitleLocator = By.xpath("//*[contains(text(), '" + expectedBlockTitle + "')]");

        WebElement blockTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));

        assertTrue(blockTitleElement.getText().contains(expectedBlockTitle),
                "Не найден блок с заголовком '" + expectedBlockTitle + "'");
    }

    // Задача 2: Проверить наличие логотипов платёжных систем в блоке
    @Test(dependsOnMethods = "testBlockTitleIsPresent")
    public void testPaymentLogosArePresent() {
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        By blockTitleLocator = By.xpath("//*[contains(text(), '" + expectedBlockTitle + "')]");
        WebElement blockElement = driver.findElement(blockTitleLocator);

        List<WebElement> logos = blockElement.findElements(By.tagName("img"));

        assertFalse(logos.isEmpty(), "В блоке не найдено ни одного логотипа платёжной системы");
    }

    // Задача 3: Проверить работу ссылки «Подробнее о сервисе» —
    // не только переход, но и что на новой странице нужный контент
    @Test(dependsOnMethods = "testBlockTitleIsPresent")
    public void testMoreInfoLinkWorks() {
        By moreInfoLinkLocator = By.linkText("Подробнее о сервисе");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLinkLocator));

        String originalUrl = driver.getCurrentUrl();
        link.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));
        String newUrl = driver.getCurrentUrl();

        // Assert 1: URL изменился
        assertNotEquals(newUrl, originalUrl, "Переход по ссылке не произошёл: URL не изменился");

        // Assert 2: на новой странице есть ожидаемый контент.
        // Проверяем, что страница содержит текст, связанный с услугой пополнения.
        // Здесь мы ждём появления элемента, который подтверждает, что мы на нужной странице.
        By pageContentLocator = By.xpath(
                "//*[contains(text(), 'Платежи') or contains(text(), 'Пополнение') " +
                        "or contains(text(), 'Комиссия') or contains(text(), 'Сервис')]");

        WebElement contentElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageContentLocator));

        assertNotNull(contentElement,
                "Переход выполнен, но на новой странице нет ожидаемого контента о сервисе платежей");

        String text = contentElement.getText();
        assertFalse(text.isEmpty(),
                "Контент найден, но текст пустой — возможно, страница не загрузилась корректно");

        System.out.println("Ссылка работает: переход на " + newUrl + ", контент подтверждён.");

        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(originalUrl));
    }

    // Задача 4: Заполнить форму и проверить реакцию кнопки «Продолжить» —
    // проверяем не только факт клика, но и появление ожидаемого результата
    @Test(dependsOnMethods = "testBlockTitleIsPresent")
    public void testContinueButtonTriggersAction() {
        By servicesOptionLocator = By.xpath(
                "//button[contains(text(), 'Услуги связи')] | //label[contains(text(), 'Услуги связи')]");

        WebElement servicesOption = wait.until(ExpectedConditions.elementToBeClickable(servicesOptionLocator));
        servicesOption.click();

        By phoneInputLocator = By.xpath(
                "//input[contains(@placeholder, 'Номер') or contains(@name, 'phone') or contains(@id, 'phone')]");
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInputLocator));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        By continueButtonLocator = By.xpath(
                "//button[contains(text(), 'Продолжить')] | //a[contains(text(), 'Продолжить')]");
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));

        String beforeClickUrl = driver.getCurrentUrl();
        continueBtn.click();

        // Проверяем, что после нажатия появился ожидаемый результат:
        // либо переход на новую страницу с формой подтверждения,
        // либо появление элементов подтверждения на текущей странице.
        try {
            // Вариант А: переход на новую страницу — проверяем и URL, и контент
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(beforeClickUrl)));
            String afterClickUrl = driver.getCurrentUrl();
            assertNotEquals(afterClickUrl, beforeClickUrl, "URL не изменился после нажатия");

            // Проверяем, что на новой странице есть ожидаемый контент
            // (форма подтверждения, поля карты, сообщение и т.д.)
            By confirmationContentLocator = By.xpath(
                    "//*[contains(text(), 'Карта') or contains(text(), 'Оплата') " +
                            "or contains(text(), 'Сумма') or contains(text(), 'Подтверждение')]");

            WebElement confirmationElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(confirmationContentLocator));
            assertNotNull(confirmationElement,
                    "Переход выполнен, но на странице нет ожидаемого контента подтверждения платежа");

        } catch (Exception e) {
            // Вариант Б: URL не изменился — проверяем появление элементов на текущей странице
            By resultLocator = By.xpath(
                    "//*[contains(@class, 'loader') or contains(@class, 'spinner') " +
                            "or contains(text(), 'Оплата') or contains(text(), 'Карта') " +
                            "or contains(text(), 'Сумма')]");

            WebElement resultElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(resultLocator));
            assertNotNull(resultElement,
                    "После нажатия «Продолжить» не появился ни переход, ни ожидаемый контент на странице");
        }
    }
}