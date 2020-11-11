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
public class PenggunaDao extends tb_menu{
    
    private Koneksi con;
    private ResultSet rs;
    private Statement st;
    private String query;
   
    public void Save(String username, String password, String role) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "insert into tb_pengguna(username, password, role)values('" + username + "','" + password + "','" + role + "')";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
        } catch (SQLException e) {
        }
    }
    
    public void Update(String username, String password) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "update tb_pengguna set password='" + password + "' where username = '" + username + "'";
            st.executeUpdate(query);
            st.close();
            con.conn.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Update");
        } catch (SQLException e) {

        }
    }
    
    public void Delete(String username) {
        con = new Koneksi();
        con.connect();
        try {
            st = con.conn.createStatement();
            query = "delete from tb_pengguna where username = '" + username + "'";
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
            query = "SELECT COUNT(username) AS Jumlah FROM tb_pengguna";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("Jumlah");
            }
            query = "select *from tb_pengguna";
            rs = st.executeQuery(query);
            data = new String[jumlahBaris][2];
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("username");
                data[r][1] = rs.getString("role");
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][2];
            for (r = 0; r < jmlBaris; r++) {
                for (int c = 0; c < 2; c++) {
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
            query = "SELECT COUNT(username) AS Jumlah FROM tb_pengguna";
            rs = st.executeQuery(query);
            if (rs.next()) {
                jumlahBaris = rs.getInt("Jumlah");
            }
            query = "select *from tb_pengguna WHERE username like '%"+search+"%' or role like '%"+search+"%'";
            rs = st.executeQuery(query);
            data = new String[jumlahBaris][2];
            int r = 0;
            while (rs.next()) {
                data[r][0] = rs.getString("username");
                data[r][1] = rs.getString("role");
                r++;
            }
            int jmlBaris = r;
            String[][] tmpArray = data;
            data = new String[jmlBaris][2];
            for (r = 0; r < jmlBaris; r++) {
                for (int c = 0; c < 2; c++) {
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
