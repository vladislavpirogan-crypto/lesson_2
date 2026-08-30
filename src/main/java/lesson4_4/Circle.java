package lesson4_4;

public class Circle implements Shape {
    private final double radius;
    private final String fillColor;
    private final String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getPerimeter() {
        // Длина окружности: 2 * π * r
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        // Площадь круга: π * r^2
        return Math.PI * radius * radius;
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