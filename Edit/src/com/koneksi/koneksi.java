package com.koneksi;

import com.model.model_barang2;
import com.model.model_usercari;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class koneksi {
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        if(koneksi == null){
            try{
                String url = "jdbc:mysql://localhost/tubes_pbo_jaya";
                String user = "root";
                String pwd = "";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = (Connection) DriverManager.getConnection(url, user, pwd);
                
            //    JOptionPane.showMessageDialog(null, "Koneksi Sukses!!!");
            }catch(Exception e){
                System.out.println(e);
            }
        }return koneksi;
    }
    
    public ArrayList<model_barang2> BindTable() {
        ArrayList<model_barang2> list = new ArrayList<model_barang2>();
        Connection con = getKoneksi();
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM barang");

            model_barang2 p;
            while (rs.next()) {
                p = new model_barang2(
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
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static void main(String[] args) {
    //    Connection koneksi = new koneksi().getKoneksi();
    }
}
