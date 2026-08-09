package lesson5_5;

public class ArrayProcessor {

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        return extracted(array);
    }

    private static int extracted(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException("Массив должен иметь 4 строки. Получено: " + (array == null ? "null" : array.length));
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException(
                        "Строка " + i + " должна содержать 4 элемента. Получено: " +
                                (array[i] == null ? "null" : array[i].length)
                );
            }
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String cell = array[i][j];
                if (cell == null) {
                    throw new MyArrayDataException("Ячейка [" + i + "][" + j + "] равна null");
                }
                try {
                    sum += Integer.parseInt(cell);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            "Ячейка [" + i + "][" + j + "] содержит некорректные данные: '" + cell + "'"
                    );
                }
            }
        }
        return sum;
    }
}
