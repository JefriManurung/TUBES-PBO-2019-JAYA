package com.model;

import com.controller.controller_usercari;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;


public class model_barang2 extends AbstractTableModel implements controller_usercari{

    private String[] columns2;
    private Object[][] rows2;
    private String id2;
    private String kategori2;
    private String name2;
    private Date tgl2;
    private String kontak2;
    private String ket2;
    private byte[] Image2;
    
    public model_barang2(){}
    
    public model_barang2(Object[][] data, String[] columnName){
    
        this.rows2 = data;
        this.columns2 = columnName;
    }

    
    public Class getColumnClass(int column){
// 4 is the index of the column image
        if(column == 6){
            return Icon.class;
        }
        else{
            return getValueAt(0,column).getClass();
        }
    }
    
    
    public int getRowCount() {
     return this.rows2.length;
    }

    public int getColumnCount() {
     return this.columns2.length;
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    return this.rows2[rowIndex][columnIndex];
    }
    public String getColumnName(int col){
        return this.columns2[col];
    }
    
    public model_barang2(String Id, String Kategori, String Name, Date tgl, String kontak, String ket, byte[] image){
        this.id2 = Id;
        this.kategori2 = Kategori;
        this.name2 = Name;
        this.tgl2 = tgl;
        this.kontak2 = kontak;
        this.ket2 = ket;
        this.Image2 = image;
    }
    
    @Override
    public String getID(){
      return id2;
    }
    
    @Override
    public void setID(String ID){
        this.id2 = ID;
    }
    
    @Override
    public String getKategori(){
        return kategori2;
    }
    
    @Override
    public void setKategori(String Kategori){
        this.kategori2 = Kategori;
    }
    
    @Override
    public String getName(){
        return name2;
    }
    
    @Override
    public void setName(String Name){
        this.name2 = Name;
    }
    
    @Override
    public byte[] getMyImage(){
        return Image2;
    }

    @Override
    public Date getDate() {
        return tgl2;
    }

    @Override
    public void setDate(Date date) {
        this.tgl2 = date;
    }
    
    @Override
    public String getKontak() {
        return kontak2;
    }

    @Override
    public void setKontak(String kontak) {
        this.kontak2 = kontak;
    }

    @Override
    public String getDesc() {
        return ket2;
    }

    @Override
    public void setDesc(String desc) {
        this.ket2 = desc;
    }


}