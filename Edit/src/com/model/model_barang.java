
package com.model;
import com.controller.controller_barang;
import com.koneksi.koneksi;
import com.view.FormBarang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
public class model_barang implements controller_barang{

    @Override
    public void Simpan(FormBarang bg) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "insert barang values(?,?,?,?)";
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, bg.txtkode_barang.getText());
            prep.setString(2, bg.txtnama_barang.getText());
            prep.setString(3, bg.tgl);
            prep.setString(4, bg.txtspek.getText());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            Tampil(bg);
            bg.setLebarKolom();
            Bersih(bg);
        }
    }

    @Override
    public void Ubah(FormBarang bg) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "update barang set nm_barang=?,"
                         + "tgl_temu = ?,"
                         + "keterangan = ?"
                         + "where kd_barang = ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, bg.txtnama_barang.getText());
            prep.setString(2, bg.tgl);
            prep.setString(3, bg.txtspek.getText());
            prep.setString(4, bg.txtkode_barang.getText());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            Tampil(bg);
            bg.setLebarKolom();;
            Bersih(bg);
        }
    }

    @Override
    public void Hapus(FormBarang bg) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "delete from barang where kd_barang = ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, bg.txtkode_barang.getText());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            Tampil(bg);
            bg.setLebarKolom();
            Bersih(bg);
        }
    }

    @Override
    public void Tampil(FormBarang bg) throws SQLException {
        try{
            bg.tbl.getDataVector().removeAllElements();
            bg.tbl.fireTableDataChanged();
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
                    bg.tbl.addRow(ob);
                }
            }catch(Exception e){
                System.out.print(e);
            }
        }catch(Exception e){            
        }
    }

    @Override
    public void Bersih(FormBarang bg) throws SQLException {
        bg.txtkode_barang.setText(null);
        bg.txtnama_barang.setText(null);
        bg.tanggal_nemu.setDate(null);
        bg.txtspek.setText(null);
    }

    @Override
    public void KlikTabel(FormBarang bg) throws SQLException {
        try{
            int pilih = bg.tableBarang.getSelectedRow();
            String s = (String) bg.tableBarang.getModel().getValueAt(pilih, 2);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = f.parse(s);
            
            if(pilih == -1){
                return;
            }
            bg.txtkode_barang.setText(bg.tbl.getValueAt(pilih, 0).toString());
            bg.txtnama_barang.setText(bg.tbl.getValueAt(pilih, 1).toString());
            bg.tanggal_nemu.setDate(d);
            bg.txtspek.setText(bg.tbl.getValueAt(pilih, 3).toString());
        }catch(Exception e){
        }
    }
}
