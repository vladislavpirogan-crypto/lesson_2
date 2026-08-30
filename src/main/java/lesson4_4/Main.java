package lesson4_4;

public class Main {
    public static void main(String[] args) {
        // Создаём миску с 10 единицами еды
        Bowl bowl = new Bowl(10);

        // Массив котов
        Cat[] cats = new Cat[3];
        cats[0] = new Cat("Барсик", 2);
        cats[1] = new Cat("Мурзик", 3);
        cats[2] = new Cat("Снежок", 1);

        // Попросим всех котов покушать (каждому нужно по 5 единиц)
        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 5);
        }

        System.out.println("Осталось еды в миске: " + bowl.getFoodAmount());

        // Добавим ещё еды в миску
        bowl.addFood(8);
        System.out.println("Добавили еды. Теперь в миске: " + bowl.getFoodAmount());

        // Ещё один кот попробует поесть (ему нужно 10)
        Cat extraCat = new Cat("Пушок", 4);
        extraCat.eatFromBowl(bowl, 10);

        // Выводим сытость всех котов
        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isFull() ? "сыт" : "голоден"));
        }
        System.out.println(extraCat.name + ": " + (extraCat.isFull() ? "сыт" : "голоден"));

        // Проверка счётчиков
        System.out.println("\nСтатистика:");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println("Собак: " + Dog.getDogCount());

        // Пример бега и плавания
        Dog dog = new Dog("Бобик", 3, "Лабрадор");
        dog.run(150);       // ОК
        dog.run(600);       // превышен лимит
        dog.swim(5);        // ОК
        dog.swim(20);       // превышен лимит

        Cat catExample = new Cat("Рыжик", 2);
        catExample.run(100); // ОК
        catExample.run(300); // превышен лимит
        catExample.swim(5);  // не умеет плавать
    }
}


    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];

        shapes[0] = new Circle(5.0, "красный", "чёрный");
        shapes[1] = new Rectangle(4.0, 6.0, "зелёный", "синий");
        shapes[2] = new Triangle(3.0, 4.0, 5.0, "жёлтый", "коричневый");

        for (Shape shape : shapes) {
            shape.printInfo();
        }
    }
}
