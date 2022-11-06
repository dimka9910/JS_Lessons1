package labs.lab1Tests;

import labs.lab1.src.java.Problem3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Problem3Tests {

    @Test
    void shouldSolveKnapsackProblem_ifWeightsAndValuesAreRegular() {
        //Не работает сам метод подсчёта
        // given
        double[] values = new double[]{2.0, 1.0, 5.0};
        double[] weights = new double[]{1.0, 1.0, 2.0};
        double maximumWeightCapability = 2.5;
        double correctAnswer = 5.0;
        // when
        assertEquals(correctAnswer,Problem3.solveKnapsackProblem(values, weights, maximumWeightCapability, values.length));
    }

    @Test
    void shouldNotSolveKnapsackProblem_ifMaximumWeihtCapabilityIsZero() {
        //Не работает сам метод подсчёта
        // given
        double[] values = new double[]{2.0, 1.0, 5.0};
        double[] weights = new double[]{1.0, 1.0, 2.0};
        double maximumWeightCapability = 0.0;
        double correctAnswer = 0;
        // when
        assertEquals(correctAnswer,Problem3.solveKnapsackProblem(values, weights, maximumWeightCapability, values.length));
    }

    @Test
    void shouldNotSolveKnapsackProblem_ifNumOfWeightsAreNotEqualNumOfValues() {
        //Не работает сам метод подсчёта
        // given
        double[] values = new double[]{2.0, 1.0, 5.0};
        double[] weights = new double[]{1.0, 1.0};
        double maximumWeightCapability = 2.5;
        double correctAnswer = 0;
        // when
        assertEquals(correctAnswer,Problem3.solveKnapsackProblem(values, weights, maximumWeightCapability, values.length));
    }
}
