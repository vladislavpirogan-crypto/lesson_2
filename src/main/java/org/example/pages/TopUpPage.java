package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class TopUpPage {

    private final WebDriver driver;

    // Радио-кнопки/кнопки выбора варианта оплаты
    @FindBy(css = "[data-option='communication-services']")
    private WebElement optionCommunicationServices;

    @FindBy(css = "[data-option='home-internet']")
    private WebElement optionHomeInternet;

    @FindBy(css = "[data-option='installment']")
    private WebElement optionInstallment;

    @FindBy(css = "[data-option='debt']")
    private WebElement optionDebt;

    // Поля ввода (примерные селекторы — подставь свои)
    @FindBy(css = "#phone-input")
    private WebElement phoneInput;

    @FindBy(css = "#amount-input")
    private WebElement amountInput;

    @FindBy(css = "button[data-action='continue']")
    private WebElement continueButton;

    // Сообщения/надписи в незаполненных полях (или атрибуты placeholder / aria-label / span рядом)
    private static final Map<String, By> requiredFieldLabels = new HashMap<>();

    static {
        requiredFieldLabels.put("phone", By.cssSelector("[for='phone-input']"));
        requiredFieldLabels.put("amount", By.cssSelector("[for='amount-input']"));
    }

    public TopUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectOptionCommunicationServices() {
        optionCommunicationServices.click();
    }

    public void selectOptionHomeInternet() {
        optionHomeInternet.click();
    }

    public void selectOptionInstallment() {
        optionInstallment.click();
    }

    public void selectOptionDebt() {
        optionDebt.click();
    }

    public void setPhone(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void setAmount(String amount) {
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getLabelText(String fieldKey) {
        return driver.findElement(requiredFieldLabels.get(fieldKey)).getText();
    }

    public boolean isRequiredFieldLabelPresent(String fieldKey) {
        return driver.findElements(requiredFieldLabels.get(fieldKey)).size() > 0;
    }
}
