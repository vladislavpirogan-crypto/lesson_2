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

    @Test
    @DisplayName("Проверка наличия каждой услуги связи в блоке оплаты")
    public void testAllServicesTabs() {
        // mainPage.open(); <-- УДАЛЕНО! Страница уже открыта в BaseTest

        assertTrue(mainPage.isServicesTabActive(), "Вкладка «Услуги связи» не отображается");
        assertTrue(mainPage.isHomeInternetTabPresent(), "Вкладка «Домашний интернет» не отображается");
        assertTrue(mainPage.isTelevisionTabPresent(), "Вкладка «Телевидение» не отображается");
        assertTrue(mainPage.isRoamingTabPresent(), "Вкладка «Роуминг» не отображается");
    }

    @Test
    @DisplayName("В платёжном виджете корректны сумма и телефон")
    public void testPaymentWidgetContent() {
        String phone = "297777777";
        String sum = "10";

        mainPage.fillConnectionForm(phone, sum, "test@test.by");
        mainPage.clickContinue();
        assertTrue(mainPage.isTransitionToPayment(), "Платёжный виджет не появился");

        mainPage.switchToPaymentWidget();

        // 1. Проверка суммы
        String amountText = mainPage.getAmountInWidget();
        assertTrue(amountText.contains(sum), "Сумма в виджете не совпадает. Ожидали: " + sum + ", получили: " + amountText);

        // 2. Проверка телефона
        String phoneText = mainPage.getPhoneInWidget();
        assertTrue(phoneText.contains(phone), "Номер телефона в виджете не совпадает. Ожидали: " + phone + ", получили: " + phoneText);

        // 3. Проверка кнопки
        String buttonText = mainPage.getPayButtonText();
        assertTrue(buttonText.contains(sum) || buttonText.contains("Оплатить"), "Кнопка не найдена или неверная");

        // 4. Проверка иконок
        int logosCount = mainPage.getLogosInWidgetCount();
        assertTrue(logosCount >= 3, "Иконки платёжных систем не отображаются. Найдено: " + logosCount);

        mainPage.switchToDefaultContent();
    }
}