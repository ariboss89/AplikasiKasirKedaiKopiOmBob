/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Controller.Koneksi;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class ReportDao {
    private Koneksi con;

    public void CetakStruk(String Id) {
        
        con = new Koneksi();

        try {
            HashMap parameter = new HashMap();
            File file = new File("src/Reports/StrukPemesanan.jasper");
            parameter.put("idpemesanan", Id);
            JasperReport jp = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, parameter, con.connect());
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void CetakLaporanPenjualan(Date dari, Date sampai) {
        
        con = new Koneksi();

        try {
            HashMap parameter = new HashMap();
            File file = new File("src/Reports/LaporanPenjualan.jasper");
            parameter.put("dari", dari);
            parameter.put("sampai", sampai);
            JasperReport jp = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, parameter, con.connect());
            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
