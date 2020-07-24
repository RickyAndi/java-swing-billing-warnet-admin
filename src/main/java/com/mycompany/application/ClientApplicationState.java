package com.mycompany.application;

import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.Setting;
import com.mycompany.application.entities.Transaction;
import com.mycompany.application.enums.ErrorMessageEnum;
import com.mycompany.application.enums.SettingNameEnum;
import com.mycompany.application.enums.TransactionStatusEnum;
import com.mycompany.application.exceptions.ComputerClientDoesNotExistException;
import com.mycompany.application.exceptions.ComputerNameIsNotSetException;
import com.mycompany.application.repositories.ComputerRepository;
import com.mycompany.application.repositories.SettingRepository;
import com.mycompany.application.repositories.TransactionRepository;
import com.mycompany.application.services.AdminConfigService;
import com.mycompany.application.services.ClientConfigService;
import org.hibernate.Session;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ClientApplicationState {

    private ClientConfigService config;

    private Optional<String> currentComputerName;
    private Optional<String> currentUsername = Optional.empty();

    private Optional<Computer> currentComputer = Optional.empty();

    private Optional<Transaction> currentTransaction = Optional.empty();

    private Optional<Setting> warnetNameSetting = Optional.empty();
    private Optional<Setting> pricePerHourSetting = Optional.empty();
    private Optional<Setting> tariffIncreasingTimeSetting = Optional.empty();

    private Session session;
    private SettingRepository settingRepository;
    private ComputerRepository computerRepository;
    private TransactionRepository transactionRepository;

    public ClientApplicationState(
            ClientConfigService config,
            Session session,
            SettingRepository settingRepository,
            ComputerRepository computerRepository,
            TransactionRepository transactionRepository
    ) {
        this.config = config;
        this.session = session;
        this.settingRepository = settingRepository;
        this.computerRepository = computerRepository;
        this.transactionRepository = transactionRepository;
    }

    public void setCurrentComputerName(Optional<String> currentComputerName) {
        this.currentComputerName = currentComputerName;
    }

    public Optional<String> getCurrentComputerName() {
        return currentComputerName;
    }

    public void setCurrentComputer(Optional<Computer> currentComputer) {
        this.currentComputer = currentComputer;
    }

    public Optional<Computer> getCurrentComputer() {
        return currentComputer;
    }

    public void setCurrentUsername(Optional<String> currentUsername) {
        this.currentUsername = currentUsername;
    }

    public Optional<String> getCurrentUsername() {
        return currentUsername;
    }

    public void setWarnetNameSetting(Optional<Setting> warnetNameSetting) {
        this.warnetNameSetting = warnetNameSetting;
    }

    public Optional<Setting> getWarnetNameSetting() {
        return warnetNameSetting;
    }

    public void setPricePerHourSetting(Optional<Setting> pricePerHourSetting) {
        this.pricePerHourSetting = pricePerHourSetting;
    }

    public Optional<Setting> getPricePerHourSetting() {
        return pricePerHourSetting;
    }

    public void setTariffIncreasingTimeSetting(Optional<Setting> tariffIncreasingTimeSetting) {
        this.tariffIncreasingTimeSetting = tariffIncreasingTimeSetting;
    }

    public Optional<Setting> getTariffIncreasingTimeSetting() {
        return tariffIncreasingTimeSetting;
    }

    public void setCurrentTransaction(Optional<Transaction> currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Optional<Transaction> getCurrentTransaction() {
        return currentTransaction;
    }

    public Long getComputerCurrentTimeAndLastStartTimeDifferencesInMinutes() {
        Computer computer = getCurrentComputer().get();
        Long startTimestamp = computer.getLastStart().getTime();
        Long currentTimestamp = new Date().getTime();
        Long differences = TimeUnit.MILLISECONDS.toMinutes(currentTimestamp - startTimestamp);
        return differences;
    }

    public Long getComputerCurrentTimeAndLastStartTimeDifferencesInSeconds() {
        Computer computer = getCurrentComputer().get();
        Long startTimestamp = computer.getLastStart().getTime();
        Long currentTimestamp = new Date().getTime();
        Long differences = TimeUnit.MILLISECONDS.toSeconds(currentTimestamp - startTimestamp);
        return differences;
    }

    public Double getCurrentTariff() {
        Setting currentPricePerHourSetting = getPricePerHourSetting().get();
        Optional<Setting> costIncreasingPerMinuteSettingOptional = getTariffIncreasingTimeSetting();
        Setting costIncreasingPerMinuteSetting = costIncreasingPerMinuteSettingOptional.get();
        Long differences = getComputerCurrentTimeAndLastStartTimeDifferencesInMinutes();
        Long differencesRemainder = differences % new Long(costIncreasingPerMinuteSetting
                .getIntegerValue());
        Long timesOfPerMinute = (differences - differencesRemainder) / new Long(costIncreasingPerMinuteSetting
                .getIntegerValue());

        Double costPerOneMinute = currentPricePerHourSetting.getDecimalValue() / 60.0;
        Double costPerMinutesSetting = costPerOneMinute * costIncreasingPerMinuteSetting
                .getIntegerValue().doubleValue();
        return timesOfPerMinute * costPerMinutesSetting;
    }

    public void getInitialDataAction()
        throws ComputerNameIsNotSetException, ComputerClientDoesNotExistException
    {
        if (!getCurrentComputerName().isPresent()) {
            throw new ComputerNameIsNotSetException(
                ErrorMessageEnum.CLIENT_COMPUTER_NAME_IS_NOT_SET.message
            );
        }

        Optional<Computer> currentComputer = computerRepository
                .getComputerByName(getCurrentComputerName().get());
        if (!currentComputer.isPresent()) {
            throw new ComputerClientDoesNotExistException(
                ErrorMessageEnum.CLIENT_COMPUTER_NAME_IS_NOT_SET.message
            );
        }
        setCurrentComputer(currentComputer);

        Optional<Setting> warnetNameSetting = settingRepository
                .getSettingByName(SettingNameEnum.NAMA_WARNET.name);
        setWarnetNameSetting(warnetNameSetting);
    }

    public void loginAction(String username) {

        setCurrentUsername(Optional.of(username));

        Computer currentComputer = getCurrentComputer().get();

        Optional<Setting> pricePerHourSettingOptional = settingRepository
                .getSettingByName(SettingNameEnum.BIAYA_PER_JAM.name);
        setPricePerHourSetting(pricePerHourSettingOptional);
        Setting pricePerHourSetting = pricePerHourSettingOptional.get();

        Optional<Setting> tariffIncreasingTimeSettingOptional = settingRepository
                .getSettingByName(SettingNameEnum.BIAYA_DITAMBAHKAN_SETIAP_BERAPA_MENIT.name);
        setTariffIncreasingTimeSetting(tariffIncreasingTimeSettingOptional);

        Date currentDateTime = new Date();

        org.hibernate.Transaction updateComputerTransaction = session.beginTransaction();
        computerRepository.updateComputerToActiveStatus(
                currentComputer,
                username,
                pricePerHourSetting.getDecimalValue(),
                new Date()
        );
        Transaction currentTransaction = transactionRepository
            .createTransaction(
                currentComputer,
                username,
                currentDateTime,
                TransactionStatusEnum.ACTIVE.value
            );

        updateComputerTransaction.commit();

        setCurrentTransaction(Optional.of(currentTransaction));
    }

    public void logoutAction() {
        Transaction currentTransaction = getCurrentTransaction().get();
        Computer currentComputer = getCurrentComputer().get();
        Double currentTariff = getCurrentTariff();
        Date endOnDateTime = new Date();

        org.hibernate.Transaction logoutTransaction = session.beginTransaction();
        transactionRepository.stopRunningTransaction(currentTransaction, endOnDateTime, currentTariff);
        computerRepository.markAsInactive(currentComputer);
        logoutTransaction.commit();
    }

    public String getSocketServerHost() {
        return config.getSocketServerHost().get();
    }

    public Long getSocketServerPort() {
        return config.getSocketServerPort().get();
    }
}
