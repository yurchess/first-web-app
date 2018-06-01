package mitroshin.data;

import mitroshin.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDB {

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

    public static boolean loginExists(String login) {
        User user = selectUser(login);
        return user != null;
    }

    public static boolean isAuthOK(String login, String password) {
        if (loginExists(login)) {
            User user = selectUser(login);
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }
}
