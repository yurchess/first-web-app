package mitroshin.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * A utility class to build, access and close hibernate {@code SessionFactory}.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            return null;
        }
    }

    /**
     * Accesses (or builds) the current instance of {@code SessionFactory}
     * @return Current instance of {@code SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Closing current instance of {@code SessionFactory}
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}
