package com.koneksi;

import com.model.model_usercari;
import com.view.FormUserCari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;

/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class koneksi_usercari {

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/tubes_pbo_jaya", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public ArrayList<model_usercari> BindTable(FormUserCari fuc, int klik) {
        ArrayList<model_usercari> list = new ArrayList<model_usercari>();
        Connection con = getConnection();
        Statement st;
        ResultSet rs = null;
        
        String cariNama = fuc.txtcariNama.getText();
        String cariKategori = fuc.txtcariKategori.getSelectedItem().toString();
        
        try {
            st = con.createStatement();
            
            if(klik == 0){
                rs = st.executeQuery("SELECT * FROM barang");
            }else if(klik == 1){
                rs = st.executeQuery("SELECT * FROM barang WHERE nm_barang LIKE '%"+cariNama+"%'");
            }else if(klik == 2){
                if(cariKategori.equals("-- Pilih Kategori --")){
                    rs = st.executeQuery("SELECT * FROM barang");
                }else if(!cariKategori.equals("-- Pilih Kategori --")){
                    rs = st.executeQuery("SELECT * FROM barang WHERE kategori LIKE '%"+cariKategori+"%'");
                }
            }
            
            model_usercari p;
            while (rs.next()) {
                p = new model_usercari(
                        rs.getString("kd_barang"),
                        rs.getString("kategori"),
                        rs.getString("nm_barang"),
                        rs.getDate("tgl_temu"),
                        rs.getString("kontak_penemu"),
                        rs.getString("keterangan"),
                        rs.getBytes("gambar")
                );
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(koneksi_usercari.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
