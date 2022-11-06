package labs.lab1.src.java;

public class Problem4 {

    /**
     * Метод flattenMatrix преобразует матрицу размера nxm в одномерный массив, записывая сперва элементы первого столбца,
     * затем элементы второго столбца и т.д.
     *
     * @param matrix матрица размера nxm
     * @return одномерный массив
     *
     * ПРИМЕР:
     * Вход: matrix = [[1, 2, 3],
     *                 [4, 5, 6],
     *                 [7, 8, 9]]
     * Выход: [1, 4, 7, 2, 5, 8, 3, 6, 9]
     */

    public static int[] flattenMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] flatMatrix = new int[m * n];
        int flatIndex = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flatMatrix[flatIndex] = matrix[j][i];
                flatIndex++;
            }
        }

        return flatMatrix;
    }

}
