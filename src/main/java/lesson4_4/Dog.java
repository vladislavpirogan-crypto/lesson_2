package lesson4_4;

public class Dog extends Animal {
    private static int dogCount = 0;
    private final String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int distance) {
        int maxRun = 500;
        if (distance <= maxRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance +
                    " м. Максимум: " + maxRun + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        int maxSwim = 10;
        if (distance <= maxSwim) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance +
                    " м. Максимум: " + maxSwim + " м.");
        }
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', breed='" + breed + "'}";
    }
}
