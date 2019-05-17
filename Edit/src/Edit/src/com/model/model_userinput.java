package com.model;

import com.controller.controller_userinput;
import com.koneksi.koneksi;
import com.view.FormUserInput;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class model_userinput extends AbstractTableModel implements controller_userinput{

    private String[] columns;
    private Object[][] rows;
    private String kategori;
    private String name;
    private Date tgl;
    private String kontak;
    private String ket;
    private byte[] Image;
    
    public model_userinput(){}
    
    public model_userinput(Object[][] data, String[] columnName){
    
        this.rows = data;
        this.columns = columnName;
    }

    
    public Class getColumnClass(int column){
// 4 is the index of the column image
        if(column == 5){
            return Icon.class;
        }
        else{
            return getValueAt(0,column).getClass();
        }
    }
    
    
    public int getRowCount() {
     return this.rows.length;
    }

    public int getColumnCount() {
     return this.columns.length;
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    return this.rows[rowIndex][columnIndex];
    }
    public String getColumnName(int col){
        return this.columns[col];
    }
    
    public model_userinput(String Kategori, String Name, Date tgl, String kontak, String ket, byte[] image){
        this.kategori = Kategori;
        this.name = Name;
        this.tgl = tgl;
        this.kontak = kontak;
        this.ket = ket;
        this.Image = image;
    }
    
    @Override
    public String getKategori(){
        return kategori;
    }
    
    @Override
    public void setKategori(String Kategori){
        this.kategori = Kategori;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public void setName(String Name){
        this.name = Name;
    }
    
    @Override
    public byte[] getMyImage(){
        return Image;
    }

    @Override
    public Date getDate() {
        return tgl;
    }

    @Override
    public void setDate(Date date) {
        this.tgl = date;
    }
    
    @Override
    public String getKontak() {
        return kontak;
    }

    @Override
    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    @Override
    public String getDesc() {
        return ket;
    }

    @Override
    public void setDesc(String desc) {
        this.ket = desc;
    }

    @Override
    public void Simpan(FormUserInput fui) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "insert barang(kategori, nm_barang, tgl_temu, kontak_penemu, keterangan, gambar) values(?,?,?,?,?,?)";
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, fui.txt_kategori.getSelectedItem().toString());
            prep.setString(2, fui.txt_namabarang.getText());
            prep.setString(3, fui.tgl);
            prep.setString(4, fui.txt_kontakpenemu.getText());
            prep.setString(5, fui.txt_keterangan.getText());
            if(!fui.txt_namagambar.getText().equals("")){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                File img = new File(fui.path);
                FileInputStream fis = new FileInputStream(img);
                try{
                    byte[] buf = new byte[1024];
                    for(int readbyte; (readbyte = fis.read(buf)) != -1;){
                        baos.write(buf, 0, readbyte);
                    }
                    fui.bitarray = baos.toByteArray();
                    fui.gambar = fui.bitarray;
                }catch(Exception e){
                }
            }
            prep.setBytes(6, fui.gambar);
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Gagal Simpan!");
        }finally{
            Bersih(fui);
        }
    }

    @Override
    public void Bersih(FormUserInput fui) throws SQLException {
        fui.txt_kategori.setSelectedItem("-- Pilih Kategori --");
        fui.txt_namabarang.setText(null);
        fui.txt_tanggaltemu.setDate(null);
        fui.txt_kontakpenemu.setText(null);
        fui.txt_keterangan.setText(null);
        fui.txt_namagambar.setText(null);
        fui.lbgambar.setIcon(null);
    }
}