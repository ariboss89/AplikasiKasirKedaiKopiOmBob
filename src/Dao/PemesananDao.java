/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Controller.Koneksi;
import Models.tb_pemesanan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PemesananDao extends tb_pemesanan{
    
    Koneksi con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    String query;
    
    public String IdPemesanan(){ 
        
        String id= "";
        java.sql.Connection conn = new Koneksi().connect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select *from tb_pemesanan ORDER BY id_pemesanan DESC");
            if (rs.first() == false) {
                id = ("ORD-0001");
            } else {
                rs.first();
                System.out.println("COT: " + rs.getString("id_pemesanan").substring(4, 8));
                int nokirim = Integer.valueOf(rs.getString("id_pemesanan").substring(4, 8)) + 1;
                System.out.println(nokirim);
                if (nokirim < 10) {
                    id = ("ORD-000" + nokirim);
                } else if (nokirim >= 10 && nokirim < 100) {
                    id = ("ORD-00" + nokirim);
                } else if (nokirim >= 100 && nokirim < 1000) {
                    id = ("ORD-0" + nokirim);
                } else if (nokirim >= 1000 && nokirim < 9999) {
                    id = ("ORD-" + nokirim);
                }
            }
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
        }
        
        return id;
    }
    
    public void Save(String idPemesanan, Date tanggal, String noMeja, int total_item, int total_harga, String nama_kasir) {
        con = new Koneksi();
        con.connect();
        try {
            pst = con.conn.prepareStatement("INSERT INTO tb_pemesanan( id_pemesanan, tanggal, no_meja, total_item, total_harga, nama_kasir, status) values(?,?,?,?,?,?,?)");
            try {
                pst.setString(1, idPemesanan);
                pst.setDate(2, (java.sql.Date) tanggal);
                pst.setString(3, noMeja);
                pst.setInt(4, total_item);
                pst.setInt(5, total_harga);
                pst.setString(6, nama_kasir);
                pst.setString(7, "PENDING");
                pst.executeUpdate();
                pst.close();
                con.conn.close();
                JOptionPane.showMessageDialog(null, "Data di Simpan !!");
            } catch (SQLException e) {

            }
        } catch (SQLException ex) {

        }
    }
    
    public String[][] ShowData() {

        rs = null;
        String[][] data = null;
        con = new Koneksi();
        con.connect();
        int jumlahBaris = 0;
        try {
            st = con.conn.createStatement();
            query = "SELECT COUNT(id_menu) AS Jumlah FROM tb_menu";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("Jumlah");
            }
            query = "select *from tb_menu";
            rs = st.executeQuery(query);
            data = new String[jumlahBaris][4];
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("id_menu");
                data[r][1] = rs.getString("nama_menu");
                data[r][2] = rs.getString("kategori");
                data[r][3] = rs.getString("harga");
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][4];
            for (r = 0; r < jmlBaris; r++) {
                for (int c = 0; c < 4; c++) {
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
}
