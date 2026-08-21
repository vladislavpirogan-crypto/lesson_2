package lesson4_4;

public interface Shape {
    // Цвета как часть контракта
    String getFillColor();
    String getBorderColor();

    // Дефолтный метод для периметра (заглушка)
    default double getPerimeter() {
        return 0.0;
    }

    // Дефолтный метод для площади (заглушка)
    default double getArea() {
        return 0.0;
    }

    // Универсальный метод вывода характеристик
    default void printInfo() {
        System.out.printf(
                "Фигура: %s | Периметр: %.2f | Площадь: %.2f | Цвет фона: %s | Цвет границы: %s%n",
                this.getClass().getSimpleName(),
                getPerimeter(),
                getArea(),
                getFillColor(),
                getBorderColor()
        );
    }

    class Circle {
    }
}