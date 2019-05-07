
package com.controller;

import com.view.FormBarang;
import com.view.FormUserInput;
import java.sql.SQLException;
import java.util.Date;

public interface controller_userinput {
    public String getKategori();
    public void setKategori(String Kategori);
    public String getName();
    public void setName(String Name);
    public Date getDate();
    public void setDate(Date date);
    public String getKontak();
    public void setKontak(String kontak);
    public String getDesc();
    public void setDesc(String desc);
    public byte[] getMyImage();
    public void Simpan (FormUserInput fui) throws SQLException;
    public void Bersih(FormUserInput fui) throws SQLException;
}
