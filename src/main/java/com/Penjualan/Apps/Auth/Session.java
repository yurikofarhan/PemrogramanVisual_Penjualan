/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Penjualan.Apps.Auth;

/**
 *
 * @author Yuriko
 */

public class Session {

    private static String idKasir;
    private static String namaKasir;
    private static String username;

    public static String getIdKasir() {
        return idKasir;
    }

    public static void setIdKasir(String idKasir) {
        Session.idKasir = idKasir;
    }

    public static String getNamaKasir() {
        return namaKasir;
    }

    public static void setNamaKasir(String namaKasir) {
        Session.namaKasir = namaKasir;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static void clear() {
        idKasir = null;
        namaKasir = null;
        username = null;
    }
}
