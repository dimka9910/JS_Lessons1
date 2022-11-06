package labs.lab1.src.java;

import java.util.Arrays;

public class Problem5 {

    /**
     * Метод isGeometricProgression определяет, является ли данная последовательность чисел numbers геометрической
     * прогрессией (возможно, при перестановке элементов)
     *
     * @param numbers строка, содержащая n положительных целых чисел, разделенных запятой
     * @return true, если последовательность является геометрической прогрессией
     *         false, если последовательность не является геометрической прогрессией
     *
     * ПРИМЕР1:
     * Вход: numbers = "1,2,4,8,16"
     * Выход: true
     *
     * ПРИМЕР2:
     * Вход: numbers = "16,2,8,1,4"
     * Выход: true (так как в результате перестановки элементов можно получить геометрическую прогрессию [1,2,4,8,16])
     *
     * ПРИМЕР3:
     * Вход: numbers = "2,3,5"
     * Выход: false
     */
    public static boolean isGeometricProgression(String numbers) {
        boolean isGeometricProgresssion = true;

        int[] numbersFromString = stringToInArray(numbers,",");
        Arrays.sort(numbersFromString);

        double denominator = (double) numbersFromString[1] / numbersFromString[0];

        for (int i = 1; i < numbersFromString.length - 1; i++) {
            isGeometricProgresssion = (double) numbersFromString[i + 1] / numbersFromString[i] == denominator;
        }

        return isGeometricProgresssion;
    }

    public static int[] stringToInArray(String nums, String delimeter){
        String[] separatedNums = nums.split(delimeter);
        int[] numbers = new int[separatedNums.length];

        for (int i = 0; i < separatedNums.length; i++) {
            numbers[i] = Integer.parseInt(separatedNums[i]);
        }

        return numbers;
    }
}
