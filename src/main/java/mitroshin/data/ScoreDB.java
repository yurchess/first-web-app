package mitroshin.data;

import mitroshin.entities.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class ScoreDB {

    public static List<Score> getScoresRank(int count) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String qString = "SELECT s FROM Score s " +
                    "ORDER BY 'score'";
            Query<Score> q = session.createQuery(qString, Score.class);
            q.setMaxResults(count);
            List<Score> scores = q.getResultList();
            return scores;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public static Score selectScore(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String qString = "SELECT s FROM Score s " +
                "WHERE s.login = :login";
        Query<Score> q = session.createQuery(qString, Score.class);
        q.setParameter("login", login);

        try {
            Score score = q.getSingleResult();
            return score;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public static boolean loginExists(String login) {
        Score score = selectScore(login);
        return score != null;
    }

    public static void updateScore(String login, int lastScore) {
        if (loginExists(login)) {
            Score score = selectScore(login);
            double newScore = (score.getScore() * score.getAttempts() + lastScore) / (score.getAttempts() + 1);
            score.setScore(newScore);
            score.setAttempts(score.getAttempts() + 1);
            update(score);
        } else {
            Score score = new Score(login, lastScore, 1);
            insert(score);
        }
    }

    private static void insert(Score score) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.save(score);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    private static void update(Score score) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.update(score);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
