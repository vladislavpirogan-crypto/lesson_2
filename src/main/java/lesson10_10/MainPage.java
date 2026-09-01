package lesson10_10;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object главной страницы mts.by:
 * здесь живут локаторы элементов и действия над ними.
 * Тесты сами не ищут элементы — только зовут методы этого класса.
 */
public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы элементов блока «Онлайн пополнение»
    private final By paymentLogos = By.xpath("//div[contains(@class,'pay__partners')]//img");
    private final By blockTitle = By.xpath("//div[@class='pay__wrapper']//h2");
    private final By moreInfoLink = By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(text(), 'Подробнее о сервисе')]");
    private final By moreInfoTitle = By.xpath("//div[@class='container-fluid']//h3[2]");
    private final By phoneInput = By.id("connection-phone");
    private final By sumInput = By.id("connection-sum");
    private final By emailInput = By.id("connection-email");
    private final By submitButton = By.xpath("//button[@class='button button__default ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу mts.by и закрыть баннер cookies")
    public void open() {
        driver.get("https://mts.by");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Принять')]"))).click();
        } catch (Exception ignored) {}
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
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("payment-widget-iframe")),
                    ExpectedConditions.visibilityOfElementLocated(By.className("payment-widget"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}