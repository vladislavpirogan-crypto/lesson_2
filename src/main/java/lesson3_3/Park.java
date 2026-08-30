package lesson3_3;

import java.time.LocalTime;

public class Park {
    // Внутренний класс (вложен внутри Park)
    public static class Attraction {
        private final String name;
        private final LocalTime openTime;
        private final LocalTime closeTime;
        private final double price;

        // Конструктор
        public Attraction(String name, LocalTime openTime, LocalTime closeTime, double price) {
            this.name = name;
            this.openTime = openTime;
            this.closeTime = closeTime;
            this.price = price;
        }

        // Метод для вывода информации
        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: с " + openTime + " до " + closeTime);
            System.out.println("Стоимость: " + price + " руб.");
            System.out.println("--------------------------");
        }
    }

    // Поля самого парка
    private final String parkName;
    private final Attraction[] attractions;

    // Конструктор парка (принимает массив аттракционов)
    public Park(String parkName, Attraction[] attractions) {
        this.parkName = parkName;
        this.attractions = attractions;
    }

    // Метод, чтобы вывести все аттракционы парка
    public void showAllAttractions() {
        System.out.println("Парк: " + parkName);
        System.out.println("Список аттракционов:");
        for (Attraction a : attractions) {
            a.printInfo();
        }
    }
}