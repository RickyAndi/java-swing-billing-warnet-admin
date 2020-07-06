package com.mycompany.billingwarnetadmin;

import com.mycompany.application.ApplicationState;
import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.Setting;
import com.mycompany.application.entities.Transaction;
import com.mycompany.application.entities.User;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.SettingRepository;
import com.mycompany.application.repositories.TransactionRepository;
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
        configuration.addAnnotatedClass(Computer.class);
        configuration.addAnnotatedClass(Setting.class);
        configuration.addAnnotatedClass(Transaction.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        UserRepository userRepository = new UserRepository(session);
        ComputerRepository computerRepository = new ComputerRepository(session);
        SettingRepository settingRepository = new SettingRepository(session);
        TransactionRepository transactionRepository = new TransactionRepository(session);

        ApplicationState applicationState = new ApplicationState(
                userRepository,
                computerRepository,
                settingRepository,
                transactionRepository
        );

        MainWindow mainWindow = new MainWindow(applicationState);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow.setVisible(true);
            }
        });
    }
}
