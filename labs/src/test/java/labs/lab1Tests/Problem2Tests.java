package labs.lab1Tests;

import labs.lab1.src.java.Problem2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Problem2Tests {

    @Test
    void shouldReturnSegregateEvenAndOddNumbers_ifArrayIsMixed() {
        // given
        int[] mixedEvenAndOdds = new int[]{10, 3, 4, 5, 6, 7, 234, 432, 57, 712};
        int[] segregatedEvenAndOdds = new int[]{10, 4, 6, 234, 432, 712, 3, 5, 7, 57};

        // when
        assertArrayEquals(segregatedEvenAndOdds, Problem2.segregateEvenAndOddNumbers(mixedEvenAndOdds));
    }

    @Test
    void shouldReturnSegregateEvenAndOddNumbers_ifArrayIsAllOdds() {
        // given
        int[] mixedEvenAndOdds = new int[]{2, 6, 8, 10, 12, 14, 16, 18, 20, 22};
        int[] segregatedEvenAndOdds = new int[]{2, 6, 8, 10, 12, 14, 16, 18, 20, 22};

        // when
        assertArrayEquals(segregatedEvenAndOdds, Problem2.segregateEvenAndOddNumbers(mixedEvenAndOdds));
    }

    @Test
    void shouldReturnSegregateEvenAndOddNumbers_ifArrayIsAllEven() {
        // given
        int[] mixedEvenAndOdds = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int[] segregatedEvenAndOdds = new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        // when
        assertArrayEquals(segregatedEvenAndOdds, Problem2.segregateEvenAndOddNumbers(mixedEvenAndOdds));
    }
}
