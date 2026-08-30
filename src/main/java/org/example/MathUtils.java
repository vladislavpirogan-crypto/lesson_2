package org.example;

public class MathUtils {

    // 1. Факториал числа (n >= 0)
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 2. Площадь треугольника по основанию и высоте
    public static double triangleArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("base and height must be positive");
        }
        return 0.5 * base * height;
    }

    // 3. Арифметические действия с двумя целыми числами
    public static int add(int a, int b) { return a + b; }
    public static int subtract(int a, int b) { return a - b; }
    public static int multiply(int a, int b) { return a * b; }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("division by zero");
        }
        return a / b;
    }

    // 4. Сравнение двух целых чисел
    // -1 если a < b, 0 если a == b, 1 если a > b
    public static int compare(int a, int b) {
        if (a < b) return -1;
        if (a > b) return 1;
        return 0;
    }
}
