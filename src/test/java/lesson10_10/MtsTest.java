package lesson10_10;

import lesson_10_Base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MtsTest extends BaseTest {

    @Test
    @DisplayName("Заголовок блока «Онлайн пополнение без комиссии» отображается")
    public void testBlockTitle() {
        String expectedTitle = "ОНЛАЙН ПОПОЛНЕНИЕ\nБЕЗ КОМИССИИ";
        assertTrue(mainPage.getBlockTitle().contains(expectedTitle), "Название блока не отображается");
    }

    @Test
    @DisplayName("В блоке оплаты присутствуют все логотипы платёжных систем")
    public void testPaymentLogo() {
        assertEquals(5, mainPage.getLogos(), "Присутствуют не все логотипы");
    }

    @Test
    @DisplayName("Ссылка «Подробнее о сервисе» ведёт на страницу с информацией")
    public void testMoreInfoLink() {
        mainPage.clickMoreInfoLink();
        assertEquals("Информация о безопасности Интернет-платежей", mainPage.getMoreInfoTitle(),
                "Неверный заголовок страницы");
    }

    @Test
    @DisplayName("После заполнения формы и нажатия «Продолжить» открывается окно оплаты")
    public void testConnectionForm() {
        mainPage.fillConnectionForm("297777777", "10", "test@test.by");
        mainPage.clickContinue();
        assertTrue(mainPage.isTransitionToPayment(), "Окно оплаты не появилось");
    }
}