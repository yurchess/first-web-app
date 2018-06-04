package mitroshin.util;

import mitroshin.data.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener started");
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener destroyed");

    /*    final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            final Driver d = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(d);
                System.out.println(String.format("Driver %s deregistered", d));
            } catch (SQLException e) {
                System.out.println(String.format("Error deregistering driver %s", d));
                e.printStackTrace();
            }
        }*/

        HibernateUtil.shutdown();
    }
}
