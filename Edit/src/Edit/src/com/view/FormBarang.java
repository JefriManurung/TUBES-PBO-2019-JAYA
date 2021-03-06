
package com.view;

import com.model.model_barang;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class FormBarang extends javax.swing.JFrame {
    public String tgl;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public DefaultTableModel tbl;
    String header[] = {"Kode", "Nama Barang", "Tanggal Temu", "Kontak Penemu", "Keterangan", "Gambar"};
    
    model_barang model = new model_barang();
    /**
     * Creates new form FormBarang
     */
    public FormBarang() throws SQLException {
        initComponents();
        
        //membuat layar menjadi fullscreen
        //membuat layar menjadi fullscreen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setBackground(new Color(0,0,0,0));
        
        tableBarang.getTableHeader().setFont(new Font ("Tahoma", Font.PLAIN, 12));
        tableBarang.getTableHeader().setOpaque(false);
        tableBarang.getTableHeader().setBackground(new Color(63,29,2));
        tableBarang.getTableHeader().setForeground(new Color(255,255,255));
        
        tanggal_nemu.setDateFormatString("dd-MM-yyyy");
        
        tbl = new DefaultTableModel(null, header);
        tableBarang.setModel(tbl);
        model.populateJTable(this);
        //tableBarang.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //setLebarKolom();
    }
    
    public void setColomnWidth(int kolom){
        DefaultTableColumnModel dt = (DefaultTableColumnModel) tableBarang.getColumnModel();
        TableColumn kolomtabel = dt.getColumn(kolom);
        
        int lebar = 0;
        int margin = 10;
        int a;
        
        TableCellRenderer render = kolomtabel.getHeaderRenderer();
        
        if(render == null){
            render = tableBarang.getTableHeader().getDefaultRenderer();
        }
        
        Component kompo = render.getTableCellRendererComponent(tableBarang, kolomtabel.getHeaderRenderer(), false, false, 0, 0);
        lebar = kompo.getPreferredSize().width;
        
        for(a = 0; a < tableBarang.getRowCount(); a++){
            render = tableBarang.getCellRenderer(a, kolom);
            kompo = render.getTableCellRendererComponent(tableBarang, tableBarang.getValueAt(a, kolom), false, false, a, kolom);
            int lebarKolom = kompo.getPreferredSize().width;
            lebar = Math.max(lebar, lebarKolom);
        }
        lebar = lebar + margin;
        kolomtabel.setPreferredWidth(lebar);
    }
    
    public void setLebarKolom(){
        int a;
        for(a = 0; a < tableBarang.getColumnCount(); a++){
            setColomnWidth(a);
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

        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtkode_barang = new javax.swing.JTextField();
        txtnama_barang = new javax.swing.JTextField();
        tanggal_nemu = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtspek = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtkontak = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKategori = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        tampilGambar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Kode Barang");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 74, -1));

        jLabel2.setText("Nama Barang");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 74, -1));

        jLabel3.setText("Tanggal Temu");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 74, -1));

        jLabel4.setText("Keterangan");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 74, -1));

        txtkode_barang.setEditable(false);
        txtkode_barang.setBackground(new java.awt.Color(255, 255, 255));
        txtkode_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkode_barangActionPerformed(evt);
            }
        });
        getContentPane().add(txtkode_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 322, -1));
        getContentPane().add(txtnama_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 322, -1));

        tanggal_nemu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggal_nemuPropertyChange(evt);
            }
        });
        getContentPane().add(tanggal_nemu, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 343, -1));

        txtspek.setColumns(20);
        txtspek.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtspek.setRows(5);
        jScrollPane1.setViewportView(txtspek);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 322, 100));

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 150, 85, 40));

        jButton2.setText("Ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 220, 85, 40));

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 290, 85, 40));

        jButton4.setText("Batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 360, 85, 39));

        tableBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tableBarang.setGridColor(new java.awt.Color(255, 255, 255));
        tableBarang.setSelectionBackground(new java.awt.Color(251, 139, 35));
        tableBarang.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tableBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableBarang);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 428, 1110, 330));

        jLabel5.setText("Kontak Penemu");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));
        getContentPane().add(txtkontak, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 322, -1));

        jLabel6.setText("Kategori");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 74, -1));

        txtKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih Kategori --", "Pakaian", "Peralatan Belajar", "Elektronik", "Aksesoris", "Kendaraan", "Kecantikan", "Dokumen" }));
        getContentPane().add(txtKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 322, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tampilGambar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tampilGambar, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, 251));

        jLabel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel8MouseMoved(evt);
            }
        });
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 275, 223, 57));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_MenuUser/close_1.png"))); // NOI18N
        jLabel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel11MouseMoved(evt);
            }
        });
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1298, 0, 56, 23));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_MenuUser/kembali_1.png"))); // NOI18N
        jLabel12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel12MouseMoved(evt);
            }
        });
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1257, 0, 40, 23));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PAPAN IKLAN");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 240, 370));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_manajemen/Background Manajemen Barang Belum Ditemukan.png"))); // NOI18N
        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel7MouseMoved(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1367, 769));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtkode_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkode_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkode_barangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            model.Ubah(this);
        }catch(Exception ex){
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tanggal_nemuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggal_nemuPropertyChange
        if(tanggal_nemu.getDate() != null){
            tgl = format.format(tanggal_nemu.getDate());
        }
    }//GEN-LAST:event_tanggal_nemuPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            model.Simpan(this);
        }catch(Exception ex){
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            model.Hapus(this);
        }catch(Exception ex){
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            model.Bersih(this);
        }catch(Exception ex){
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBarangMouseClicked
        try{
            model.KlikTabel(this);
            //int i = tableBarang.getSelectedRow();
            
            /*int pilih = tableBarang.getSelectedRow();*/
            /*String s = (String) tableBarang.getModel().getValueAt(pilih, 2);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = f.parse(s);*/
            /*
            if(pilih == -1){
                return;
            }
            *//*txtkode_barang.setText(tableBarang.getValueAt(pilih, 0).toString());
            txtnama_barang.setText(tableBarang.getValueAt(pilih, 1).toString());
            tanggal_nemu.setDate((Date) tableBarang.getValueAt(pilih, 2));
            txtkontak.setText(tableBarang.getValueAt(pilih, 3).toString());
            txtspek.setText(tableBarang.getValueAt(pilih, 4).toString());
            if(tableBarang.getValueAt(pilih, 5) != null){
                ImageIcon image1 = (ImageIcon) tableBarang.getValueAt(pilih, 5);
                Image image2 = image1.getImage().getScaledInstance(tampilGambar.getWidth(), tampilGambar.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image3 = new ImageIcon(image2);
                tampilGambar.setIcon(image3);
            }else{
                System.out.println("No Image");
            }*/
        }catch(Exception ex){
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableBarangMouseClicked

    private void jLabel8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseMoved
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_manajemen/button barang sudah ditemukan klik.png")));
    }//GEN-LAST:event_jLabel8MouseMoved

    private void jLabel7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseMoved
        jLabel8.setIcon(null);
        jLabel11.setIcon(null);
        jLabel12.setIcon(null);
    }//GEN-LAST:event_jLabel7MouseMoved

    private void jLabel11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseMoved
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_MenuUser/close klik.png")));
        jLabel12.setIcon(null);
    }//GEN-LAST:event_jLabel11MouseMoved

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseMoved
        jLabel11.setIcon(null);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_MenuUser/kembali klik.png")));
    }//GEN-LAST:event_jLabel12MouseMoved

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        try{
            FormLogin lg = new FormLogin();
            lg.setVisible(true);
            this.setVisible(false);
        }catch(Exception ex){
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        try {
            FormBarangKetemu bgk = new FormBarangKetemu();
            bgk.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jLabel8MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormBarang().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormBarang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    public javax.swing.JTable tableBarang;
    public javax.swing.JLabel tampilGambar;
    public com.toedter.calendar.JDateChooser tanggal_nemu;
    public javax.swing.JComboBox txtKategori;
    public javax.swing.JTextField txtkode_barang;
    public javax.swing.JTextField txtkontak;
    public javax.swing.JTextField txtnama_barang;
    public javax.swing.JTextArea txtspek;
    // End of variables declaration//GEN-END:variables
}
