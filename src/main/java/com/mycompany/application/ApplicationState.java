/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

import com.mycompany.application.entities.User;
import com.mycompany.application.enums.ErrorMessage;
import com.mycompany.application.exceptions.UserDoesNotExistException;
import com.mycompany.application.repositories.UserRepository;

import java.util.Optional;

/**
 *
 * @author rickyandhi
 */
public class ApplicationState {

    private Optional<User> currentUser;

    private UserRepository userRepository;

    public ApplicationState(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Optional<User> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Optional<User> currentUser) {
        this.currentUser = currentUser;
    }

    public void login(String username, String plainPassword) throws UserDoesNotExistException {
        Optional<User> userOptional = this.userRepository
                .getUserByUsernameAndPassword(username, plainPassword);

        setCurrentUser(userOptional);

        if (!isUserBeingLoggedIn()) {
            throw new UserDoesNotExistException(ErrorMessage.ADMIN_DOES_NOT_EXIST.message);
        }
    }

    public Boolean isUserBeingLoggedIn() {
        return this.currentUser.isPresent();
    }
}
