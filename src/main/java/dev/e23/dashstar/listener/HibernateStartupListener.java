package dev.e23.dashstar.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@WebListener
public class HibernateStartupListener implements ServletContextListener {

    private EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            emf = Persistence.createEntityManagerFactory("default");
        } catch (PersistenceException ex) {
            System.err.println("Database connection failed. Server will shut down.");
            shutdownServer();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    private void shutdownServer() {
        System.exit(1);
    }
}
