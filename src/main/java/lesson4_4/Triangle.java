package lesson4_4;

public class Triangle implements Shape {
    private final double a;
    private final double b;
    private final double c;
    private final String fillColor;
    private final String borderColor;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        if (!isValidTriangle(a, b, c)) {
            throw new IllegalArgumentException("Треугольник с такими сторонами невозможен");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    private static boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a && a > 0 && b > 0 && c > 0;
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2.0; // полупериметр
        // Формула Герона: sqrt(p * (p-a) * (p-b) * (p-c))
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}
