import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
    }

    private static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
}
public class Main {
    public static void main(String[] args) {
        checkSumSign();
    }

    private static void checkSumSign() {
        int a = 10;   // можешь поставить любые значения
        int b = -5;

        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        printColor();
    }

    private static void printColor() {
        int value = 50;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        compareNumbers();
    }

    private static void compareNumbers() {
        int a = 15;
        int b = 10;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println(isSumInRange(5, 6));
        System.out.println(isSumInRange(3, 4));
        System.out.println(isSumInRange(10, 10));
        System.out.println(isSumInRange(11, 10));
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }
}
public class Main {
    public static void main(String[] args) {
        printPositiveOrNegative(5);
        printPositiveOrNegative(-3);
        printPositiveOrNegative(0);
    }

    public static void printPositiveOrNegative(int value) {
        if (value >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Отрицательное");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println(isNegative(-5));  // true
        System.out.println(isNegative(0));   // false (ноль считаем положительным)
        System.out.println(isNegative(3));    // false
    }

    public static boolean isNegative(int value) {
        return value < 0;
    }
}
public class Main {
    public static void main(String[] args) {
        printStringNTimes("Привет", 3);
        // Вывод:
        // Привет
        // Привет
        // Привет
    }

    public static void printStringNTimes(String text, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(text);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("2000: " + isLeapYear(2000)); // true (делится на 400)
        System.out.println("1900: " + isLeapYear(1900)); // false (делится на 100, но не на 400)
        System.out.println("2024: " + isLeapYear(2024)); // true (делится на 4, не на 100)
        System.out.println("2023: " + isLeapYear(2023)); // false
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println("До: " + Arrays.toString(arr));
        invertZeroOneArray(arr);
        System.out.println("После: " + Arrays.toString(arr));
    }

    public static void invertZeroOneArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else if (arr[i] == 1) {
                arr[i] = 0;
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        int[] arr = createFilledArray100();


        System.out.println("Первые 10 элементов: " + Arrays.toString(Arrays.copyOf(arr, 10)));


        System.out.println("Последние 10 элементов: " + Arrays.toString(Arrays.copyOfRange(arr, 90, 100)));
    }

    public static int[] createFilledArray100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println("До: " + Arrays.toString(arr));

        doubleIfLessThanSix(arr);

        System.out.println("После: " + Arrays.toString(arr));
        // Ожидаемый результат: [2, 10, 6, 4, 11, 8, 10, 4, 8, 8, 9, 2]
    }

    public static void doubleIfLessThanSix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        int size = 5; // размер матрицы 5x5 (можно поменять на любое число)
        int[][] matrix = createMatrixWithDiagonal(size);

        System.out.println("Матрица с диагональю (" + size + "x" + size + "):");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

    }

    public static int[][] createMatrixWithDiagonal(int size) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }

        return matrix;
    }
}
public class Main {
    public static void main(String[] args) {
        int len = 7;
        int initialValue = 42;

        int[] arr = createArrayWithValue(len, initialValue);

        System.out.println("Массив: " + Arrays.toString(arr));
        // Вывод: [42, 42, 42, 42, 42, 42, 42]
    }

    public static int[] createArrayWithValue(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}