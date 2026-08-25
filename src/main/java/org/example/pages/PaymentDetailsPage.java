package org.example.;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentDetailsPage {

    private final WebDriver driver;

    @FindBy(css = ".payment-summary .total-amount")
    private WebElement totalAmountDisplay;

    @FindBy(css = "button.pay-button span")
    private WebElement payButtonAmountText;

    @FindBy(css = ".summary-phone")
    private WebElement displayedPhone;

    // Незаполненные поля для реквизитов карты
    @FindBy(css = "#card-number")
    private WebElement cardNumberField;

    @FindBy(css = "#expiry-date")
    private WebElement expiryDateField;

    @FindBy(css = "#cvv")
    private WebElement cvvField;

    // Иконки платёжных систем (пример: .payment-icons img)
    @FindBy(css = ".payment-icons img")
    private List<WebElement> paymentIcons;

    // Подсказки/надписи у полей (placeholder или label)
    private static final By cardNumberLabel = By.cssSelector("[for='card-number']");
    private static final By expiryDateLabel = By.cssSelector("[for='expiry-date']");
    private static final By cvvLabel = By.cssSelector("[for='cvv']");

    public PaymentDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTotalAmount() {
        return totalAmountDisplay.getText();
    }

    public String getPayButtonAmount() {
        return payButtonAmountText.getText();
    }

    public String getDisplayedPhone() {
        return displayedPhone.getText();
    }

    public List<WebElement> getPaymentIcons() {
        return paymentIcons;
    }

    public String getCardNumberLabel() {
        return driver.findElement(cardNumberLabel).getText();
    }

    public String getExpiryDateLabel() {
        return driver.findElement(expiryDateLabel).getText();
    }

    public String getCvvLabel() {
        return driver.findElement(cvvLabel).getText();
    }

    public boolean isCardNumberFieldEmpty() {
        return cardNumberField.getAttribute("value") == null || cardNumberField.getAttribute("value").isEmpty();
    }

    public boolean isExpiryDateFieldEmpty() {
        return expiryDateField.getAttribute("value") == null || expiryDateField.getAttribute("value").isEmpty();
    }

    public boolean isCvvFieldEmpty() {
        return cvvField.getAttribute("value") == null || cvvField.getAttribute("value").isEmpty();
    }
}