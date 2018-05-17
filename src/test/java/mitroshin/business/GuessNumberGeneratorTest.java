package mitroshin.business;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GuessNumberGeneratorTest {

    @Test
    public void areDigitsUnique() throws Exception {
        int[] guessedNumber = GuessNumberGenerator.generate(10);
        Arrays.sort(guessedNumber);
        for (int i = 0; i < guessedNumber.length - 1; i++) {
            assertNotEquals(guessedNumber[i], guessedNumber[i + 1]);
        }
    }

    @Test
    public void areDigitsInCorrectRange() throws Exception {
        int[] guessedNumber = GuessNumberGenerator.generate(10);
        Arrays.sort(guessedNumber);
        for (int i = 0; i < guessedNumber.length; i++) {
            assertTrue(guessedNumber[i] >= 0 && guessedNumber[i] < 10);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceedingMaxSize() throws Exception {
        GuessNumberGenerator.generate(11);
    }
}