package mitroshin.data;

import mitroshin.entities.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.Comparator;
import java.util.List;

/**
 * A utility class for operations with {@code Score} table in the database.
 */
public class ScoreDB {

    /**
     * Returns a list of first {@code count} {@code Score} objects
     * sorted by {@code score} attribute in descendant order retieved from the
     * database.
     *
     * @param count Size of returned list.
     * @return A list of {@code Score} objects
     */
    public static List<Score> getScoresRank(int count) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String qString = "SELECT s FROM Score s " +
                    "ORDER BY s.score";
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

    /**
     * Select {@code Score} object from the database by {@code login} value.
     *
     * @param login User login.
     * @return {@code Score} object of the appropriate user.
     */
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

    /**
     * Checks whether {@code login} exists in database or not.
     *
     * @param login User login to check.
     * @return {@code true} if {@code login} exists in database.
     * {@code false} if not.
     */
    public static boolean loginExists(String login) {
        Score score = selectScore(login);
        return score != null;
    }

    /**
     * Updates a user score in database.
     *
     * @param login     User login.
     * @param lastScore A score which is going to be added to an average score stored in database.
     */
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

    /**
     * Inserts a new {@code Score} entity into database {@code Score} table.
     *
     * @param score {@code Score} object to insert into database.
     */
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

    /**
     * Updates an existing {@code Score} entity in database {@code Score} table.
     *
     * @param score A {@code Score} object to be updated in database.
     */
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
