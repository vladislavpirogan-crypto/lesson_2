package lesson4_4;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull; // сытость

    public Cat(String name, int age) {
        super(name, age);
        this.isFull = false; // при создании кот голоден
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean isFull() {
        return isFull;
    }

    @Override
    public void run(int distance) {
        int maxRun = 200;
        if (distance <= maxRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance +
                    " м. Максимум: " + maxRun + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }

    // кот пытается поесть из миски
    public void eatFromBowl(Bowl bowl, int neededFood) {
        if (isFull) {
            System.out.println(name + " уже сыт, есть не будет.");
            return;
        }

        boolean success = bowl.tryFeedCat(neededFood);
        if (success) {
            isFull = true;
            System.out.println(name + " поел и теперь сыт.");
        } else {
            System.out.println(name + " хотел поесть, но еды в миске не хватило. Остался голодным.");
        }
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + "', isFull=" + isFull + "}";
    }
}