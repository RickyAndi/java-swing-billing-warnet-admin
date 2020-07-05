/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.User;
import com.mycompany.application.enums.ErrorMessageEnum;
import com.mycompany.application.exceptions.UserDoesNotExistException;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.UserRepository;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rickyandhi
 */
public class ApplicationState {

    private Optional<User> currentUser;
    private List<Computer> clientComputers;

    private UserRepository userRepository;
    private ComputerRepository computerRepository;

    public ApplicationState(
            UserRepository userRepository,
            ComputerRepository computerRepository
    ) {

        this.userRepository = userRepository;
        this.computerRepository = computerRepository;
    }

    public void loginAction(String username, String plainPassword) throws Exception {

        String hashedPassword = turnPasswordToHash(plainPassword);
        Optional<User> userOptional = this.userRepository
                .getUserByUsernameAndPassword(username, hashedPassword);

        setCurrentUser(userOptional);

        if (!isUserBeingLoggedIn()) {
            throw new UserDoesNotExistException(ErrorMessageEnum.ADMIN_DOES_NOT_EXIST.message);
        }
    }

    public void getClientComputersAction() {
        clientComputers = this.computerRepository.getComputers();
    }

    private String turnPasswordToHash(String plainPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(plainPassword.getBytes());
        byte[] digest = md.digest();
        String hashedPassword = DatatypeConverter
                .printHexBinary(digest).toLowerCase();
        return hashedPassword;
    }

    public Optional<User> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Optional<User> currentUser) {
        this.currentUser = currentUser;
    }

    public List<Computer> getClientComputers() {
        return clientComputers;
    }

    public Boolean isUserBeingLoggedIn() {
        return this.currentUser.isPresent();
    }
}
