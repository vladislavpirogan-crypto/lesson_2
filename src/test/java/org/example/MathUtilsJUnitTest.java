package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsJUnitTest {

    @Test
    void testFactorial_valid() {
        assertEquals(1, MathUtils.factorial(0));
        assertEquals(1, MathUtils.factorial(1));
        assertEquals(6, MathUtils.factorial(3));
        assertEquals(24, MathUtils.factorial(4));
    }

    @Test
    void testTriangleArea_valid() {
        // Если triangleArea возвращает double, обязательно указываем delta (погрешность)
        double area = MathUtils.triangleArea(3, 4, 5);
        assertEquals(6.0, area, 0.001);
    }

    @Test
    void testArithmetic_add() {
        assertEquals(5, MathUtils.add(2, 3));
        assertEquals(-1, MathUtils.add(-2, 1));
    }

    @Test
    void testArithmetic_subtract() {
        assertEquals(1, MathUtils.subtract(3, 2));
        assertEquals(-5, MathUtils.subtract(-2, 3));
    }

    @Test
    void testArithmetic_multiply() {
        assertEquals(6, MathUtils.multiply(2, 3));
        assertEquals(-6, MathUtils.multiply(-2, 3));
    }

    @Test
    void testArithmetic_divide() {
        assertEquals(2.0, MathUtils.divide(6, 3), 0.001);
        assertEquals(-2.0, MathUtils.divide(-6, 3), 0.001);
        // Если деление на ноль должно кидать исключение — раскомментируй:
        // assertThrows(ArithmeticException.class, () -> MathUtils.divide(5, 0));
    }

    @Test
    void testCompare_logic() {
        // Важно: передаём boolean, а не int
        assertTrue(MathUtils.compare(5, 5));
        assertFalse(MathUtils.compare(5, 6));
    }
}
