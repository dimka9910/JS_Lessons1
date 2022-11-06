package labs.lab1Tests;

import labs.lab1.src.java.Problem4;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Problem4Test {

    @Test
    void shouldReturnFlatMatrix_ifNumOfRowsEqualNumOfColumns() {
        // given
        int[][] matrix = new int[][]
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };

        int[] flatMatrix = new int[]{1, 4, 7, 2, 5, 8, 3, 6, 9};

        // then
        assertArrayEquals(flatMatrix, Problem4.flattenMatrix(matrix));
    }

    @Test
    void shouldReturnFlatMatrix_ifNumOfRowsMoreThanNumOfColumns() {
        // given
        int[][] matrix = new int[][]
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9},
                        {10, 11, 12}
                };

        int[] flatMatrix = new int[]{1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12};

        // then
        assertArrayEquals(flatMatrix, Problem4.flattenMatrix(matrix));
    }

    @Test
    void shouldReturnFlatMatrix_ifNumOfColumnsMoreThanNumOfRows() {
        // given
        int[][] matrix = new int[][]
                {
                        {1, 2, 3, 0},
                        {4, 5, 6, 0},
                        {7, 8, 9, 0}
                };

        int[] flatMatrix = new int[]{1, 4, 7, 2, 5, 8, 3, 6, 9, 0, 0, 0};

        //
        // then
        assertArrayEquals(flatMatrix, Problem4.flattenMatrix(matrix));
    }
}
