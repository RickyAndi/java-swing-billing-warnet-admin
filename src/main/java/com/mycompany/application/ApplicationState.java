/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

import com.mycompany.gui.MainWindow;

/**
 *
 * @author rickyandhi
 */
public class Application {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.dispose();
                mainWindow.setUndecorated(true);
                mainWindow.setVisible(true);
            }
        });
    }
}
