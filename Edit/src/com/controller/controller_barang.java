package com.controller;

import com.view.FormBarang;
import java.sql.SQLException;


public interface controller_barang {
    public void Simpan (FormBarang bg) throws SQLException;
    public void Ubah (FormBarang bg) throws SQLException;
    public void Hapus (FormBarang bg) throws SQLException;
    public void Tampil (FormBarang bg) throws SQLException;
    public void Bersih (FormBarang bg) throws SQLException;
    public void KlikTabel (FormBarang bg) throws SQLException;
}
