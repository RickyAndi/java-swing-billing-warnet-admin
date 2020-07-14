package com.mycompany.billingwarnetadmin;

import com.mycompany.application.ClientApplicationState;
import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.Setting;
import com.mycompany.application.entities.Transaction;
import com.mycompany.application.entities.User;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.SettingRepository;
import com.mycompany.application.repositories.TransactionRepository;
import com.mycompany.application.services.AdminConfigService;
import com.mycompany.application.services.ClientConfigService;
import com.mycompany.gui.ClientWindows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.*;
import java.util.Optional;
import java.util.Properties;

public class ClientApplication {
    public static void main(String[] args) {
        try {

            ClientConfigService config = new ClientConfigService(
                new String[] {
                    System.getProperty("user.dir") + "/client.config.json",
                    System.getProperty("user.dir") + "/src/main/resources/config/client.config.json"
                }
            );

            Properties properties = new Properties();
            properties.put("hibernate.connection.url", config.getDatabaseConnectionUrl().get());
            properties.put("hibernate.connection.username", config.getDatabaseUsername().get());
            properties.put("hibernate.connection.password", config.getDatabasePassword().get());

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Computer.class);
            configuration.addAnnotatedClass(Setting.class);
            configuration.addAnnotatedClass(Transaction.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.openSession();


            ComputerRepository computerRepository = new ComputerRepository(session);
            SettingRepository settingRepository = new SettingRepository(session);
            TransactionRepository transactionRepository = new TransactionRepository(session);

            ClientApplicationState applicationState = new ClientApplicationState(
                    config,
                    session,
                    settingRepository,
                    computerRepository,
                    transactionRepository
            );


            applicationState.setCurrentComputerName(
                    Optional.of(config.getComputerName().get())
            );

            applicationState.getInitialDataAction();
            ClientWindows clientWindows = new ClientWindows(applicationState);
            clientWindows.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
