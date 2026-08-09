package lesson4_4;

public class Bowl {
    private int foodAmount;

    public Bowl(int initialFood) {
        this.foodAmount = Math.max(initialFood, 0);
    }

    // метод добавления еды
    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
        }
        // если amount <= 0, ничего не делаем
    }

    // попытка дать коту еду: возвращает true, если еды хватило, иначе false
    public boolean tryFeedCat(int needed) {
        if (needed <= 0) return false;
        if (foodAmount >= needed) {
            foodAmount -= needed;
            return true;
        }
        return false; // еды не хватило, кот не трогает миску
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}
