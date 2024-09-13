/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum12;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO
 */
public class krs extends javax.swing.JFrame {

    public Connection conn;
    public Statement cn;

    /**
     * Creates new form krs
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
    
    public void date(){
        Date tanggal = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-d");
        System.out.println(tanggal);
    }
    
      public void tampildata() {
        /**DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("Kode Matakuliah");
        tabelnyo.addColumn("Matakuliah");
        tabelnyo.addColumn("SKS");
        try {
            koneksi();
            String sql = "select * from matakuliah2222001";
            ResultSet rs
                    = cn.executeQuery(sql);
            while (rs.next()) {
                tabelnyo.addRow(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3),});
            }
            jTable1.setModel(tabelnyo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan table1");
        }**/

        tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("KRS ID");
        tableModel2.addColumn("Tanggal Isi");
        tableModel2.addColumn("Semester");
        tableModel2.addColumn("NIM");
        tableModel2.addColumn("Kode Matkul");
        tableModel2.addColumn("Nilai");
        try {
            koneksi();
            String sql = "select * from krs2222001";
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                tableModel2.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                }
                );
            }
            jTable2.setModel(tableModel2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ada Kesalahan table2 "); 
        } 
      }
     
     
    
    void bersih() {
        semmhs.setText("");
        nimmhs.setText("");
        namamhs.setText("");
        kodematkul.setText("");
        namamatkul.setText("");
        sksmatkul.setText("");
    }

    void cekkrs() {
        DefaultTableModel tabelnyo = new DefaultTableModel();
        tabelnyo.addColumn("Kode");
        tabelnyo.addColumn("Nama Matakuliah");
        tabelnyo.addColumn("SKS");

        tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("KRS ID");
        tableModel2.addColumn("Tanggal Isi");
        tableModel2.addColumn("Semester");
        tableModel2.addColumn("NIM");
        tableModel2.addColumn("Kode Matkul");
        tableModel2.addColumn("Nilai");
        try {
            koneksi();
            String sql = "SELECT krs2222001.krsid,krs2222001.krssem,krs2222001.krskodemtk,krs2222001.krsnobp,krs2222001.krsnilai, "
                    + " matakuliah2222001.namamtk, matakuliah2222001.sks FROM krs2222001"
                    + " JOIN mahasiswa2222001 on krsnobp=nimmahasiswa "
                    + " JOIN matakuliah2222001 on krskodemtk=kodemtk "
                    + " WHERE krssem = '" + semmhs.getText() + "' or krsnobp ='"
                    + nimmhs.getText() + "'";                            
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                tabelnyo.addRow(new Object[]{
                    rs.getString(3),
                    rs.getString(6),
                    rs.getString(7)
                });

            }
            jTable1.setModel(tabelnyo);
            jTable2.setModel(tableModel2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan Menampilkan Data" + e);
        }
        
        try {
            koneksi();
            String sql = "Select * from mahasiswa2222001 where "
                    + "nimmahasiswa = '" + nimmhs.getText() + "'";

            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                namamhs.setText(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kesalahan Menampilkan Data" + e);
        }
    }

    public krs() {
        initComponents();
        bersih();

        jTable1.setModel(tblmodel);

        tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("KRS ID");
        tableModel2.addColumn("Tanggal Isi");
        tableModel2.addColumn("Semester");
        tableModel2.addColumn("NIM");
        tableModel2.addColumn("Kode Matkul");
        tableModel2.addColumn("Nilai");

        jTable2.setModel(tableModel2);
        tampildata();
    }

    String data[] = new String[3];

    private javax.swing.table.DefaultTableModel tblmodel = getDefaultTableModel();

    private DefaultTableModel tableModel2;

    int row = 0;

    private javax.swing.table.DefaultTableModel getDefaultTableModel() {
        return new javax.swing.table.DefaultTableModel(new Object[][]{},
                new String[]{"Kode", "Matakuliah", "SKS"}
        );

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
        carimhs = new javax.swing.JButton();
        semmhs = new javax.swing.JTextField();
        nimmhs = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        namamhs = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        kodematkul = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        namamatkul = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        sksmatkul = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        kurang = new javax.swing.JButton();
        carimatkul = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Simpan = new javax.swing.JButton();
        cetakkrs = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SEMESTER");

        jLabel2.setText("NIM MAHASISWA");

        carimhs.setText("CARI");
        carimhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carimhsActionPerformed(evt);
            }
        });

        nimmhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nimmhsActionPerformed(evt);
            }
        });
        nimmhs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nimmhsKeyPressed(evt);
            }
        });

        jLabel4.setText("NAMA");

        jLabel5.setText("KODE MTK");

        kodematkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodematkulActionPerformed(evt);
            }
        });

        jLabel6.setText("MATA KULIAH");

        jLabel7.setText("SKS");

        tambah.setText("+");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        kurang.setText("-");
        kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kurangActionPerformed(evt);
            }
        });

        carimatkul.setText("CARI");
        carimatkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carimatkulActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        Simpan.setText("Simpan");
        Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanActionPerformed(evt);
            }
        });

        cetakkrs.setText("Cetak");
        cetakkrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakkrsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(carimhs, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(nimmhs)
                            .addComponent(semmhs))
                        .addGap(68, 68, 68)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(namamhs, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(carimatkul, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kodematkul, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(namamatkul, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sksmatkul, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(tambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kurang))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cetakkrs)))
                    .addComponent(Simpan))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(semmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nimmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(namamhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(carimhs)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodematkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namamatkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sksmatkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambah)
                    .addComponent(kurang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carimatkul)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cetakkrs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(301, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nimmhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nimmhsActionPerformed
        // TODO add your handling code here:
        cekkrs();
    }//GEN-LAST:event_nimmhsActionPerformed

    private void nimmhsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nimmhsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            try {

                koneksi();
                String sql = "Select * from mahasiswa2222001 where nimmahasiswa='" + nimmhs.getText() + "'";
                ResultSet rs = cn.executeQuery(sql);
                if (rs.next()) {
                    namamhs.setText(rs.getString(2));
                    kodematkul.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Data Mahasiswa Tidak Ada");
                    nimmhs.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ada Kesalahan Menampilkan Data Mahasiswa"
                        + e);
            }

        }
    }//GEN-LAST:event_nimmhsKeyPressed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        String k = kodematkul.getText();
        if (k.equals("")) {
            JOptionPane.showMessageDialog(null, "Kode Matakuliah Belum Diisi");
        } else {
            data[0] = kodematkul.getText();
            data[1] = namamatkul.getText();
            data[2] = sksmatkul.getText();
            tblmodel.insertRow(row, data);
            kodematkul.setText("");
            namamatkul.setText("");
            sksmatkul.setText("");
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void kurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kurangActionPerformed
        // TODO add your handling code here:
        tblmodel.removeRow(row);
        kodematkul.setText("");
        namamatkul.setText("");
        sksmatkul.setText("");
    }//GEN-LAST:event_kurangActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        row = jTable1.getSelectedRow();
        kodematkul.setText(tblmodel.getValueAt(row, 0).toString());
        namamatkul.setText(tblmodel.getValueAt(row, 1).toString());
        sksmatkul.setText(tblmodel.getValueAt(row, 2).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void carimatkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carimatkulActionPerformed
        // TODO add your handling code here:
        new carimatkul().show();
    }//GEN-LAST:event_carimatkulActionPerformed

    private void carimhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carimhsActionPerformed
        // TODO add your handling code here:
        new carimhs().show();
    }//GEN-LAST:event_carimhsActionPerformed

    private void kodematkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodematkulActionPerformed
        // TODO add your handling code here:
        try {
            koneksi();
            String sql = "Select * from matakuliah2222001 where "
                    + "kodemtk = '" + kodematkul.getText() + "'";

            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                namamatkul.setText(rs.getString(2));
                sksmatkul.setText(rs.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + e);
        }
    }//GEN-LAST:event_kodematkulActionPerformed

    private void SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanActionPerformed
        // TODO add your handling code here:
        try {
            koneksi();
            int t = jTable1.getRowCount();
            for (int i = 0; i < t; i++) {
                String kdmtk = jTable1.getValueAt(i, 0).toString();

                String sql = "insert INTO krs2222001 (krstglisi,krssem,krsnobp,krskodemtk) "
                        + " VALUES(CURDATE(),'" + semmhs.getText() + "', "
                        + " '" + nimmhs.getText() + "', '" + kdmtk + "') ";
                cn.executeUpdate(sql);

            }
            conn.close();
            for (int i = 0; i < t; i++) {
                tblmodel.removeRow(row);
            }
            JOptionPane.showMessageDialog(null, "Data Sukses Disimpan");

            bersih();
            tampildata();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses Gagal" + e);
        }
    }//GEN-LAST:event_SimpanActionPerformed

    private void cetakkrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakkrsActionPerformed
        // TODO add your handling code here:
        try {
            HashMap parameter = new HashMap();
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql:" + "///dbakademik2222001", "root", "");
            String sql = "SELECT * FROM krs2222001";
            File file = new File("src/LaporanPraktikum12/laporankrs.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, cn);
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Data Tidak Dapat di Cetak !!!" + "\n" + e.getMessage(), "Cetak Data", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cetakkrsActionPerformed

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
            java.util.logging.Logger.getLogger(krs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(krs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(krs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(krs.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new krs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Simpan;
    private javax.swing.JButton carimatkul;
    private javax.swing.JButton carimhs;
    private javax.swing.JButton cetakkrs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTextField kodematkul;
    private javax.swing.JButton kurang;
    public static javax.swing.JTextField namamatkul;
    public static javax.swing.JTextField namamhs;
    public static javax.swing.JTextField nimmhs;
    public static javax.swing.JTextField semmhs;
    public static javax.swing.JTextField sksmatkul;
    private javax.swing.JButton tambah;
    // End of variables declaration//GEN-END:variables
}
