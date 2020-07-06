/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.Setting;
import com.mycompany.application.entities.Transaction;
import com.mycompany.application.entities.User;
import com.mycompany.application.enums.ErrorMessageEnum;
import com.mycompany.application.enums.SettingNameEnum;
import com.mycompany.application.enums.TransactionStatusEnum;
import com.mycompany.application.exceptions.UserDoesNotExistException;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.SettingRepository;
import com.mycompany.application.repositories.TransactionRepository;
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

    private Optional<User> currentUser = Optional.empty();

    private Setting internetCafeNameSetting;
    private Setting internetCafeAddressSetting;
    private Setting costPerHourSetting;
    private Setting costIncreasingPerMinuteSetting;

    private List<Computer> clientComputers;
    private List<Transaction> transactions;
    private Optional<Transaction> selectedTransaction = Optional.empty();

    private UserRepository userRepository;
    private ComputerRepository computerRepository;
    private SettingRepository settingRepository;
    private TransactionRepository transactionRepository;

    public ApplicationState(
            UserRepository userRepository,
            ComputerRepository computerRepository,
            SettingRepository settingRepository,
            TransactionRepository transactionRepository
    ) {

        this.userRepository = userRepository;
        this.computerRepository = computerRepository;
        this.settingRepository = settingRepository;
        this.transactionRepository = transactionRepository;
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

    public void getInternetCafeSettingsAction() {
        setInternetCafeNameSetting(settingRepository
                .getSettingByName(SettingNameEnum.NAMA_WARNET.name)
                .get()
        );

        setInternetCafeAddressSetting(settingRepository
                .getSettingByName(SettingNameEnum.ALAMAT_WARNET.name)
                .get()
        );

        setCostPerHourSetting(settingRepository
                .getSettingByName(SettingNameEnum.BIAYA_PER_JAM.name)
                .get()
        );

        setCostIncreasingPerMinuteSetting(settingRepository
                .getSettingByName(SettingNameEnum.BIAYA_DITAMBAHKAN_SETIAP_BERAPA_MENIT.name)
                .get()
        );
    }

    public void getTransactionsAction() {
        List<Transaction> transactions = this.transactionRepository
                .getTransactionsByStatusNot(TransactionStatusEnum.ACTIVE.value);
        setTransactions(transactions);
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

    public void setInternetCafeNameSetting(Setting internetCafeNameSetting) {
        this.internetCafeNameSetting = internetCafeNameSetting;
    }

    public void setInternetCafeAddressSetting(Setting internetCafeAddressSetting) {
        this.internetCafeAddressSetting = internetCafeAddressSetting;
    }

    public void setCostPerHourSetting(Setting costPerHourSetting) {
        this.costPerHourSetting = costPerHourSetting;
    }

    public void setCostIncreasingPerMinuteSetting(Setting costIncreasingPerMinuteSetting) {
        this.costIncreasingPerMinuteSetting = costIncreasingPerMinuteSetting;
    }

    public Setting getInternetCafeNameSetting() {
        return internetCafeNameSetting;
    }

    public Setting getInternetCafeAddressSetting() {
        return internetCafeAddressSetting;
    }

    public Setting getCostIncreasingPerMinuteSetting() {
        return costIncreasingPerMinuteSetting;
    }

    public Setting getCostPerHourSetting() {
        return costPerHourSetting;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setSelectedTransaction(Optional<Transaction> selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public Optional<Transaction> getSelectedTransaction() {
        return selectedTransaction;
    }
}
