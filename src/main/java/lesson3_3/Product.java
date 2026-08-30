package lesson3_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Product {
    private String name;
    private  LocalDate productionDate;
    private  String manufacturer;
    private  String country;
    private  double  price;
    private  boolean isReserved;

    public Product(String name, String productionDateStr,
                   String manufacturer, String country,
                   double price, boolean isReserved) {
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.productionDate = LocalDate.parse(productionDateStr, formatter);
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    public Product(String samsungS25Ultra, LocalDate localDate, String productionDateStr, String samsung, double price, boolean isReserved) {
    }

    public Product() {

    }

    public void printInfo() {
        System.out.println("Товар: " + name);
        System.out.println("Дата производства: " + productionDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Зарезервирован: " + (isReserved ? "Да" : "Нет"));
        System.out.println("-------------------------");
    }
}