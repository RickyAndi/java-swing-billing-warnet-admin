/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.application.ClientApplicationState;
import com.mycompany.application.entities.Computer;
import com.mycompany.application.entities.Setting;
import com.mycompany.application.enums.CurrencyAbbrevationEnum;
import com.mycompany.application.enums.ErrorMessageEnum;
import com.mycompany.application.enums.SocketEventNameEnum;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.simple.JSONObject;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author ADMIN
 */
public class ClientWindows extends javax.swing.JFrame {

    private ClientApplicationState applicationState;

    private Socket socket;

    private CardLayout dashboardPanelCardLayout;
    private CardLayout mainPanelCardLayout;
    private Optional<Timer> tariffAndTimeAnimation = Optional.empty();
    
    /**
     * Creates new form ClientWindows
     */
    public ClientWindows(ClientApplicationState clientApplicationState) throws Exception {
        this.applicationState = clientApplicationState;
        
        initComponents();
        
        this.changeWarnetNameLabel();
        
        mainPanelCardLayout = (CardLayout) mainPanel.getLayout();
        dashboardPanelCardLayout = (CardLayout) dashboardPanel.getLayout();

        this.initializeSocketConnection();
    }

    private void initializeSocketConnection() throws Exception {
        String socketServerHost = applicationState.getSocketServerHost();
        Long socketServerPort = applicationState.getSocketServerPort();
        System.out.println(socketServerPort);
        socket = IO.socket("http://" + socketServerHost + ":" + socketServerPort.toString());
        socket.connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        loginWarnetNameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtnStart = new javax.swing.JButton();
        loginUsernameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dashboardPanel = new javax.swing.JPanel();
        dashboardStatusPanel = new javax.swing.JPanel();
        dashboardStatusWarnetNameLabel = new javax.swing.JLabel();
        dashboardStatusUsernameTextField = new javax.swing.JTextField();
        dashboardStatusTimeTextField = new javax.swing.JTextField();
        dashboardStatusComputerNameTextField = new javax.swing.JTextField();
        dashboardStatusTariffTextField = new javax.swing.JTextField();
        dashboardStatusStopButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        dashboardLogoutConfirmationPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        logoutConfirmationYesButton = new javax.swing.JButton();
        logoutConfirmationBackButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        dashboardLoadingPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dashboardTarifInformationPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dashboardTariffTotalTextField = new javax.swing.JLabel();
        dashboardTarifToLoginButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.CardLayout());

        loginPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                loginPanelComponentShown(evt);
            }
        });
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginWarnetNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginWarnetNameLabel.setText("HomeInet.id");
        loginPanel.add(loginWarnetNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Username");
        loginPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        jLabel3.setText("Welcome , Please login first ... ");
        loginPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        BtnStart.setBackground(new java.awt.Color(121, 204, 231));
        BtnStart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnStart.setForeground(new java.awt.Color(255, 255, 255));
        BtnStart.setText("Start");
        BtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnStartActionPerformed(evt);
            }
        });
        loginPanel.add(BtnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 280, 31));
        loginPanel.add(loginUsernameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 280, 27));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dasboard Client Billing.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        loginPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 610, 430));

        mainPanel.add(loginPanel, "loginPanelCard");

        dashboardPanel.setLayout(new java.awt.CardLayout());

        dashboardStatusPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dashboardStatusPanelComponentShown(evt);
            }
        });
        dashboardStatusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboardStatusWarnetNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dashboardStatusWarnetNameLabel.setText("HomeInet.id");
        dashboardStatusPanel.add(dashboardStatusWarnetNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 120, -1));

        dashboardStatusUsernameTextField.setEditable(false);
        dashboardStatusUsernameTextField.setBackground(new java.awt.Color(255, 255, 255));
        dashboardStatusPanel.add(dashboardStatusUsernameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 220, 30));

        dashboardStatusTimeTextField.setEditable(false);
        dashboardStatusTimeTextField.setBackground(new java.awt.Color(255, 255, 255));
        dashboardStatusPanel.add(dashboardStatusTimeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 220, 30));

        dashboardStatusComputerNameTextField.setEditable(false);
        dashboardStatusComputerNameTextField.setBackground(new java.awt.Color(180, 234, 246));
        dashboardStatusComputerNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardStatusComputerNameTextFieldActionPerformed(evt);
            }
        });
        dashboardStatusPanel.add(dashboardStatusComputerNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 120, 30));

        dashboardStatusTariffTextField.setEditable(false);
        dashboardStatusTariffTextField.setBackground(new java.awt.Color(180, 234, 246));
        dashboardStatusPanel.add(dashboardStatusTariffTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 120, 30));

        dashboardStatusStopButton.setBackground(new java.awt.Color(251, 79, 79));
        dashboardStatusStopButton.setForeground(new java.awt.Color(255, 255, 255));
        dashboardStatusStopButton.setText("Stop");
        dashboardStatusStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardStatusStopButtonActionPerformed(evt);
            }
        });
        dashboardStatusPanel.add(dashboardStatusStopButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 340, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dasboard Client Billing.png"))); // NOI18N
        jLabel7.setText("jLabel6");
        dashboardStatusPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 610, 430));

        dashboardPanel.add(dashboardStatusPanel, "dashboardStatusPanelCard");

        dashboardLogoutConfirmationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Apakah anda yakin akan keluar ?");

        logoutConfirmationYesButton.setText("Ya");
        logoutConfirmationYesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutConfirmationYesButtonActionPerformed(evt);
            }
        });

        logoutConfirmationBackButton.setBackground(new java.awt.Color(255, 255, 255));
        logoutConfirmationBackButton.setText("Tidak");
        logoutConfirmationBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutConfirmationBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel5)
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutConfirmationBackButton)
                .addGap(18, 18, 18)
                .addComponent(logoutConfirmationYesButton)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutConfirmationBackButton)
                    .addComponent(logoutConfirmationYesButton))
                .addGap(43, 43, 43))
        );

        dashboardLogoutConfirmationPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 530, 190));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/Dasboard Client Billing.png"))); // NOI18N
        dashboardLogoutConfirmationPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 604, 426));

        dashboardPanel.add(dashboardLogoutConfirmationPanel, "dashboardLogoutConfirmationPanelCard");

        dashboardLoadingPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dashboardLoadingPanelComponentShown(evt);
            }
        });
        dashboardLoadingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/loading-gear.gif"))); // NOI18N
        jLabel8.setText("jLabel8");
        dashboardLoadingPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 190, 180));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/Dasboard Client Billing.png"))); // NOI18N
        dashboardLoadingPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 604, 426));

        dashboardPanel.add(dashboardLoadingPanel, "dashboardLoadingPanelCard");

        dashboardTarifInformationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Silahkan konfirmasi ke admin, terima kasih");

        jLabel11.setText("Total biaya anda,");

        dashboardTariffTotalTextField.setForeground(new java.awt.Color(0, 0, 255));
        dashboardTariffTotalTextField.setText("jLabel12");

        dashboardTarifToLoginButton.setText("Ok");
        dashboardTarifToLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardTarifToLoginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dashboardTariffTotalTextField)))
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dashboardTarifToLoginButton)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(dashboardTariffTotalTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(dashboardTarifToLoginButton)
                .addGap(40, 40, 40))
        );

        dashboardTarifInformationPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 530, 190));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/Dasboard Client Billing.png"))); // NOI18N
        dashboardTarifInformationPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 604, 426));

        dashboardPanel.add(dashboardTarifInformationPanel, "dashboardTarifInformationPanelCard");

        mainPanel.add(dashboardPanel, "dashboardPanelCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void loginPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_loginPanelComponentShown
        this.changeWarnetNameLabel();
    }//GEN-LAST:event_loginPanelComponentShown

    private void BtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnStartActionPerformed
        try {
            String currentUsername = loginUsernameTextField.getText();
            if (currentUsername.equals("")) {
                JOptionPane
                    .showMessageDialog(
                        this, 
                        ErrorMessageEnum.USERNAME_MUST_BE_NOT_EMPTY.message
                    );
            } else {
                applicationState.loginAction(currentUsername);

                JSONObject eventPayload = new JSONObject();
                eventPayload.put("computer_name", applicationState.getCurrentComputer().get().getName());
                socket.emit(SocketEventNameEnum.CLIENT_LOGGED_OUT.name, eventPayload);

                socket.emit(
                        SocketEventNameEnum.CLIENT_LOGGED_IN.name,
                        eventPayload
                );

                mainPanelCardLayout
                        .show(mainPanel, "dashboardPanelCard");
                dashboardPanelCardLayout
                        .show(dashboardPanel, "dashboardLoadingPanelCard");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnStartActionPerformed

    private void dashboardStatusComputerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardStatusComputerNameTextFieldActionPerformed
        
    }//GEN-LAST:event_dashboardStatusComputerNameTextFieldActionPerformed
    private void dashboardStatusPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dashboardStatusPanelComponentShown
        ClientWindows mainWindow = this;
        
        if (!this.tariffAndTimeAnimation.isPresent()) {
            dashboardStatusUsernameTextField.setText(
                applicationState.getCurrentUsername().get()
            );
            dashboardStatusComputerNameTextField.setText(
                    applicationState.getCurrentComputer().get().getName()
            );


            Timer tariffAndTimeAnimation = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Optional<Computer> optionalComputer = mainWindow.applicationState.getCurrentComputer();
                        dashboardStatusTimeTextField
                                .setText(getComputerActiveTime());
                        dashboardStatusTariffTextField
                                .setText(getComputerCurrentTarif());
                    }
            });
            tariffAndTimeAnimation.start();
            this.tariffAndTimeAnimation = Optional.of(tariffAndTimeAnimation);
        }
        
    }//GEN-LAST:event_dashboardStatusPanelComponentShown

    private void dashboardStatusStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardStatusStopButtonActionPerformed
        dashboardPanelCardLayout.show(dashboardPanel, "dashboardLogoutConfirmationPanelCard");
    }//GEN-LAST:event_dashboardStatusStopButtonActionPerformed

    private void dashboardLoadingPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dashboardLoadingPanelComponentShown
        ClientWindows mainWindow = this;
        Timer prepareDashboardStatusInitializationTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.dashboardPanelCardLayout
                        .show(mainWindow.dashboardPanel, "dashboardStatusPanelCard");
            }
        });
        prepareDashboardStatusInitializationTimer.setRepeats(false);
        prepareDashboardStatusInitializationTimer.start();
    }//GEN-LAST:event_dashboardLoadingPanelComponentShown

    private void logoutConfirmationBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutConfirmationBackButtonActionPerformed
        dashboardPanelCardLayout.show(dashboardPanel, "dashboardStatusPanelCard");
    }//GEN-LAST:event_logoutConfirmationBackButtonActionPerformed

    private void logoutConfirmationYesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutConfirmationYesButtonActionPerformed
        dashboardTariffTotalTextField.setText(getComputerCurrentTarif());
        
        applicationState.logoutAction();
        
        this.tariffAndTimeAnimation.get().stop();
        this.tariffAndTimeAnimation = Optional.empty();
        
        dashboardPanelCardLayout
                .show(dashboardPanel, "dashboardTarifInformationPanelCard");

        JSONObject eventPayload = new JSONObject();
        eventPayload.put("computer_name", applicationState.getCurrentComputer().get().getName());
        socket.emit(SocketEventNameEnum.CLIENT_LOGGED_OUT.name, eventPayload);

    }//GEN-LAST:event_logoutConfirmationYesButtonActionPerformed

    private void dashboardTarifToLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardTarifToLoginButtonActionPerformed
        mainPanelCardLayout.show(mainPanel, "loginPanelCard");
    }//GEN-LAST:event_dashboardTarifToLoginButtonActionPerformed
    
    private String getComputerCurrentTarif() {
        Double totalTariff = applicationState.getCurrentTariff();
        return CurrencyAbbrevationEnum.RUPIAH.currencyAbbr + 
                " " + 
                new DecimalFormat("#0.00")
                        .format(totalTariff);
    }
    
    private String getComputerActiveTime() {
        Long differencesInMinutes = applicationState
            .getComputerCurrentTimeAndLastStartTimeDifferencesInMinutes();
        Long differencesInSeconds = applicationState
            .getComputerCurrentTimeAndLastStartTimeDifferencesInSeconds();
        
        Long minutesRemainder = differencesInMinutes % 60;
        Long hours = (differencesInMinutes - minutesRemainder) / 60;
        
        Long secondsRemainder = differencesInSeconds % 60;
        
        return hours.toString() + " : " + minutesRemainder.toString() + " : " + secondsRemainder.toString();
    }
    
    private void changeWarnetNameLabel() {
        Setting warnetNameSetting = applicationState.getWarnetNameSetting().get();
        loginWarnetNameLabel
            .setText(
                warnetNameSetting.getStringValue()
            );
        dashboardStatusWarnetNameLabel.setText(
                warnetNameSetting.getStringValue()
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnStart;
    private javax.swing.JPanel dashboardLoadingPanel;
    private javax.swing.JPanel dashboardLogoutConfirmationPanel;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JTextField dashboardStatusComputerNameTextField;
    private javax.swing.JPanel dashboardStatusPanel;
    private javax.swing.JButton dashboardStatusStopButton;
    private javax.swing.JTextField dashboardStatusTariffTextField;
    private javax.swing.JTextField dashboardStatusTimeTextField;
    private javax.swing.JTextField dashboardStatusUsernameTextField;
    private javax.swing.JLabel dashboardStatusWarnetNameLabel;
    private javax.swing.JPanel dashboardTarifInformationPanel;
    private javax.swing.JButton dashboardTarifToLoginButton;
    private javax.swing.JLabel dashboardTariffTotalTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField loginUsernameTextField;
    private javax.swing.JLabel loginWarnetNameLabel;
    private javax.swing.JButton logoutConfirmationBackButton;
    private javax.swing.JButton logoutConfirmationYesButton;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
