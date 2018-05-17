package mitroshin.business;

public class GuessedNumberHint {
    private int[] guessedNumber;
    private final char BULL = 'B';
    private final char COW = 'C';

    public GuessedNumberHint(int[] guessedNumber) {
        this.guessedNumber = guessedNumber;
    }

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

    public String hint(String guess) {
        int[] intGuess = new int[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            intGuess[i] = Character.getNumericValue(guess.charAt(i));
        }
        return hint(intGuess);
    }
}
