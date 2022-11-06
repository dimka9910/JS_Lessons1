package labs.lab1.src.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2 {

    /**
     * Метод segregateEvenAndOddNumbers разделяет четные и нечетные числа в массиве array, т.у. возвращает массив,
     * в котором сперва записаны все четные числа массива array в порядке их следования, а затем все нечетные числа
     * массива array в порядке их следования.
     *
     * @param arr массив положительных чисел
     * @return массив с разделенными четными и нечетными числами
     * <p>
     * ПРИМЕР:
     * Вход: array = [2, 1, 5, 6, 8]
     * Выход: [2, 6, 8, 1, 5]
     */

    public static int[] segregateEvenAndOddNumbers(int[] arr) {
        //Вторым путём решения - будет разделять числа на два массива, а потом
        int length = arr.length;
        int[] separated = new int[length];

        int index = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] % 2 == 0) {
                separated[index] = arr[i];
                index++;
            }
        }

        for (int i = 0; i < length; i++) {
            if (arr[i] % 2 != 0) {
                separated[index] = arr[i];
                index++;
            }
        }

        return separated;
    }

}
