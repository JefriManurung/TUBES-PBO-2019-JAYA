
package com.model;

import com.controller.controller_login;
import com.koneksi.koneksi;
import com.view.FormBarang;
import com.view.FormLogin;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class model_login implements controller_login{
    @Override
    public void Login(FormLogin lg) throws SQLException {
       try{
           String password = new String(lg.txtpass.getPassword());
           Connection con = koneksi.getKoneksi();
           Statement st = con.createStatement();
           String sql = "SELECT * FROM account where username ='" + lg.txtuser.getText()
                        + "' and password = '" + password + "'";
           ResultSet rs = st.executeQuery(sql);
           if(rs.next()){
               if(rs.getString(4).equals("admin")){
                   FormBarang bg = new FormBarang();
                   JOptionPane.showMessageDialog(lg, "Selamat Datang Admin!");
                   bg.setVisible(true);
                   lg.setVisible(false);
               }else if(rs.getString(4).equals("user")){
                   JOptionPane.showMessageDialog(lg, "Selamat Datang User!");
               }
           }else{
               JOptionPane.showMessageDialog(lg, "Periksa Kembali Username Anda!");
               lg.txtuser.requestFocus();
           }
       }catch(Exception e){
       }
    }
}
