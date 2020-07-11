package com.mycompany.application.repositories;

import com.mycompany.application.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class UserRepository {
    private Session session;

    public UserRepository(Session session) {
        this.session = session;
    }

    public Optional<User> getUserByUsernameAndPassword(String username, String hashedPassword) {
        Query query = session.createQuery("from User U where U.username = :username and U.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", hashedPassword);

        return Optional.ofNullable((User) query.uniqueResult());
    }

    public void updateUsername(User user, String username) {
        user.setUsername(username);
        session.update(user);
    }

    public void updatePassword(User user, String hashedPassword) {
        user.setPassword(hashedPassword);
        session.update(user);
    }
}
