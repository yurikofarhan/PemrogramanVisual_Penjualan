/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.Penjualan.Apps.Nota;

import com.Penjualan.Apps.Auth.Session;
import com.Penjualan.Apps.Config.Koneksi;
import com.Penjualan.Apps.MainFrame;
import java.io.InputStream;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Yuriko
 */
public class FormNota extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormNota.class.getName());

    /**
     * Creates new form FormNota
     */
    public String id, nama, jenis, telp, almt;
    public String kdbrg, nmbrg, jenisbrg, hb, hj;
    private Connection conn = new Koneksi().getConnection();
    private DefaultTableModel tabmode;

    public FormNota() {
        initComponents();
        setLocationRelativeTo(null);
        
        kosong();
        aktif();
        autonumber();

        setData();
    }

    protected void setData() {
        String idKasir = Session.getIdKasir();
        String namaKasir = Session.getNamaKasir();

        lblIdKasir.setText(idKasir);
        lblNamaKasir.setText(namaKasir);
    }

    protected void nama() {
        try {
            String sql = "SELECT * FROM kasir1 WHERE id_kasir='"
                    + lblIdKasir.getText() + "'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            if (hasil.next()) {
                jLabel17.setText(hasil.getString("nama_kasir"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil" + e);
        }
    }

    protected void aktif() {
        txtQyt.requestFocus();
        jTgl.setEditor(new JSpinner.DateEditor(jTgl, "yyyy/MM/dd"));
        Object[] Baris = {"KD Barang", "Nama", "Harga Beli", "Harga Jual", "QTY", "Total"};
        tabmode = new DefaultTableModel(null, Baris);
        tblTransaksi.setModel(tabmode);
    }

    protected void kosong() {
        txtId.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtKodeBarang.setText("");
//        txtNoBarang.setText("");
        txtHbeli.setText("");
        txtHJual.setText("");
        txtQyt.setText("");
        txtTotal.setText("");
    }

    protected void autonumber() {
        try {
            String sql = "SELECT idnota FROM nota order by idnota asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtIdNota.setText("IN0001");
            while (rs.next()) {
                String id_nota = rs.getString("idnota").substring(2);
                int AN = Integer.parseInt(id_nota) + 1;
                String Nol = "";
                if (AN < 10) {
                    Nol = "000";
                } else if (AN < 100) {
                    Nol = "00";
                } else if (AN < 1000) {
                    Nol = "0";
                } else if (AN < 10000) {
                    Nol = "";
                }

                txtIdNota.setText("IN" + Nol + AN);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Auto Number Gagal" + e);
        }
    }

    public void itemTerpilih() {
        PopupPelanggan Pp = new PopupPelanggan();
        Pp.plgn = this;
        txtId.setText(id);
        txtNama.setText(nama);
        txtAlamat.setText(almt);
    }

    public void itemTerpilihBrg() {
        PopupBarang Pbrg = new PopupBarang();
        Pbrg.brg = this;
        txtKodeBarang.setText(kdbrg);
        txtNama1.setText(nmbrg);
        txtHbeli.setText(hb);
        txtHJual.setText(hj);
        txtQyt.requestFocus();
    }

    public void hitung() {
        int total = 0;
        for (int i = 0; i < tblTransaksi.getRowCount(); i++) {
            int amount = Integer.parseInt(
                    tblTransaksi.getValueAt(i, 5).toString()
            );
            total += amount;
        }
        txtTotalHarga.setText(Integer.toString(total));
    }

    public void cetak() {
        try {
            String fileName = "nota.jasper";

            InputStream is = getClass().getClassLoader().getResourceAsStream("IReportFile/" + fileName);

            if (is == null) {
                JOptionPane.showMessageDialog(rootPane,
                        "File " + fileName + " tetap tidak ditemukan dengan ClassLoader!\n"
                        + "Pastikan folder di src/main/resources bernama 'IReportFile' (bukan 'report')");
                return;
            }

            HashMap parameter = new HashMap();
            parameter.put("id_nota", txtIdNota.getText());

            JasperPrint print = JasperFillManager.fillReport(is, parameter, conn);
            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Gagal Mencetak: " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblIdKasir = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdNota = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        btnCariPlg = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        btnCariBrng = new javax.swing.JButton();
        txtNama1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtHbeli = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtHJual = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtQyt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnCari2 = new javax.swing.JButton();
        jTgl = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        lblNamaKasir = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        btnCari3 = new javax.swing.JButton();
        btnCari4 = new javax.swing.JButton();
        btnCari5 = new javax.swing.JButton();
        btnCari6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Nota");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("ID Kasir");

        lblIdKasir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblIdKasir.setText("jLabel1");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("ID Nota");

        txtIdNota.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("Nama Kasir");

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Data Pelanggan");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("ID Pelanggan");

        txtId.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("Nama ");

        txtNama.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        btnCariPlg.setText("Cari");
        btnCariPlg.addActionListener(this::btnCariPlgActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(txtId)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCariPlg, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPlg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("Data Barang");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setText("KD Barang");

        txtKodeBarang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        btnCariBrng.setText("Cari");
        btnCariBrng.addActionListener(this::btnCariBrngActionPerformed);

        txtNama1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel13.setText("Nama ");

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel14.setText("Harga Bei");

        txtHbeli.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel15.setText("Harga Jua");

        txtHJual.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel16.setText("QYT");

        txtQyt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtQyt.addActionListener(this::txtQytActionPerformed);

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel17.setText("Total");

        txtTotal.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        btnCari2.setText("Tambah");
        btnCari2.addActionListener(this::btnCari2ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel17))
                                        .addGap(9, 9, 9)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                    .addComponent(txtNama1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtHbeli, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtHJual)
                                    .addComponent(txtQyt)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCariBrng, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btnCari2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBrng))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtHbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtHJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtQyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCari2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTgl.setModel(new javax.swing.SpinnerDateModel());

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel10.setText("Tgl Nota");

        lblNamaKasir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNamaKasir.setText("test");

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblTransaksi);

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setText("Transaksi");

        btnCari3.setText("Hapus");
        btnCari3.addActionListener(this::btnCari3ActionPerformed);

        btnCari4.setText("Simpan");
        btnCari4.addActionListener(this::btnCari4ActionPerformed);

        btnCari5.setText("Batal");
        btnCari5.addActionListener(this::btnCari5ActionPerformed);

        btnCari6.setText("Kembali");
        btnCari6.addActionListener(this::btnCari6ActionPerformed);

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel19.setText("Total Harga");

        txtTotalHarga.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCari3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdKasir))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdNota, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaKasir)
                            .addComponent(jTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnCari4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCari5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCari6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblIdKasir)
                            .addComponent(jLabel9)
                            .addComponent(lblNamaKasir))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtIdNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCari3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCari4)
                        .addComponent(btnCari5)
                        .addComponent(btnCari6)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariPlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPlgActionPerformed
        PopupPelanggan Pp = new PopupPelanggan();
        Pp.plgn = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_btnCariPlgActionPerformed

    private void btnCariBrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBrngActionPerformed
        PopupBarang Pbrg = new PopupBarang();
        Pbrg.brg = this;
        Pbrg.setVisible(true);
        Pbrg.setResizable(false);
    }//GEN-LAST:event_btnCariBrngActionPerformed

    private void btnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari2ActionPerformed
        try {
            String kode = txtKodeBarang.getText();
            String nama = txtNama.getText();
            int hargab = Integer.parseInt(txtHbeli.getText());
            int hargaj = Integer.parseInt(txtHJual.getText());
            int qty = Integer.parseInt(txtQyt.getText());
            int total = Integer.parseInt(txtTotal.getText());
            tabmode.addRow(new Object[]{kode, nama, hargab, hargaj, qty, total});
            tblTransaksi.setModel(tabmode);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        txtKodeBarang.setText("");
        txtNama1.setText("");
        txtHbeli.setText("");
        txtHJual.setText("");
        txtQyt.setText("");
        txtTotal.setText("");
        hitung();

    }//GEN-LAST:event_btnCari2ActionPerformed

    private void btnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari3ActionPerformed
        int index = tblTransaksi.getSelectedRow();
        tabmode.removeRow(index);
        tblTransaksi.setModel(tabmode);
        hitung();
    }//GEN-LAST:event_btnCari3ActionPerformed

    private void btnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari4ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fd = sdf.format(jTgl.getValue());
        String sql = "insert into nota values (?,?,?,?)";
        String zsql = "insert into isi values (?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdNota.getText());
            stat.setString(2, fd);
            stat.setString(3, txtId.getText());
            stat.setString(4, lblIdKasir.getText());
            stat.executeUpdate();
            int t = tblTransaksi.getRowCount();
            for (int i = 0; i < t; i++) {
                String xkd = tblTransaksi.getValueAt(i, 0).toString();
                String xhb = tblTransaksi.getValueAt(i, 2).toString();
                String xhj = tblTransaksi.getValueAt(i, 3).toString();
                String xqty = tblTransaksi.getValueAt(i, 4).toString();
                PreparedStatement stat2 = conn.prepareStatement(zsql);
                stat2.setString(1, txtIdNota.getText());
                stat2.setString(2, xkd);
                stat2.setString(3, xhb);
                stat2.setString(4, xhj);
                stat2.setString(5, xqty);
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");

            cetak();
            kosong();
            aktif();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan" + e);
        }
    }//GEN-LAST:event_btnCari4ActionPerformed

    private void btnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari5ActionPerformed
        kosong();
        aktif();
        autonumber();
    }//GEN-LAST:event_btnCari5ActionPerformed

    private void btnCari6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari6ActionPerformed
        MainFrame x = new MainFrame();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCari6ActionPerformed

    private void txtQytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQytActionPerformed
        int xhrgj = Integer.parseInt(txtHJual.getText());
        int xqty = Integer.parseInt(txtQyt.getText());
        int xjml = xhrgj * xqty;
        txtTotal.setText(String.valueOf(xjml));
    }//GEN-LAST:event_txtQytActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormNota().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari2;
    private javax.swing.JButton btnCari3;
    private javax.swing.JButton btnCari4;
    private javax.swing.JButton btnCari5;
    private javax.swing.JButton btnCari6;
    private javax.swing.JButton btnCariBrng;
    private javax.swing.JButton btnCariPlg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jTgl;
    private javax.swing.JLabel lblIdKasir;
    private javax.swing.JLabel lblNamaKasir;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtHJual;
    private javax.swing.JTextField txtHbeli;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdNota;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNama1;
    private javax.swing.JTextField txtQyt;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables
}
