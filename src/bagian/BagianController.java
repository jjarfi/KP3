/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bagian;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import geter_seter.bagian;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author pacevil
 */
public class BagianController implements Initializable {

    final ObservableList options = FXCollections.observableArrayList();
    private ObservableList<bagian> data;
    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;
    private Statement st;
    String query = "";
    @FXML
    private JFXButton btnsimpan;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnhapus;
    @FXML
    private TableView<bagian> tabbagian;
    @FXML
    private TableColumn<bagian, String> kolid;
    @FXML
    private TableColumn<bagian, String> kolnama;
    @FXML
    private TableColumn<bagian, String> koltgl;
    @FXML
    private TableColumn<bagian, String> kolidper;
    @FXML
    private JFXTextField cari;

    @FXML
    private JFXTextField namaperu;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField nama;
    @FXML
    private DatePicker tgl;
    @FXML
    private ComboBox idper;

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

    @FXML
    private void simpan(ActionEvent event) throws SQLException {
        insert();
    }

    private void insert() throws SQLException {
        konek();
        String insert = "insert into bagian (id_bagian,	id_perusahaan,tgl_ijin,nm_bagian) values(?,?,?,?)";
        try {
            pst = (PreparedStatement) conn.prepareStatement(insert);
            pst.setString(1, id.getText());
            pst.setString(4, nama.getText());
            pst.setString(3, tgl.getValue().format(DateTimeFormatter.ISO_DATE));
            pst.setString(2, idper.getValue().toString());
            int success = pst.executeUpdate();
            if (success == 1) {
                TrayNotification tn = new TrayNotification("SUKSES", "Data Berhasil Terdaftar", NotificationType.SUCCESS);
                tn.setAnimationType(AnimationType.POPUP);
                tn.showAndDismiss(Duration.seconds(2));
                hapus();
                refresh();

            } else {
                TrayNotification tn = new TrayNotification("GAGAL", "Pastikan Data Yang Anda Masukan Lengkap", NotificationType.WARNING);
                tn.setAnimationType(AnimationType.POPUP);
                tn.showAndDismiss(Duration.seconds(2));
            }

        } catch (Exception ex) {
            System.err.println("Error inserting" + ex);
            TrayNotification tn = new TrayNotification("ERROR", "Periksa Kembali Inputan Anda", NotificationType.ERROR);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));
        }
        pst.close();
        conn.close();
    }

    private void hapus() {
        id.setText("");
        nama.setText("");
        idper.getSelectionModel().clearSelection();
        idper.setValue(null);
        tgl.getEditor().setText("");
        namaperu.setText("");
    }

    private void ko() throws SQLException {
        konek();
        options.clear();
        try {
            rs = conn.createStatement().executeQuery("select * from perusahaan");
            while (rs.next()) {
                options.add(rs.getString("id_perusahaan"));
                idper.setItems(options);

            }

        } catch (Exception ex) {

            System.err.println("Error" + ex);
        }

    }

    private void klear() {
        idper.setItems(null);
    }

    private void tabelbag() throws SQLException {
        konek();
        try {
            data = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String idb = set.getString(1);
                String idperb = set.getString(2);
                String tglb = set.getString(3);
                String nmb = set.getString(4);
                data.add(new bagian(idb, idperb, tglb, nmb));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolid.setCellValueFactory(new PropertyValueFactory<>("idb"));
        kolidper.setCellValueFactory(new PropertyValueFactory<>("idperb"));
        koltgl.setCellValueFactory(new PropertyValueFactory<>("tglb"));
        kolnama.setCellValueFactory(new PropertyValueFactory<>("nmb"));
        tabbagian.setItems(null);
        tabbagian.setItems(data);
    }

    private void refresh() throws SQLException {
        konek();
        tabbagian.getItems().clear();
        query = "select * from bagian";
        tabelbag();
    }

    @FXML
    private void setdaritabel(MouseEvent event) throws SQLException {
        //konek();
        try {
            // String sql = "select * from perusahaan where nm_perusahaan = ?";
            bagian pr = tabbagian.getItems().get(tabbagian.getSelectionModel().getSelectedIndex());
            id.setText(pr.getIdb());
            nama.setText(pr.getNmb());
            idper.setValue(pr.getIdperb());
            tgl.getEditor().setText(pr.getTglb());
            // namaperu.setText(rs.getString(sql));
            btnsimpan.setDisable(true);
            btnhapus.setDisable(false);
            btnupdate.setDisable(false);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        // rs.close();

    }

    @FXML
    private void segarkan(ActionEvent event) throws SQLException {
        refresh();
        hapus();
        // idper.setItems(null);
        // ko();

        btnsimpan.setDisable(false);
        btnhapus.setDisable(true);
        btnupdate.setDisable(true);
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            konek();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("HAPUS");
            alert.setHeaderText("ID Bagian :" + id.getText());
            alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
            String sql = "delete from bagian where id_bagian = ? ";
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1, id.getText());
                    int i = pst.executeUpdate();
                    if (i == 1) {
                        hapus();
                        refresh();
                        btnsimpan.setDisable(false);
                        btnhapus.setDisable(true);
                        btnupdate.setDisable(true);
                        TrayNotification tn = new TrayNotification("SUKSES", "Data Berhasil Terhapus", NotificationType.SUCCESS);
                        tn.setAnimationType(AnimationType.POPUP);
                        tn.showAndDismiss(Duration.seconds(2));
                    } else {
                        TrayNotification tn = new TrayNotification("GAGAL", "Tidak ada data yang terhapus", NotificationType.WARNING);
                        tn.setAnimationType(AnimationType.POPUP);
                        tn.showAndDismiss(Duration.seconds(2));

                    }
                } catch (Exception ex) {

                }
            } else {

            }
            pst.close();
            conn.close();
        } catch (SQLException ex) {

        }
    }

    @FXML
    private void caribagian(KeyEvent event) throws SQLException {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from bagian where id_bagian LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from bagian where nm_bagian LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from bagian where id_perusahaan LIKE '%" + cari.getText() + "%'";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(2));
                    data.add(new bagian(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

                }
                tabbagian.setItems(data);
            } catch (SQLException ex) {
            }
        }

    }

    @FXML
    private void loadmanakombo(MouseEvent event) throws SQLException {
        try {
            ko();

        } catch (Exception e) {
        }
    }

    @FXML
    private void loadmanakomboo(ActionEvent event) throws SQLException {
        loadnama();
    }

    private void loadnama() throws SQLException {
        konek();
        try {
            String sql = "select * from perusahaan where id_perusahaan = ?";
            //rs = conn.createStatement().executeQuery("select * from perusahaan where id_perusahaan = ?");
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, (String) idper.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            while (rs.next()) {

                String namaperue = rs.getString("nm_perusahaan");
                namaperu.setText(namaperue);
                // ko();

            }
            //options.clear();

        } catch (Exception ex) {

        }

    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        updated();
    }

    private void updated() throws SQLException {
        konek();
        String update = "update bagian set id_perusahaan=?, tgl_ijin=?, nm_bagian=? where id_bagian=?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(update);
            pst.setString(3, nama.getText());
            pst.setString(2, tgl.getValue().format(DateTimeFormatter.ISO_DATE));
            pst.setString(1, idper.getValue().toString());
            pst.setString(4, id.getText());
            int success = pst.executeUpdate();
            if (success == 1) {
                TrayNotification tn = new TrayNotification("SUKSES", "Data Berhasil Terupdate", NotificationType.SUCCESS);
                tn.setAnimationType(AnimationType.POPUP);
                tn.showAndDismiss(Duration.seconds(2));
                hapus();
                refresh();

            } else {
                TrayNotification tn = new TrayNotification("GAGAL", "Pastikan Data Yang Anda Masukan Lengkap", NotificationType.WARNING);
                tn.setAnimationType(AnimationType.POPUP);
                tn.showAndDismiss(Duration.seconds(2));
            }

        } catch (Exception ex) {
            System.err.println("Error inserting" + ex);
            TrayNotification tn = new TrayNotification("ERROR", "Periksa Kembali Inputan Anda", NotificationType.ERROR);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));
        }
        pst.close();
        conn.close();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            konek();
            refresh();
            options.clear();
        } catch (SQLException ex) {

        }

    }

}
