/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Controller.Koneksi;
import Models.tb_menu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class MenuDao extends tb_menu{
    
    private Koneksi con;
    private ResultSet rs;
    private Statement st;
    private String query;
   
    public String IdMenu(){ 
        
        String id= "";
        java.sql.Connection conn = new Koneksi().connect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select *from tb_menu ORDER BY id_menu DESC");
            if (rs.first() == false) {
                id = ("M001");
            } else {
                rs.first();
                System.out.println("COT: " + rs.getString("id_menu").substring(1, 4));
                int nokirim = Integer.valueOf(rs.getString("id_menu").substring(1, 4)) + 1;
                System.out.println(nokirim);
                if (nokirim < 10) {
                    id = ("M00" + nokirim);
                } else if (nokirim >= 10 && nokirim < 100) {
                    id = ("M0" + nokirim);
                } else if (nokirim >= 100 && nokirim < 1000) {
                    id = ("M" + nokirim);
                }
            }
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
        }
        
        return id;
    }
    
    public void Save(String idMenu, String namaMenu, String kategori, int harga) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tb_menu(id_menu, nama_menu, kategori, harga)values('" + idMenu + "','" + namaMenu + "','" + kategori + "','" + harga + "')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
        } catch (SQLException e) {
        }
    }
    
    public void Update(String idMenu, String namaMenu, String kategori, int harga) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update tb_menu set nama_menu='" + namaMenu + "', kategori='" + kategori + "', harga='" + harga + "' where id_menu = '" + idMenu + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Update");
        } catch (SQLException e) {

        }
    }
    
    public void Delete(String idMenu) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "delete from tb_menu where id_menu = '" + idMenu + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
        } catch (SQLException e) {

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

    public String[][] SearchData(String search) {

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
            query = "select *from tb_menu where nama_menu like '%"+ search +"%' OR kategori like '%"+ search +"%'";
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
                for (int c = 0; c <4; c++) {
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
