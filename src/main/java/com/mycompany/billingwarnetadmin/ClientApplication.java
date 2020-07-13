package com.mycompany.billingwarnetadmin;

import com.mycompany.application.ClientApplicationState;
import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.Setting;
import com.mycompany.application.entities.Transaction;
import com.mycompany.application.entities.User;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.SettingRepository;
import com.mycompany.application.repositories.TransactionRepository;
import com.mycompany.application.services.ClientConfigService;
import com.mycompany.gui.ClientWindows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.*;
import java.util.Optional;

public class ClientApplication {
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


        ComputerRepository computerRepository = new ComputerRepository(session);
        SettingRepository settingRepository = new SettingRepository(session);
        TransactionRepository transactionRepository = new TransactionRepository(session);

        ClientApplicationState applicationState = new ClientApplicationState(
                session,
                settingRepository,
                computerRepository,
                transactionRepository
        );

        ClientConfigService clientConfigService = new ClientConfigService();
        applicationState.setCurrentComputerName(
                Optional.of(clientConfigService.getClientComputerName())
        );

        try {
            applicationState.getInitialDataAction();
            ClientWindows clientWindows = new ClientWindows(applicationState);
            clientWindows.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
