
package com.model;

import com.controller.controller_barangketemu;
import com.koneksi.koneksi;
import com.koneksi.koneksi_usercari;
import com.view.FormBarangKetemu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class model_barangketemu implements controller_barangketemu{
    byte[] gambar;
    
    @Override
    public void Kembalikan(FormBarangKetemu bgk) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "insert barang(kd_barang, kategori, nm_barang, tgl_temu, kontak_penemu, keterangan, gambar) values(?,?,?,?,?,?,?)";
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, bgk.txtkode_barang.getText());
            prep.setString(2, bgk.txtKategori.getSelectedItem().toString());
            prep.setString(3, bgk.txtnama_barang.getText());
            prep.setString(4, bgk.tgl);
            prep.setString(5, bgk.txtkontak.getText());
            prep.setString(6, bgk.txtspek.getText());
            
            int pilih = bgk.tableBarang.getSelectedRow();
            
            if(bgk.tampilGambar.getIcon() != null){
                try {
                    Icon icons = bgk.tampilGambar.getIcon();
                    BufferedImage bi = new BufferedImage(icons.getIconWidth(), icons.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bi.createGraphics();
                    icons.paintIcon(null, g, 0, 0);
                    g.setColor(Color.WHITE);
                    g.drawString(bgk.tampilGambar.getText(), 10, 20);
                    g.dispose();

                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    ImageIO.write(bi, "jpg", os);
                    InputStream fis = new ByteArrayInputStream(os.toByteArray());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    for (int readNum; (readNum = fis.read(buf)) != -1;) {
                        bos.write(buf, 0, readNum);
                        System.out.println("read " + readNum + " bytes,");
                        }
                    byte[] bytes = bos.toByteArray();
                    byte[] photo = bytes;
                    prep.setBytes(7, photo);
                } catch (IOException d) {
                    JOptionPane.showMessageDialog(bgk, d);
                }
            }else{
                System.out.println("No Image");
                prep.setBytes(7, null);
            }
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            Hapus(bgk);
            populateJTable(bgk);
            bgk.setLebarKolom();
            Bersih(bgk);
        }
    }
    
    public void Hapus(FormBarangKetemu bgk) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "delete from barangketemu where kd_barang = ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, bgk.txtkode_barang.getText());
            prep.executeUpdate();
            bgk.tampilGambar.setIcon(null);
            //JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public void populateJTable(FormBarangKetemu bgk){
        koneksi mq = new koneksi();
        ArrayList<model_barang2> list = mq.BindTable(bgk);
        String[] columnName = {"Kode", "Kategori","Nama Barang", "Tanggal Temu", "Kontak Penemu", "Keterangan", "Gambar"};
        Object[][] rows = new Object[list.size()][7];
        for(int i=0; i<list.size(); i++){
            rows[i][0] = list.get(i).getID();
            rows[i][1] = list.get(i).getKategori();
            rows[i][2] = list.get(i).getName();
            rows[i][3] = list.get(i).getDate();
            rows[i][4] = list.get(i).getKontak();
            rows[i][5] = list.get(i).getDesc();
            
            if(list.get(i).getMyImage() != null){
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getMyImage()).getImage().getScaledInstance(200, 120 , Image.SCALE_SMOOTH));
                
                rows[i][6] = image;
            }else{
                rows[i][6] = null;
            }
        }
        
        model_barang2 model = new model_barang2(rows, columnName);
        bgk.tableBarang.setModel(model);
        bgk.tableBarang.setRowHeight(120);
        bgk.tableBarang.getColumnModel().getColumn(0).setPreferredWidth(50);
        bgk.tableBarang.getColumnModel().getColumn(1).setPreferredWidth(110);
        bgk.tableBarang.getColumnModel().getColumn(2).setPreferredWidth(160);
        bgk.tableBarang.getColumnModel().getColumn(3).setPreferredWidth(120);
        bgk.tableBarang.getColumnModel().getColumn(4).setPreferredWidth(205);
        bgk.tableBarang.getColumnModel().getColumn(5).setPreferredWidth(480);
        bgk.tableBarang.getColumnModel().getColumn(6).setPreferredWidth(245);
        
    }
    
    public byte[] getMyImage(){
        return gambar;
    }
    
    @Override
    public void Tampil(FormBarangKetemu bgk) throws SQLException {
        try{
            bgk.tbl.getDataVector().removeAllElements();
            bgk.tbl.fireTableDataChanged();
            try{
                Connection con = koneksi.getKoneksi();
                Statement st = con.createStatement();
                String sql = "select * from barang order by kd_barang asc";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    Object[] ob = new Object[8];
                    ob[0] = rs.getString(1);
                    ob[1] = rs.getString(2);
                    ob[2] = rs.getString(3);
                    ob[3] = rs.getString(4);
                    ob[4] = rs.getString(5);
                    /*if(getMyImage() != null){
                        ImageIcon image = new ImageIcon(new ImageIcon(getMyImage()).getImage().getScaledInstance(200, 150 , Image.SCALE_SMOOTH));

                        ob[5] = image;
                    }else{
                        ob[5] = null;
                    }*/
                    bgk.tbl.addRow(ob);
                }
            }catch(Exception e){
                System.out.print(e);
            }
        }catch(Exception e){            
        }
    }

    @Override
    public void Bersih(FormBarangKetemu bgk) throws SQLException {
        bgk.txtKategori.setSelectedItem("-- Pilih Kategori --");
        bgk.txtkode_barang.setText(null);
        bgk.txtnama_barang.setText(null);
        bgk.tanggal_nemu.setDate(null);
        bgk.txtkontak.setText(null);
        bgk.txtspek.setText(null);
    }

    @Override
    public void KlikTabel(FormBarangKetemu bgk) throws SQLException {
        try{
            int pilih = bgk.tableBarang.getSelectedRow();
            /*String s = (String) tableBarang.getModel().getValueAt(pilih, 2);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = f.parse(s);*/
            /*
            if(pilih == -1){
                return;
            }
            */bgk.txtkode_barang.setText(bgk.tableBarang.getValueAt(pilih, 0).toString());
            bgk.txtKategori.setSelectedItem(bgk.tableBarang.getValueAt(pilih, 1).toString());
            bgk.txtnama_barang.setText((String) bgk.tableBarang.getValueAt(pilih, 2).toString());
            bgk.tanggal_nemu.setDate((Date) bgk.tableBarang.getValueAt(pilih, 3));
            bgk.txtkontak.setText(bgk.tableBarang.getValueAt(pilih, 4).toString());
            bgk.txtspek.setText(bgk.tableBarang.getValueAt(pilih, 5).toString());
            if(bgk.tableBarang.getValueAt(pilih, 6) != null){
                ImageIcon image1 = (ImageIcon) bgk.tableBarang.getValueAt(pilih, 6);
                Image image2 = image1.getImage().getScaledInstance(bgk.tampilGambar.getWidth(), bgk.tampilGambar.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image3 = new ImageIcon(image2);
                bgk.tampilGambar.setIcon(image3);
            }else{
                System.out.println("No Image");
                bgk.tampilGambar.setIcon(null);
            }
        }catch(Exception e){
        }
    }
}
