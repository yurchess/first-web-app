package mitroshin.business;

public class GuessedNumber {
    private static final int DEFAULT_DIGITS_COUNT = 4;
    private int[] guessedNumber;

    public GuessedNumber(int digitsCount) {
        guessedNumber = GuessNumberGenerator.generate(digitsCount);
    }

    public GuessedNumber() {
        this(DEFAULT_DIGITS_COUNT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : guessedNumber) {
            sb.append(i);
        }
        return sb.toString();
    }

    public int[] getGuessedNumber() {
        return guessedNumber;
    }
}
