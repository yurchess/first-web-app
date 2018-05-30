package mitroshin.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//            serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
//                    .build();
//
//            return configuration.buildSessionFactory(serviceRegistry);
//        } catch (Exception e) {
//            System.err.println("Initial SessionFactory creation failed. " + e);
//            throw new ExceptionInInitializerError(e);
//        }
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

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
