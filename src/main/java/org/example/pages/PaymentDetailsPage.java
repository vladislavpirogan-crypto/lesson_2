
package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentDetailsPage extends BasePage {

    @FindBy(id = "amount-input")
    private WebElement amountInput;

    @FindBy(css = ".continue-button")
    private WebElement continueButton;

    @FindBy(css = ".summary-amount")
    private WebElement summaryAmount;

    private final WebDriverWait wait;

    public PaymentDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterAmount(String amount) {
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getSummaryAmountText() {
        return wait.until(ExpectedConditions.visibilityOf(summaryAmount)).getText();
    }

}