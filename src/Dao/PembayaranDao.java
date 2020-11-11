/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Controller.Koneksi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PembayaranDao {
    Koneksi con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    String query;
    
    public void Update(int bayar, int kembalian, String idPemesanan) {
        con = new Koneksi();
        con.connect();
        try {
            pst = con.conn.prepareStatement("UPDATE tb_pemesanan SET bayar =?, kembalian=?, status = 'SELESAI' WHERE id_pemesanan =?");
            try {
                pst.setInt(1, bayar);
                pst.setInt(2, kembalian);
                pst.setString(3, idPemesanan);
                pst.executeUpdate();
                pst.close();
                con.conn.close();
                JOptionPane.showMessageDialog(null, "Sedang Mencetak Struk ...");
            } catch (SQLException e) {

            }
        } catch (SQLException ex) {

        }
    }
    
    public ArrayList<String> ShowIdPemesanan() {
        ArrayList<String> listMenu = new ArrayList<String>();
        con = new Koneksi();
        con.connect();
        try {
            st = con.connect().createStatement();
            rs = st.executeQuery("SELECT *FROM tb_pemesanan WHERE status = 'PENDING'");
            while (rs.next()) {
                listMenu.add(rs.getString("id_pemesanan"));
            }
        } catch (SQLException ex) {

        }

        return listMenu;
    }
    
    public ArrayList<String> ShowDataByIdPemesanan(String idPemesanan) {
        ArrayList<String> listMenu = new ArrayList<String>();
        con = new Koneksi();
        con.connect();
        try {
            st = con.connect().createStatement();
            rs = st.executeQuery("SELECT *FROM tb_pemesanan WHERE id_pemesanan = '"+idPemesanan+"'");
            while (rs.next()) {
                listMenu.add(rs.getString("tanggal"));
                listMenu.add(rs.getString("no_meja"));
                listMenu.add(rs.getString("total_item"));
                listMenu.add(rs.getString("total_harga"));
                listMenu.add(rs.getString("nama_kasir"));
            }
        } catch (SQLException ex) {

        }

        return listMenu;
    }
}
