/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Penjualan.Apps.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Yuriko
 */

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/db_latihan";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

//                JOptionPane.showMessageDialog(null,
//                        "Koneksi database berhasil",
//                        "Informasi",
//                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Koneksi database berhasil");
            }

        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null,
                    "Driver MySQL tidak ditemukan!",
                    "Error Database",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,
                    "Koneksi database gagal!\n" + e.getMessage(),
                    "Error Database",
                    JOptionPane.ERROR_MESSAGE);

        }

        return connection;

    }
}

