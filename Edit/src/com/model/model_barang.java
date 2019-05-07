
package com.model;
import com.controller.controller_barang;
import com.koneksi.koneksi;
import com.koneksi.koneksi_usercari;
import com.view.FormBarang;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class model_barang implements controller_barang{
    byte[] gambar;
    
    @Override
    public void Simpan(FormBarang bg) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "insert barang(kategori, nm_barang, tgl_temu, kontak_penemu, keterangan) values(?,?,?,?,?)";
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, bg.txtKategori.getSelectedItem().toString());
            prep.setString(2, bg.txtnama_barang.getText());
            prep.setString(3, bg.tgl);
            prep.setString(4, bg.txtkontak.getText());
            prep.setString(5, bg.txtspek.getText());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            populateJTable(bg);
            bg.setLebarKolom();
            Bersih(bg);
        }
    }

    @Override
    public void Ubah(FormBarang bg) throws SQLException {
        try{
            Connection con = koneksi.getKoneksi();
            String sql = "update barang set kategori=?,"
                         + "nm_barang=?,"
                         + "tgl_temu = ?,"
                         + "kontak_penemu = ?,"
                         + "keterangan = ?"
                         //+ "gambar = ? "
                         + "where kd_barang = ?";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, bg.txtKategori.getSelectedItem().toString());
            prep.setString(2, bg.txtnama_barang.getText());
            prep.setString(3, bg.tgl);
            prep.setString(4, bg.txtkontak.getText());
            prep.setString(5, bg.txtspek.getText());
            prep.setString(6, bg.txtkode_barang.getText());
            //prep.setString(6, null);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah!");
            prep.close();
        }catch(Exception e){
            System.out.print(e);
        }finally{
            populateJTable(bg);
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
            populateJTable(bg);
            bg.setLebarKolom();
            Bersih(bg);
        }
    }
    
    public void populateJTable(FormBarang bg){
        koneksi mq = new koneksi();
        ArrayList<model_barang2> list = mq.BindTable();
        String[] columnName = {"Kode", "Kategori","Nama Barang", "Tanggal Temu", "Kontak Penemu", "Keterangan", "Gambar"};
        Object[][] rows = new Object[list.size()][7];
        for(int i=0; i<list.size(); i++){
            rows[i][0] = list.get(i).getID();
            rows[i][1] = list.get(i).getKategori();
            rows[i][2] = list.get(i).getName();
            rows[i][3] = list.get(i).getDate();
            rows[i][4] = list.get(i).getKontak();
            rows[i][5] = list.get(i).getDesc();
            
            if(list.get(i).getMyImage() != null){
                ImageIcon image = new ImageIcon(new ImageIcon(list.get(i).getMyImage()).getImage().getScaledInstance(200, 120 , Image.SCALE_SMOOTH));
                
                rows[i][6] = image;
            }else{
                rows[i][6] = null;
            }
        }
        
        model_barang2 model = new model_barang2(rows, columnName);
        bg.tableBarang.setModel(model);
        bg.tableBarang.setRowHeight(120);
        bg.tableBarang.getColumnModel().getColumn(0).setPreferredWidth(40);
        bg.tableBarang.getColumnModel().getColumn(1).setPreferredWidth(110);
        bg.tableBarang.getColumnModel().getColumn(2).setPreferredWidth(160);
        bg.tableBarang.getColumnModel().getColumn(3).setPreferredWidth(100);
        bg.tableBarang.getColumnModel().getColumn(4).setPreferredWidth(150);
        bg.tableBarang.getColumnModel().getColumn(5).setPreferredWidth(570);
        bg.tableBarang.getColumnModel().getColumn(6).setPreferredWidth(200);
        
    }
    
    public byte[] getMyImage(){
        return gambar;
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
                    ob[4] = rs.getString(5);
                    /*if(getMyImage() != null){
                        ImageIcon image = new ImageIcon(new ImageIcon(getMyImage()).getImage().getScaledInstance(200, 150 , Image.SCALE_SMOOTH));

                        ob[5] = image;
                    }else{
                        ob[5] = null;
                    }*/
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
        bg.txtKategori.setSelectedItem("-- Pilih Kategori --");
        bg.txtkode_barang.setText(null);
        bg.txtnama_barang.setText(null);
        bg.tanggal_nemu.setDate(null);
        bg.txtkontak.setText(null);
        bg.txtspek.setText(null);
    }

    @Override
    public void KlikTabel(FormBarang bg) throws SQLException {
        try{
            int pilih = bg.tableBarang.getSelectedRow();
            /*String s = (String) tableBarang.getModel().getValueAt(pilih, 2);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = f.parse(s);*/
            /*
            if(pilih == -1){
                return;
            }
            */bg.txtkode_barang.setText(bg.tableBarang.getValueAt(pilih, 0).toString());
            bg.txtKategori.setSelectedItem(bg.tableBarang.getValueAt(pilih, 1).toString());
            bg.txtnama_barang.setText((String) bg.tableBarang.getValueAt(pilih, 2).toString());
            bg.tanggal_nemu.setDate((Date) bg.tableBarang.getValueAt(pilih, 3));
            bg.txtkontak.setText(bg.tableBarang.getValueAt(pilih, 4).toString());
            bg.txtspek.setText(bg.tableBarang.getValueAt(pilih, 5).toString());
            if(bg.tableBarang.getValueAt(pilih, 6) != null){
                ImageIcon image1 = (ImageIcon) bg.tableBarang.getValueAt(pilih, 6);
                Image image2 = image1.getImage().getScaledInstance(bg.tampilGambar.getWidth(), bg.tampilGambar.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image3 = new ImageIcon(image2);
                bg.tampilGambar.setIcon(image3);
            }else{
                System.out.println("No Image");
                bg.tampilGambar.setIcon(null);
            }
        }catch(Exception e){
        }
    }
}
