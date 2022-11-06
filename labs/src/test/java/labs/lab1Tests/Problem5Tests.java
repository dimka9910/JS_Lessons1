package labs.lab1Tests;

import labs.lab1.src.java.Problem5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Problem5Tests {

    @Test
    void shouldReturnTrue_ifStringIsSortedGeometricProgression() {
        // given
        String geometricProgression = "1,2,4,8,16";
        // then
        assertTrue(Problem5.isGeometricProgression(geometricProgression));
    }

    @Test
    void shouldReturnTrue_ifStringIsUnsortedGeometricProgression() {
        // given
        String geometricProgression = "16,2,8,1,4";
        // then
        assertTrue(Problem5.isGeometricProgression(geometricProgression));
    }

    @Test
    void shouldReturnTrue_ifStringIsNotGeometricProgression() {
        // given
        String geometricProgression = "16,2,8,1,4,73";
        // then
        assertFalse(Problem5.isGeometricProgression(geometricProgression));
    }
}
