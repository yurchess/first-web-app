package mitroshin.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class GuessedNumberHintTest {

    private String guess;
    private String expectedHint;
    private boolean expectedMatch;
    private GuessedNumberHint guessedNumberHint;

    public GuessedNumberHintTest(String guess, String expectedHint, boolean expectedMatch) {
        this.guess = guess;
        this.expectedHint = expectedHint;
        this.expectedMatch = expectedMatch;
    }

    @Before
    public void init() {
        guessedNumberHint = new GuessedNumberHint(new int[] {7,3,2,8});
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {"0819", "0B1C", false},
                {"4073", "0B2C", false},
                {"5820", "1B1C" /*В примере 0Б1К - ошибка*/, false},
                {"3429", "1B1C", false},
                {"5960", "0B0C", false},
                {"7238", "2B2C", false},
                {"7328", "4B0C", true}
        });
    }

    @Test
    public void isHintCorrect() throws Exception {
        String hint = guessedNumberHint.hint(guess);
        assertTrue(String.format("Guess: %s. Actual hint: %s. Expected hint: %s", guess, hint, expectedHint) , hint.equals(expectedHint));
    }

    @Test
    public void doesMatchCorrectlyDefined() throws Exception {
        assertEquals(guessedNumberHint.isGuessCorrect(guess), expectedMatch);
    }
}