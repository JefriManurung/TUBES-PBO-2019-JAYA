package com.model;

import com.controller.controller_usercari;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;


public class model_usercari extends AbstractTableModel implements controller_usercari{

    private String[] columns;
    private Object[][] rows;
    private String id;
    private String name;
    private Date tgl;
    private String kontak;
    private String ket;
    private byte[] Image;
    
    public model_usercari(){}
    
    public model_usercari(Object[][] data, String[] columnName){
    
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
    
    public model_usercari(String Id, String Name, Date tgl, String kontak, String ket, byte[] image){
        this.id = Id;
        this.name = Name;
        this.tgl = tgl;
        this.kontak = kontak;
        this.ket = ket;
        this.Image = image;
    }
    
    @Override
    public String getID(){
      return id;
    }
    
    @Override
    public void setID(String ID){
        this.id = ID;
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


}