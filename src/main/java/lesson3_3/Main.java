package lesson3_3;



import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // === БЛОК 1: Работа с товарами (Product) ===
        System.out.println("--- СПИСОК ТОВАРОВ ---");

        Product[] products = new Product[5];

        products[0] = new Product("Samsung S25 Ultra", LocalDate.of(2025, 2, 1), "Samsung", "Южная Корея", 1299.99, false);
        products[1] = new Product("iPhone 16 Pro", LocalDate.of(2025, 3, 15), "Apple", "Китай", 1399.00, true);
        products[2] = new Product("Sony WH-1000XM5", LocalDate.of(2025, 1, 10), "Sony", "Япония", 349.50, false);
        products[3] = new Product("Xiaomi Redmi Note 13", LocalDate.of(2025, 2, 20), "Xiaomi", "Китай", 249.99, false);
        products[4] = new Product("Huawei MatePad Pro", LocalDate.of(2025, 3, 5), "Huawei", "Китай", 599.00, true);

        for (Product p : products) {
            p.printInfo();
        }

             }

            package lesson3_3;

import LocalDate;
import java.time.LocalTime;

    public class Main {
        public static void main(String[] args) {
            System.out.println("--- СПИСОК ТОВАРОВ ---");

            Product products = new Product;

            // ВАЖНО: Только значения через запятую. Никаких name:, price:, year:
            products = new Product();
            products = new Product("iPhone 16 Pro", LocalDate.of(2025, 3, 15), "Apple", "Китай", 1399.00, true);
            products = new Product("Sony WH-1000XM5", LocalDate.of(2025, 1, 10), "Sony", "Япония", 349.50, false);
            products = new Product("Xiaomi Redmi Note 13", LocalDate.of(2025, 2, 20), "Xiaomi", "Китай", 249.99, false);
            products = new Product("Huawei MatePad Pro", LocalDate.of(2025, 3, 5), "Huawei", "Китай", 599.00, true);

            for (Product p : products) p.printInfo();

            System.out.println("\n--- ПАРК АТТРАКЦИОНОВ ---");

            Park.Attraction rollerCoaster = new Park.Attraction(
                    "Американские горки",
                    LocalTime.of(10, 0),
                    LocalTime.of(22, 0),
                    800.0
            );

            Park.Attraction[] ferrisWheel = new Park.Attraction(
                    "Колесо обозрения",
                    LocalTime.of(11, 0),
                    LocalTime.of(23, 0),
                    500.0
            );

            Park.Attraction[] attractionsArray = new Park.Attraction;
            attractionsArray = new Park.Attraction[]{rollerCoaster};
            attractionsArray = new Park.Attraction[][]{ferrisWheel};

            Park centralPark = new Park("Центральный парк развлечений", attractionsArray);
            centralPark.showAllAttractions();
        }
    }
