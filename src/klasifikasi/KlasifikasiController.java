/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasifikasi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import geter_seter.klasifikasi;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class KlasifikasiController implements Initializable {

    final ObservableList options = FXCollections.observableArrayList();
    private ObservableList<klasifikasi> data;
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
    private TableView<klasifikasi> tabkla;
    @FXML
    private TableColumn<klasifikasi, String> kolid;
    @FXML
    private TableColumn<klasifikasi, String> kolnama;
    @FXML
    private TableColumn<klasifikasi, String> kolidbag;
    @FXML
    private JFXTextField cari;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField nama, namabag;
    @FXML
    private ComboBox idbag;

    public void konek() {
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
        String insert = "insert into klasifikasi (id_klasifikasi,id_bagian,nm_klasifikasi) values(?,?,?)";
        try {
            pst = (PreparedStatement) conn.prepareStatement(insert);
            pst.setString(1, id.getText());
            pst.setString(3, nama.getText());
            pst.setString(2, idbag.getValue().toString());
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
        idbag.getSelectionModel().clearSelection();
        idbag.setValue(null);
        namabag.setText("");

    }

    private void ko() {
        konek();
        options.clear();
        try {
            rs = conn.createStatement().executeQuery("select * from bagian");
            while (rs.next()) {
                options.add(rs.getString("id_bagian"));
                idbag.setItems(options);
            }
        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }

    }

    private void tabelkla() {
        konek();
        try {
            data = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String idk = set.getString(1);
                String idbagk = set.getString(2);
                String nmk = set.getString(3);
                data.add(new klasifikasi(idk, idbagk, nmk));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolid.setCellValueFactory(new PropertyValueFactory<>("idk"));
        kolidbag.setCellValueFactory(new PropertyValueFactory<>("idbagk"));
        kolnama.setCellValueFactory(new PropertyValueFactory<>("nmk"));
        tabkla.setItems(null);
        tabkla.setItems(data);
    }

    private void refresh() {
        konek();
        tabkla.getItems().clear();
        query = "select * from klasifikasi";
        tabelkla();
    }

    @FXML
    private void setdaritabel(MouseEvent event) throws SQLException {
        try {
            klasifikasi pr = tabkla.getItems().get(tabkla.getSelectionModel().getSelectedIndex());
            id.setText(pr.getIdk());
            nama.setText(pr.getNmk());
            idbag.setValue(pr.getIdbagk());
            btnsimpan.setDisable(true);
            btnhapus.setDisable(false);
            btnupdate.setDisable(false);
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    private void segarkan(ActionEvent event) {
        refresh();
        hapus();
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
            alert.setHeaderText("ID Klasifikasi :" + id.getText());
            alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
            String sql = "delete from klasifikasi where id_klasifikasi = ? ";
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
    private void cariklasifikasi(KeyEvent event) {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from klasifikasi where id_klasifikasi LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from klasifikasi where nm_klasifikasi LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from klasifikasi where id_bagian LIKE '%" + cari.getText() + "%'";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(2));
                    data.add(new klasifikasi(rs.getString(1), rs.getString(2), rs.getString(3)));

                }
                tabkla.setItems(data);
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
            String sql = "select * from bagian where id_bagian = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, (String) idbag.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            if (rs.next()) {
                String namaperue = rs.getString("nm_bagian");
                namabag.setText(namaperue);
            }
        } catch (Exception ex) {

        }
    }

    @FXML
    private void udate(ActionEvent event) throws SQLException {
        updated();
    }

    private void updated() throws SQLException {
        konek();
        String update = "update klasifikasi set id_bagian=?, nm_klasifikasi=? where id_klasifikasi=?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(update);
            pst.setString(2, nama.getText());
            pst.setString(1, idbag.getValue().toString());
            pst.setString(3, id.getText());
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        konek();
        refresh();
    }

}
