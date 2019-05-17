package com.controller;

import com.view.FormBarangKetemu;
import java.sql.SQLException;


public interface controller_barangketemu {
    public void Kembalikan (FormBarangKetemu bgk) throws SQLException;
    public void Tampil (FormBarangKetemu bgk) throws SQLException;
    public void Bersih (FormBarangKetemu bgk) throws SQLException;
    public void KlikTabel (FormBarangKetemu bgk) throws SQLException;
}
