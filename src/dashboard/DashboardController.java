/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import geter_seter.bagian;
import java.io.File;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardController implements Initializable {

    final ObservableList options = FXCollections.observableArrayList();
    private ObservableList<bagian> data;
    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;
    private Statement st;

    @FXML
    private ImageView foto;
    @FXML
    private JFXTextField cari;
    @FXML
    private JFXTextField nama;
    @FXML
    private JFXButton btncari;
    @FXML
    private JFXTextField bagian;
    @FXML
    private JFXTextField klasifikasi;
    @FXML
    private ComboBox cbid;
    @FXML
    private JFXTextField tempat;
    @FXML
    private JFXTextField tgl;
    @FXML
    private JFXTextField alamat;
    @FXML
    private JFXTextField agama;
    @FXML
    private JFXTextField noktp;
    @FXML
    private JFXTextField nonpwp;
    @FXML
    private JFXTextField norek;
    @FXML
    private JFXTextField nobpjssehat;
    @FXML
    private JFXTextField nobpjskenaker;
    @FXML
    private JFXTextField nohp;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField nosepatu;
    @FXML
    private JFXTextField nocelana;
    @FXML
    private JFXTextField nohelm;
    @FXML
    private JFXTextField nobaju;
    @FXML
    private JFXTextField nosarung;
    @FXML
    private JFXButton btnprint;
    @FXML
    private JFXButton btnrefresh;
    @FXML
    private JFXButton btnhelp;
    @FXML
    private JFXButton btnclose;
    @FXML
    private JFXRadioButton rbdatabagian;
    @FXML
    private JFXCheckBox ckkes;

    @FXML
    private JFXTextField txtsr;

    @FXML
    private JFXTextField txtfck;

    @FXML
    private JFXTextField txtfckk;

    @FXML
    private JFXTextField txtfcit;

    @FXML
    private JFXTextField txtfcn;

    @FXML
    private JFXTextField txtfcbk;

    @FXML
    private JFXTextField txtfcbpk;

    @FXML
    private JFXTextField txtpasfoto;

    @FXML
    private JFXRadioButton rbdataklasi;

    public void konek() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demi", "root", "admin");
            pst = (PreparedStatement) conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Exception" + e.getMessage());
        }

    }

    private void cetak() throws JRException {

        HashMap params = new HashMap();
        params.put("nama", nama.getText());
        params.put("bagian", bagian.getText());
        params.put("klasifikasi", klasifikasi.getText());
        params.put("tempat", tempat.getText());
        params.put("tanggal", tgl.getText());
        params.put("alamat", alamat.getText());
        params.put("agama", agama.getText());
        params.put("noktp", noktp.getText());
        params.put("nonpwp", nonpwp.getText());
        params.put("norek", norek.getText());
        params.put("nobpjssehat", nobpjssehat.getText());
        params.put("nobpjskenaker", nobpjskenaker.getText());
        params.put("hp", nohp.getText());
        params.put("sepatu", nosepatu.getText());
        params.put("baju", nobaju.getText());
        params.put("celana", nocelana.getText());
        params.put("helm", nohelm.getText());
        params.put("sarung", nosarung.getText());
        params.put("email", email.getText());
        //File folo = new File(rs.getString(28));
        //Image image = new Image(folo.toURI().toString());
        params.put("foto", foto.getImage());

        String report = "src\\Laporan\\Lap.jrxml";
        JasperReport jr = JasperCompileManager.compileReport(report);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());
        JasperViewer.viewReport(jp, false);

    }

    @FXML
    private void cetakPDF(ActionEvent event) throws JRException {
        prinkkes();
    }

    @FXML
    private void cari(ActionEvent event) throws SQLException {
        if (!cari.getText().isEmpty() && cari.getText() != null) {
            setkefield(cari.getText());
        }
    }

    private void setkefield(String kar_id) throws SQLException {
        konek();
        try {
            String query = "select * from dash where id_karyawan = ? ";
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, kar_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama.setText(rs.getString(1));
                bagian.setText(rs.getString(3));
                klasifikasi.setText(rs.getString(4));
                tempat.setText(rs.getString(5));
                tgl.setText(rs.getString(6));
                alamat.setText(rs.getString(7));
                agama.setText(rs.getString(8));
                noktp.setText(rs.getString(9));
                nonpwp.setText(rs.getString(10));
                norek.setText(rs.getString(11));
                nobpjssehat.setText(rs.getString(12));
                nobpjskenaker.setText(rs.getString(13));
                nohp.setText(rs.getString(14));
                nosepatu.setText(rs.getString(15));
                nobaju.setText(rs.getString(16));
                nocelana.setText(rs.getString(17));
                nohelm.setText(rs.getString(18));
                nosarung.setText(rs.getString(19));
                txtsr.setText(rs.getString(20));
                txtfck.setText(rs.getString(21));
                txtfckk.setText(rs.getString(22));
                txtfcit.setText(rs.getString(23));
                txtfcn.setText(rs.getString(24));
                txtfcbk.setText(rs.getString(25));
                txtfcbpk.setText(rs.getString(26));
                txtpasfoto.setText(rs.getString(27));
                email.setText(rs.getString(29));
                File folo = new File(rs.getString(28));
                Image image = new Image(folo.toURI().toString());
                foto.setImage(image);
            }
        } catch (Exception ex) {

        }
    }

    private void bagian() throws SQLException {
        String report = "src\\Laporan\\Bagian.jrxml";
        konek();
        try {

            JasperReport rpt = JasperCompileManager.compileReport(report);
            Map params = new HashMap();
            params.put("bag", cbid.getValue());
            JasperPrint jp = JasperFillManager.fillReport(rpt, params, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
        private void klasi() throws SQLException {
        String report = "src\\Laporan\\klasifikasi.jrxml";
        konek();
        try {

            JasperReport rpt = JasperCompileManager.compileReport(report);
            Map params = new HashMap();
            params.put("klas", cbid.getValue());
            JasperPrint jp = JasperFillManager.fillReport(rpt, params, conn);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            System.err.println(e);
        }

    }


    @FXML
    private void resettext(ActionEvent event) {
        nama.setText("");
        bagian.setText("");
        klasifikasi.setText("");
        tempat.setText("");
        tgl.setText("");
        alamat.setText("");
        agama.setText("");
        noktp.setText("");
        nonpwp.setText("");
        norek.setText("");
        nobpjssehat.setText("");
        nobpjskenaker.setText("");
        nohp.setText("");
        nosepatu.setText("");
        nobaju.setText("");
        nocelana.setText("");
        nohelm.setText("");
        nosarung.setText("");
        foto.setImage(null);
        email.setText("");
        txtsr.setText("");
        txtfck.setText("");
        txtfckk.setText("");
        txtfcit.setText("");
        txtfcn.setText("");
        txtfcbk.setText("");
        txtfcbpk.setText("");
        txtpasfoto.setText("");

    }

    @FXML
    private void radiobtn(ActionEvent event) throws SQLException {
        if (rbdatabagian.isSelected()) {
            ko();
            rbdataklasi.setDisable(true);
            ckkes.setDisable(true);
            cari.setVisible(true);
        } else {
            rbdataklasi.setDisable(false);
            ckkes.setDisable(true);
            cbid.getItems().clear();
            cari.setDisable(true);
        }
        if (rbdataklasi.isSelected()) {
            ko();
            rbdatabagian.setDisable(true);
            ckkes.setDisable(true);
            cari.setVisible(true);
        } else {
            rbdatabagian.setDisable(false);
            ckkes.setDisable(false);
            cari.setDisable(false);
        }

    }

    private void ko() throws SQLException {
        konek();
        try {
            rs = conn.createStatement().executeQuery("select * from bagian");
            while (rs.next()) {
                options.add(rs.getString("nm_bagian"));
                cbid.setItems(options);
            }
        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }
        rs.close();
        conn.close();
    }

    private void kko() throws SQLException {
        konek();
        try {
            rs = conn.createStatement().executeQuery("SELECT `id_bagian` , `nm_klasifikasi` FROM `demi`.`klasifikasi` LIMIT 0, 1000");
            while (rs.next()) {
                options.add(rs.getString("nm_klasifikasi"));
                cbid.setItems(options);
                //namalapo.setText(rs.getString("nm_klasifikasi"));
            }
            rs.close();
            conn.close();
        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }

    }

    private void prinkkes() {
        try {
            if (ckkes.isSelected()) {
                cetakkes();
                //cari.setDisable(true);
            } else {
                //cari.setDisable(false);
            }
            if (rbdatabagian.isSelected()) {
                // cetakbagian();
                bagian();
            } else {

            }
            if (rbdataklasi.isSelected()) {
                klasi();
            } else {

            }
            if (nama.getText().isEmpty() == false) {
                cetak();
            } else {
//                TrayNotification tn = new TrayNotification("GAGAL", "Data Kosong !", NotificationType.WARNING);
//                tn.setAnimationType(AnimationType.POPUP);
//                tn.showAndDismiss(Duration.seconds(2));
            }
        } catch (Exception ex) {

        }
    }

    private void cetakkes() {
        try {
            konek();
            String report = "src\\Laporan\\karyawan.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (SQLException | JRException ex) {

        }

    }

    private void cetakbagian() {
        try {
            konek();
            String report = "src\\Laporan\\Bagian.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (SQLException | JRException ex) {

        }

    }

    private void cetakklasifikasi() {
        try {
            konek();
            String report = "src\\Laporan\\klasifikasi.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);
        } catch (SQLException | JRException ex) {

        }

    }

    @FXML
    private void loadmanakombo(MouseEvent event) throws SQLException {
        loadnama();
    }

    private void loadnama() throws SQLException {
        konek();
        try {
            String sql = "select * from bagian where id_bagian = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(2, (String) cbid.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            if (rs.next()) {
                String namaperue = rs.getString("nm_bagian");
            }

        } catch (Exception ex) {

        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //radiobtn();
    }

}
