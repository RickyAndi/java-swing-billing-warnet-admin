package com.mycompany.billingwarnetadmin;

import com.mycompany.application.ApplicationState;
import com.mycompany.application.entities.User;
import com.mycompany.application.repositories.UserRepository;
import com.mycompany.gui.MainWindow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Application {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        UserRepository userRepository = new UserRepository(session);

        ApplicationState applicationState = new ApplicationState(userRepository);

        MainWindow mainWindow = new MainWindow(applicationState);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow.setVisible(true);
            }
        });
    }
}
