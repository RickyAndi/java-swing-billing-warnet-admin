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
import com.mycompany.application.exceptions.AmountPaidByClientIsNotEnoughException;
import com.mycompany.application.exceptions.OldPasswordDoesNotMatchException;
import com.mycompany.application.exceptions.RepeatPasswordDoesNotMatchException;
import com.mycompany.application.exceptions.UserDoesNotExistException;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.SettingRepository;
import com.mycompany.application.repositories.TransactionRepository;
import com.mycompany.application.repositories.UserRepository;
import com.mycompany.application.services.AdminConfigService;
import org.hibernate.Session;
import org.joda.time.DateTime;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rickyandhi
 */
public class ApplicationState {

    private AdminConfigService config;

    private Session session;

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
            AdminConfigService config,
            Session session,
            UserRepository userRepository,
            ComputerRepository computerRepository,
            SettingRepository settingRepository,
            TransactionRepository transactionRepository
    ) {
        this.config = config;
        this.session = session;
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
        List<Computer> computers = this.computerRepository.getComputers();
        clientComputers = computers;
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

    public void getTransactionsAction(
            Optional<String> optionalDate,
            Optional<String> optionalUsername
    ) throws Exception {

        List<Integer> statuses = Arrays.asList(
            TransactionStatusEnum.NOT_PAID.value,
            TransactionStatusEnum.PAID.value
        );

        Date startDate = null;
        Date endDate = null;

        if (optionalDate.isPresent()) {
            SimpleDateFormat startDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            startDate = startDateFormat.parse(optionalDate.get());
            endDate = new DateTime(startDate).plusDays(1).toDate();
        } else {
            startDate = new DateTime(new Date()).withTime(0,0,0,0).toDate();
            endDate = new DateTime(startDate).plusDays(1).toDate();
            System.out.println(startDate);
            System.out.println(endDate);
        }

        List<Transaction> transactions = this.transactionRepository
            .getTransactions(
                statuses,
                optionalUsername,
                Optional.of(startDate),
                Optional.of(endDate)
        );
        setTransactions(transactions);
    }

    public void updateSettingsAction(String warnetName, String warnetAddress, Double pricePerHour) {
        org.hibernate.Transaction transaction = session.beginTransaction();

        Setting warnetNameSetting = settingRepository
                .updateStringValue(getInternetCafeNameSetting(), warnetName);
        setInternetCafeNameSetting(warnetNameSetting);
        Setting warnetAddressSetting = settingRepository
                .updateStringValue(getInternetCafeAddressSetting(), warnetAddress);
        setInternetCafeAddressSetting(warnetAddressSetting);
        Setting pricePerHourSetting = settingRepository
                .updateDecimalValue(getCostPerHourSetting(), pricePerHour);

        setCostPerHourSetting(pricePerHourSetting);

        transaction.commit();
    }

    public void updateProfileAction(
            String username,
            String oldPassword,
            String newPassword,
            String repeatNewPassword
    ) throws NoSuchAlgorithmException, OldPasswordDoesNotMatchException, RepeatPasswordDoesNotMatchException {
        Optional<User> optionalCurrentUser = getCurrentUser();
        if (optionalCurrentUser.isPresent()) {

            User user = optionalCurrentUser.get();
            org.hibernate.Transaction transaction = session
                    .beginTransaction();
            userRepository
                    .updateUsername(user, username);
            transaction.commit();

            if (isUserChangingItsPassword(newPassword)) {
                String hashedOldPasswordOfUser = user.getPassword();
                String hashedOldPassword = turnPasswordToHash(oldPassword);
                if (!hashedOldPasswordOfUser.equals(hashedOldPassword)) {
                    throw new OldPasswordDoesNotMatchException(ErrorMessageEnum.OLD_PASSWORD_DOES_NOT_MATCH.message);
                }

                if (!newPassword.equals(repeatNewPassword)) {
                    throw new RepeatPasswordDoesNotMatchException(ErrorMessageEnum.NEW_PASSWORD_DOES_NOT_MATCH.message);
                }

                org.hibernate.Transaction changePasswordTransaction = session
                        .beginTransaction();
                String hashedNewPassword = turnPasswordToHash(newPassword);
                userRepository
                        .updatePassword(user, hashedNewPassword);
                changePasswordTransaction.commit();
            }

            setCurrentUser(Optional.of(user));
        }
    }

    public void updateSelectedTransactionStatusToPaidAction(Double paidAmountByClient) throws AmountPaidByClientIsNotEnoughException {
        Optional<Transaction> optionalSelectedTransaction = getSelectedTransaction();
        Transaction selectedTransaction = optionalSelectedTransaction.get();

        if (selectedTransaction.getAmountToBePaid() > paidAmountByClient) {
            throw new AmountPaidByClientIsNotEnoughException(
                    ErrorMessageEnum.AMOUNT_PAID_BY_CLIENT_IS_NOT_ENOUGH.message
            );
        }

        Double changeAmount = paidAmountByClient - selectedTransaction.getAmountToBePaid();

        org.hibernate.Transaction changeTransactionStatusTransaction = session.beginTransaction();
        transactionRepository
                .changeTransactionStatusToPaid(selectedTransaction, paidAmountByClient, changeAmount);

        changeTransactionStatusTransaction.commit();

        setSelectedTransaction(Optional.of(selectedTransaction));
    }

    private Boolean isUserChangingItsPassword(String password) {
        return !password.equals("");
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

    public String getSocketServerHost() {
        return config.getSocketServerHost().get();
    }

    public Long getSocketServerPort() {
        return config.getSocketServerPort().get();
    }
}
