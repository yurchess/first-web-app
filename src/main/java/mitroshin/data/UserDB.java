package mitroshin.data;

import mitroshin.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * A utility class for operations with {@code User} table in the database.
 */
public class UserDB {

    /**
     * Inserts a new {@code User} object in database table {@code User}.
     *
     * @param user A {@code User} object to insert into database.
     *
     * @return {@code true} if the {@code User} object in inserted successfully, {@code false} if not.
     */
    public static boolean insert(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public static void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.remove(session.merge(user));
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Selects a {@code User} from the database by user login.
     * @param login User login.
     * @return {@code User} if the user is presented in database and
     * {@code null} if there is no user with such {@code login}.
     */
    public static User selectUser(String login) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String qString = "SELECT u FROM User u " +
                "WHERE u.login = :login";
        TypedQuery<User> q = session.createQuery(qString, User.class);
        q.setParameter("login", login);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * Checks if there is user with this {@code login} in database.
     * @param login User login to check.
     * @return {@code true} if there is user with such {@code login} and
     * {@code false} if not.
     */
    public static boolean loginExists(String login) {
        User user = selectUser(login);
        return user != null;
    }

    /**
     * Checks whether passed login and password are correct.
     * @param login User login.
     * @param password User password.
     * @return {@code true} if user with this {@code login} is presented in database
     * and the {@code password} matches one in database,
     * otherwise returns {@code false}
     */
    public static boolean isAuthOK(String login, String password) {
        if (loginExists(login)) {
            User user = selectUser(login);
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }
}
