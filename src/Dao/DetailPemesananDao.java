/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Controller.Koneksi;
import Models.dt_pemesanan;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DetailPemesananDao extends dt_pemesanan {
    Koneksi con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    String query;
    
    public String IdDetailPemesanan(){ 
        
        String id= "";
        java.sql.Connection conn = new Koneksi().connect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select *from dt_pemesanan ORDER BY id_detail DESC");
            if (rs.first() == false) {
                id = ("DT0001");
            } else {
                rs.first();
                System.out.println("COT: " + rs.getString("id_detail").substring(2, 6));
                int nokirim = Integer.valueOf(rs.getString("id_detail").substring(2, 6)) + 1;
                System.out.println(nokirim);
                if (nokirim < 10) {
                    id = ("DT000" + nokirim);
                } else if (nokirim >= 10 && nokirim < 100) {
                    id = ("DT00" + nokirim);
                } else if (nokirim >= 100 && nokirim < 1000) {
                    id = ("DT0" + nokirim);
                } else if (nokirim >= 1000 && nokirim < 9999) {
                    id = ("DT" + nokirim);
                }
            }
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
        }
        
        return id;
    }
    
    public void Save(String idDetail, String idPemesanan, String idItem, String namaItem, String kategori, int harga, int qty, int total) {
        con = new Koneksi();
        con.connect();
        try {
            pst = con.conn.prepareStatement("insert into dt_pemesanan(id_detail, id_pemesanan, id_item, nama_item, kategori, harga, qty, total)values(?,?,?,?,?,?,?,?)");
            try {
                pst.setString(1, idDetail);
                pst.setString(2, idPemesanan);
                pst.setString(3, idItem);
                pst.setString(4, namaItem);
                pst.setString(5, kategori);
                pst.setInt(6, harga);
                pst.setInt(7, qty);
                pst.setInt(8, total);
                pst.executeUpdate();
                pst.close();
                con.conn.close();
                JOptionPane.showMessageDialog(null, "Data di Tambahkan !!");
            } catch (SQLException e) {

            }
        } catch (SQLException ex) {

        }
    }
    
    public String[][] ShowDataById( String idPemesanan) {

        rs = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "SELECT COUNT(id_detail) AS Jumlah FROM dt_pemesanan";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("Jumlah");
            }
            query = "select *from dt_pemesanan WHERE id_pemesanan = '"+idPemesanan+"'";
            rs = st.executeQuery(query);
            data = new String[jumlahBaris][8];
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("id_detail");
                data[r][1] = rs.getString("id_pemesanan");
                data[r][2] = rs.getString("id_item");
                data[r][3] = rs.getString("nama_item");
                data[r][4] = rs.getString("kategori");
                data[r][5] = rs.getString("harga");
                data[r][6] = rs.getString("qty");
                data[r][7] = rs.getString("total");
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][8];
            for (r = 0; r < jmlBaris; r++) {
                for (int c = 0; c < 8; c++) {
                    data[r][c] = tmpArray[r][c];
                }
            }
            st.close();
            con.conn.close();
        } catch (SQLException e) {
            System.err.println("SQLException : " + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<String> ShowMenu() {
        ArrayList<String> listMenu = new ArrayList<String>();
        con = new Koneksi();
        con.connect();
        try {
            st = con.connect().createStatement();
            rs = st.executeQuery("SELECT *FROM tb_menu");
            while (rs.next()) {
                listMenu.add(rs.getString("nama_menu"));
            }
        } catch (SQLException ex) {

        }

        return listMenu;
    }
    
    public ArrayList<String> ShowDataByMenu(String nama) {
        ArrayList<String> listMenu = new ArrayList<String>();
        con = new Koneksi();
        con.connect();
        try {
            st = con.connect().createStatement();
            rs = st.executeQuery("SELECT *FROM tb_menu WHERE nama_menu = '"+nama+"'");
            while (rs.next()) {
                listMenu.add(rs.getString("id_menu"));
                listMenu.add(rs.getString("kategori"));
                listMenu.add(rs.getString("harga"));
            }
        } catch (SQLException ex) {

        }

        return listMenu;
    }
    
    public void Delete(String idDetail) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "delete from dt_pemesanan where id_detail = '" + idDetail + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
        } catch (SQLException e) {

        }
    }
}
