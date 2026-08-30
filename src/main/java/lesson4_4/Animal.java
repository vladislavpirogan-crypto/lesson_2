package lesson4_4;

public class Animal {
    private static int animalCount = 0; // счётчик всех животных

    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        animalCount++; // увеличиваем счётчик при создании
    }

    // Ограничения задаются в наследниках, здесь заглушка
    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + "}";
    }
}
