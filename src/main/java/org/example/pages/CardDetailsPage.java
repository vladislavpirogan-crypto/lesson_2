package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CardDetailsPage extends BasePage {

    @FindBy(id = "4111111111111111")
    private WebElement cardNumberInput;

    @FindBy(id = "12/25")
    private WebElement expiryInput;

    @FindBy(id = "123")
    private WebElement cvvInput;

    @FindBy(css = ".payment-icons")
    private WebElement paymentIcons;

    private final WebDriverWait wait;

    public CardDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        // ✅ Важно: Duration.ofSeconds для Selenium 4
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCardNumber(String cardNumber) {
        cardNumberInput.clear();
        cardNumberInput.sendKeys(cardNumber);
    }

    public void enterExpiryDate(String expiry) {
        expiryInput.clear();
        expiryInput.sendKeys(expiry);
    }

    public void enterCvv(String cvv) {
        cvvInput.clear();
        cvvInput.sendKeys(cvv);
    }

    public String getPaymentIconsText() {
        // ✅ Используем visibilityOf с WebElement, который уже найден через @FindBy
        return wait.until(ExpectedConditions.visibilityOf(paymentIcons)).getText();
    }

}