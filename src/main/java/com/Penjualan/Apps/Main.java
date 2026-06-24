/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.Penjualan.Apps;

import com.Penjualan.Apps.Auth.Login;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author Yuriko
 */
public class Main {

    public static void main(String[] args) {

        try {
            FlatLightLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
