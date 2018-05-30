package mitroshin.data;

import mitroshin.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDB {

    public static void insert(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();
        try {
            session.merge(user);
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

    public static User selectUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String qString = "SELECT u FROM User u " +
                "WHERE u.email = :email";
        TypedQuery<User> q = session.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public static boolean emailExists(String email) {
        User user = selectUser(email);
        return user != null;
    }
}
