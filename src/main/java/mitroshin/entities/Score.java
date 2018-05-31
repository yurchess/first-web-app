package mitroshin.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Score implements Serializable {

    private double score;
    private int attempts;

    public Score() {
    }

    public Score(double score, int attempts) {
        this.score = score;
        this.attempts = attempts;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
