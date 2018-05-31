package mitroshin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Score implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ScoreId;
    private String login;
    private double score;
    private int attempts;

    public Score() {
    }

    public Score(String login, double score, int attempts) {
        this.login = login;
        this.score = score;
        this.attempts = attempts;
    }

    public long getScoreId() {
        return ScoreId;
    }

    public void setScoreId(long scoreId) {
        ScoreId = scoreId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
