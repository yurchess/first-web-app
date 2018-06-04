package mitroshin.business;

import java.io.Serializable;

public class Attempt implements Serializable{
    private final String guess;
    private final String hint;

    public Attempt(String guess, String hint) {
        this.guess = guess;
        this.hint = hint;
    }

    /**
     * Returns the user guess as a String object.
     * @return User guess
     */
    public String getGuess() {
        return guess;
    }

    /**
     * Returns the hint to the appropriate user guess as a String object.
     * @return Hint to the appropriate user guess
     */
    public String getHint() {
        return hint;
    }
}
