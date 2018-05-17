package mitroshin.business;

import java.io.Serializable;

public class Attempt implements Serializable{
    private final String guess;
    private final String hint;

    public Attempt(String guess, String hint) {
        this.guess = guess;
        this.hint = hint;
    }

    public String getGuess() {
        return guess;
    }

    public String getHint() {
        return hint;
    }
}
