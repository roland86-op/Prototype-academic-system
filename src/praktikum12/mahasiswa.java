/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum12;

import java.sql.ResultSet;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author LENOVO
 */
public class mahasiswa extends javax.swing.JFrame {

    public Connection conn;
    public Statement cn;

    /**
     * Creates new form mahasiswa
     */
    public void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbakademik2222001", "root", "");
            cn = conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal..");
            System.out.println(e.getMessage());
        }
    }

    public void tampildata() {
        DefaultTableModel tabela = new DefaultTableModel();
        tabela.addColumn("NIM Mahasiswa");
        tabela.addColumn("Nama Mahasiswa");
        tabela.addColumn("Tempat Lahir");
        tabela.addColumn("Tanggal Lahir");
        tabela.addColumn("No HP");
        tabela.addColumn("Alamat");
        tabela.addColumn("Jenis Kelamin");
        tabela.addColumn("Nama Orang Tua");
        tabela.addColumn("Fakultas");
        tabela.addColumn("Prodi");
        try {
            koneksi();
            String sql = "select * from mahasiswa2222001";
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                tabela.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10)
                }
                );
            }
            tabel.setModel(tabela);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan");
        }
    }

    public void bersih() {
        cari.setText("");
        nimmhs.setText("");
        namamhs.setText("");
        tmplahirmhs.setText("");
        //tgllahirmhs.setDateFormatString("");
        hpmhs.setText("");
        alamatmhs.setText("");
        jkmhs.setSelectedItem("");
        namaortumhs.setText("");
        fakultas.setSelectedItem("");
        prodi.setSelectedItem("");
    }

    public void validasiMHS() {
        try {
            koneksi();
            String sql = "Select * from mahasiswa2222001 where nimmahasiswa='" + nimmhs.getText() + "'";
            ResultSet rs = cn.executeQuery(sql);
            if (rs.next()) {
                //cari.setText("");
                nimmhs.setText(rs.getString(1));
                namamhs.setText(rs.getString(2));
                tmplahirmhs.setText(rs.getString(3));
                tgllahirmhs.setDate(rs.getDate(4));
                hpmhs.setText(rs.getString(5));
                alamatmhs.setText(rs.getString(6));
                jkmhs.setSelectedItem(rs.getString(7));
                namaortumhs.setText(rs.getString(8));
                fakultas.setSelectedItem(rs.getString(9));
                prodi.setSelectedItem(rs.getString(10));

                edit.setEnabled(true);
                simpan.setEnabled(false);
                try {
                    int kel = JOptionPane.showConfirmDialog(this,
                            "Nim Mahasiswa :" + nimmhs.getText()
                            + "Sudah ada, Mau Di Edit atau di hapus...?",
                            "INFORMASI", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (kel == JOptionPane.NO_OPTION) {
                        bersih();
                        edit.setEnabled(true);
                        simpan.setEnabled(true);
                        nimmhs.requestFocus();
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
        }

    }

    public void Cari() {
        String a = new String(cari.getText());
        if (a.equals("")) {
            JOptionPane.showMessageDialog(null, "Field Masih kosong..", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
            cari.requestFocus();
        } else {
            DefaultTableModel tabela = new DefaultTableModel();
            tabela.addColumn("NIM Mahasiswa");
            tabela.addColumn("Nama Mahasiswa");
            tabela.addColumn("Tempat Lahir");
            tabela.addColumn("Tanggal Lahir");
            tabela.addColumn("No HP");
            tabela.addColumn("Alamat");
            tabela.addColumn("Jenis Kelamin");
            tabela.addColumn("Nama Orang Tua");
            tabela.addColumn("Fakultas");
            tabela.addColumn("Prodi");
            try {
                koneksi();
                String sql = "Select * from mahasiswa2222001 where "
                        + "nimmahasiswa like '%" + cari.getText() + "%' or "
                        + "namamhs like '%" + cari.getText() + "%' ";

                ResultSet rs = cn.executeQuery(sql);
                while (rs.next()) {
                    tabela.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                    }
                    );
                }

                tabel.setModel(tabela);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan..", "INFORMASI", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public mahasiswa() {
        initComponents();
        tampildata();
        
        jkmhs.removeAllItems();
        jkmhs.addItem("Laki-laki");
        jkmhs.addItem("Perempuan");

        fakultas.removeAllItems();
        fakultas.addItem("Teknologi Industri");
        fakultas.addItem("Teknologi Informasi");

        prodi.removeAllItems();
        prodi.addItem("Desain Komunikasi Visual");
        prodi.addItem("Matematika");
        prodi.addItem("Sistem Informasi");
        prodi.addItem("Teknik Komputer");
        prodi.addItem("Teknik Industri");
        prodi.addItem("Manajemen Rekayasa");
        prodi.addItem("Perdagangan Internasional");
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
        cari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nimmhs = new javax.swing.JTextField();
        namamhs = new javax.swing.JTextField();
        tmplahirmhs = new javax.swing.JTextField();
        tgllahirmhs = new com.toedter.calendar.JDateChooser();
        hpmhs = new javax.swing.JTextField();
        alamatmhs = new javax.swing.JTextField();
        jkmhs = new javax.swing.JComboBox<>();
        fakultas = new javax.swing.JComboBox<>();
        namaortumhs = new javax.swing.JTextField();
        prodi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        simpan = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        keluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setText("INPUT DATA MAHASISWA");

        jLabel2.setText("CARI BERDASARKAN NIM/NAMA MAHASISWA");

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jLabel3.setText("NIM MAHASISWA");

        jLabel4.setText("NAMA MAHASISWA");

        jLabel5.setText("TEMPAT LAHIR MAHASISWA");

        jLabel6.setText("TANGGAL LAHIR MAHASISWA");

        jLabel7.setText("NO HP");

        jLabel8.setText("ALAMAT MAHASISWA");

        jLabel9.setText("JENIS KELAMIN");

        jLabel10.setText("NAMA ORTU");

        jLabel11.setText("FAKULTAS");

        jLabel12.setText("PRODI");

        nimmhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nimmhsActionPerformed(evt);
            }
        });

        tgllahirmhs.setDateFormatString("yyyy-MM-d");

        jkmhs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fakultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        prodi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(36, 36, 36))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(keluar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(prodi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fakultas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jkmhs, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tgllahirmhs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                .addComponent(nimmhs, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(namamhs, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tmplahirmhs, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hpmhs, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(alamatmhs, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(namaortumhs, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jLabel1))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nimmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(namamhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tmplahirmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tgllahirmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(hpmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(alamatmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jkmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(namaortumhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(fakultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(prodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan)
                            .addComponent(edit)
                            .addComponent(hapus)
                            .addComponent(keluar)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nimmhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nimmhsActionPerformed
        // TODO add your handling code here:
        validasiMHS();
    }//GEN-LAST:event_nimmhsActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_cariActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        try {
            koneksi();
            String sql = "insert into mahasiswa2222001 values('" + nimmhs.getText() + "','"
                    + namamhs.getText() + "','"
                    + tmplahirmhs.getText() + "','"
                    + ((JTextField) tgllahirmhs.getDateEditor().getUiComponent()).getText() + "','"
                    + hpmhs.getText() + "','"
                    + alamatmhs.getText() + "','"
                    + jkmhs.getSelectedItem() + "','"
                    + namaortumhs.getText() + "','"
                    + fakultas.getSelectedItem() + "','"
                    + prodi.getSelectedItem() + "')";
            cn.executeUpdate(sql);
            conn.close();
            tampildata();
            bersih();
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses penyimpanan gagal" + e);
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        try {

            koneksi();
            String sql = "update mahasiswa2222001 set namamhs='"
                    + namamhs.getText() + "',tempatlahir='"
                    + tmplahirmhs.getText() + "',tanggallahir='"
                    + ((JTextField) tgllahirmhs.getDateEditor().getUiComponent()).getText() + "',nohp='"
                    + hpmhs.getText() + "',alamat='"
                    + alamatmhs.getText() + "',jeniskelamin='"
                    + jkmhs.getSelectedItem() + "',namaortu='"
                    + namaortumhs.getText() + "',fakultas='"
                    + fakultas.getSelectedItem() + "',prodi='"
                    + prodi.getSelectedItem() + "' where nimmahasiswa='"
                    + nimmhs.getText() + "'";
            cn.executeUpdate(sql);
            cn.close();
            tampildata();
            bersih();
            nimmhs.setEnabled(true);
            simpan.setEnabled(true);
            nimmhs.requestFocus();
            edit.setEnabled(false);
            hapus.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses edit gagal" + e);
        }
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        try {
            getToolkit().beep();
            int keluar = JOptionPane.showConfirmDialog(this, "Anda Yakin Ingin Meghapus Ini..?", "PERINGATAN", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (keluar == JOptionPane.YES_OPTION) {
                try {
                    koneksi();
                    String sql = "delete from mahasiswa2222001 where nimmahasiswa='" + nimmhs.getText() + "'";
                    cn.executeUpdate(sql);
                    conn.close();
                    
                    nimmhs.setEnabled(true);
                    simpan.setEnabled(true);
                    edit.setEnabled(false);
                    
                    nimmhs.requestFocus();
                    tampildata();
                    bersih();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Deleting failed..");
                }
            }
        } catch (Exception e) {}
    }//GEN-LAST:event_hapusActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int row = 0;
        if (evt.getClickCount() == 1) {
            row = tabel.getSelectedRow();

            String a = tabel.getValueAt(row, 0).toString();
            String b = tabel.getValueAt(row, 1).toString();
            String c = tabel.getValueAt(row, 2).toString();
            String d = tabel.getValueAt(row, 3).toString();
            String e = tabel.getValueAt(row, 4).toString();
            String f = tabel.getValueAt(row, 5).toString();
            String g = tabel.getValueAt(row, 6).toString();
            String h = tabel.getValueAt(row, 7).toString();
            String i = tabel.getValueAt(row, 8).toString();
            String j = tabel.getValueAt(row, 9).toString();

            nimmhs.setText(a);
            namamhs.setText(b);
            tmplahirmhs.setText(c);
            ((JTextField) tgllahirmhs.getDateEditor().getUiComponent()).setText(d);
            hpmhs.setText(e);
            alamatmhs.setText(f);
            jkmhs.setSelectedItem(g);
            namaortumhs.setText(h);
            fakultas.setSelectedItem(i);
            prodi.setSelectedItem(j);

            nimmhs.setEnabled(false);
            simpan.setEnabled(false);
            edit.setEnabled(true);
            hapus.setEnabled(true);
        } else {JOptionPane.showMessageDialog(null, "Failed..");}
    }//GEN-LAST:event_tabelMouseClicked

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        this.toBack();
    }//GEN-LAST:event_keluarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatmhs;
    private javax.swing.JTextField cari;
    private javax.swing.JButton edit;
    private javax.swing.JComboBox<String> fakultas;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField hpmhs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jkmhs;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField namamhs;
    private javax.swing.JTextField namaortumhs;
    private javax.swing.JTextField nimmhs;
    private javax.swing.JComboBox<String> prodi;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabel;
    private com.toedter.calendar.JDateChooser tgllahirmhs;
    private javax.swing.JTextField tmplahirmhs;
    // End of variables declaration//GEN-END:variables
}
