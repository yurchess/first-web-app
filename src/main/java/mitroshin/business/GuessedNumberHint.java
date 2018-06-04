package mitroshin.business;

/**
 * This class checks the user guess and generates a hint to the invalid guess.
 */
public class GuessedNumberHint {
    private int[] guessedNumber;
    private final char BULL = 'B';
    private final char COW = 'C';

    /**
     * @param guessedNumber
     *        Guessed number to remember.
     */
    public GuessedNumberHint(int[] guessedNumber) {
        this.guessedNumber = guessedNumber;
    }

    /**
     * Checks whether guess is correct or not.
     * @param guess
     *        A guess (as a {@code String} object) to check.
     * @return {@code true} if guess is correct and {@code false} if not.
     */
    public boolean isGuessCorrect(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            if (Character.getNumericValue(guess.charAt(i)) != guessedNumber[i]) {
                return false;
            }
        }
        return true;
    }

    private String hint(int[] guess) {
        int bullCounter = 0;
        int cowCounter = 0;

        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == guessedNumber[i]) {
                bullCounter++;
            } else {
                for (int j = 0; j < guess.length; j++) {
                    if (guess[i] == guessedNumber[j]) {
                        cowCounter++;
                    }
                }
            }
        }

        return String.format("%d%c%d%c", bullCounter, BULL, cowCounter, COW);
    }

    /**
     * Returns a hint to the passed guess.
     * @param guess
     *        User guess as a {@code String} object.
     * @return A hint as a {@code String} object.
     */
    public String hint(String guess) {
        int[] intGuess = new int[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            intGuess[i] = Character.getNumericValue(guess.charAt(i));
        }
        return hint(intGuess);
    }
}
