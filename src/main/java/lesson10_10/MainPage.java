package lesson10_10;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы элементов блока «Онлайн пополнение»
    private final By paymentLogos = By.xpath("//div[contains(@class,'pay__partners')]//img");
    private final By blockTitle = By.xpath("//div[@class='pay__wrapper']//h2");
    private final By moreInfoLink = By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(text(), 'Подробнее о сервисе')]");
    private final By moreInfoTitle = By.xpath("//div[@class='container-fluid']//h3");
    private final By phoneInput = By.id("connection-phone");
    private final By sumInput = By.id("connection-sum");
    private final By emailInput = By.id("connection-email");
    private final By submitButton = By.xpath("//button[@class='button button__default ']");

    // === Локаторы вкладок услуг (ИСПРАВЛЕНО: ищем и button, и a) ===
    private final By tabServices = By.xpath("//button[contains(normalize-space(), 'Услуги связи')] | //a[contains(normalize-space(), 'Услуги связи')]");
    private final By tabHomeInternet = By.xpath("//button[contains(normalize-space(), 'Домашний') and contains(normalize-space(), 'интернет')] | //a[contains(normalize-space(), 'Домашний') and contains(normalize-space(), 'интернет')]");
    private final By tabTelevision = By.xpath("//button[contains(normalize-space(), 'Телевидение')] | //a[contains(normalize-space(), 'Телевидение')]");
    private final By tabRoaming = By.xpath("//button[contains(normalize-space(), 'роуминг') or contains(normalize-space(), 'Роуминг')] | //a[contains(normalize-space(), 'роуминг') or contains(normalize-space(), 'Роуминг')]");

    // Локаторы iframe
    private final By paymentIframe = By.cssSelector("iframe[src*='payment'], iframe[src*='pay']");

    // Элементы внутри виджета
    private final By amountInWidget = By.xpath("//*[contains(@class,'sum') or contains(@class,'amount') or contains(@class,'price')]");
    private final By phoneInWidget = By.xpath("//*[contains(@class,'phone') or contains(@class,'number') or contains(@class,'tel')]");
    private final By payButtonInWidget = By.xpath("//button[contains(@class,'pay') or contains(@class,'submit') or contains(@class,'btn')] | //*[contains(@class,'pay-button')]");
    private final By logosInWidget = By.xpath("//img[contains(@class,'payment') or contains(@class,'logo') or contains(@class,'card')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        // Базовое ожидание 10 сек
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу mts.by и закрыть баннер cookies")
    public void open() {
        driver.get("https://mts.by");
        try {
            // Ждем кнопку "Принять" и кликаем, если есть
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Принять')]"))).click();
        } catch (Exception ignored) {
        }
    }

    @Step("Получить заголовок блока оплаты")
    public String getBlockTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).getText().trim();
    }

    @Step("Посчитать логотипы платёжных систем")
    public int getLogos() {
        List<WebElement> logos = driver.findElements(paymentLogos);
        return logos.size();
    }

    @Step("Кликнуть по ссылке «Подробнее о сервисе»")
    public void clickMoreInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink)).click();
    }

    public String getMoreInfoLink() {
        return driver.findElement(moreInfoLink).getAttribute("href");
    }

    @Step("Получить заголовок страницы «Подробнее о сервисе»")
    public String getMoreInfoTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(moreInfoTitle)).getText().trim();
    }

    @Step("Заполнить форму «Услуги связи»: телефон={phone}, сумма={sum}, email={email}")
    public void fillConnectionForm(String phone, String sum, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).clear();
        driver.findElement(phoneInput).sendKeys(phone);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput)).clear();
        driver.findElement(sumInput).sendKeys(sum);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Нажать кнопку «Продолжить»")
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Проверить переход к платёжному виджету")
    public boolean isTransitionToPayment() {
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            longerWait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(paymentIframe),
                    ExpectedConditions.visibilityOfElementLocated(By.className("payment-widget"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Step("Проверить, что вкладка «Услуги связи» активна")
    public boolean isServicesTabActive() {
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            longerWait.until(ExpectedConditions.elementToBeClickable(tabServices));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить наличие вкладки «Домашний интернет»")
    public boolean isHomeInternetTabPresent() {
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            longerWait.until(ExpectedConditions.elementToBeClickable(tabHomeInternet));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить наличие вкладки «Телевидение»")
    public boolean isTelevisionTabPresent() {
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            longerWait.until(ExpectedConditions.elementToBeClickable(tabTelevision));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить наличие вкладки «Роуминг»")
    public boolean isRoamingTabPresent() {
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            longerWait.until(ExpectedConditions.elementToBeClickable(tabRoaming));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ================= МЕТОДЫ РАБОТЫ С ВИДЖЕТОМ =================

    @Step("Переключиться в iframe платёжного виджета")
    public void switchToPaymentWidget() {
        WebDriverWait waitForFrame = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitForFrame.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
    }

    @Step("Вернуться из iframe в основной контент")
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    @Step("Получить сумму в платёжном виджете")
    public String getAmountInWidget() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(amountInWidget)).getText().trim();
    }

    @Step("Получить номер телефона в платёжном виджете")
    public String getPhoneInWidget() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInWidget)).getText().trim();
    }

    @Step("Получить текст кнопки оплаты в виджете")
    public String getPayButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payButtonInWidget)).getText().trim();
    }

    @Step("Проверить наличие иконок платёжных систем в виджете")
    public int getLogosInWidgetCount() {
        return driver.findElements(logosInWidget).size();
    }
}
