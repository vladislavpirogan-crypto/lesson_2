package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class TopUpPage extends BasePage {

    @FindBy(css = "[data-type='communication']")
    private WebElement btnCommunication;

    @FindBy(css = "[data-type='home-internet']")
    private WebElement btnHomeInternet;

    @FindBy(css = "[data-type='installment']")
    private WebElement btnInstallment;

    @FindBy(css = "[data-type='debt']")
    private WebElement btnDebt;

    private final Map<String, WebElement> typeButtons = new HashMap<>();

    public TopUpPage(WebDriver driver) {
        super(driver);
        // Без этой строки @FindBy не заполнит поля, и они останутся null
        PageFactory.initElements(driver, this);

        typeButtons.put("услуги связи", btnCommunication);
        typeButtons.put("домашний интернет", btnHomeInternet);
        typeButtons.put("рассрочка", btnInstallment);
        typeButtons.put("задолженность", btnDebt);
    }

    public void selectPaymentType(String type) {
        WebElement button = typeButtons.get(type.toLowerCase());
        if (button == null) {
            throw new IllegalArgumentException("Неизвестный тип оплаты: " + type);
        }
        button.click();
    }

    public String getPlaceholderText(String fieldLocatorCss) {
        return driver.findElement(By.cssSelector(fieldLocatorCss)).getAttribute("placeholder");
    }
}