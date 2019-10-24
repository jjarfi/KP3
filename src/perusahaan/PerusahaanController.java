/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perusahaan;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import geter_seter.perusahaan;
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
public class PerusahaanController implements Initializable {

    static int jjID;
    private ObservableList<perusahaan> data;
    String query = "";
    public static int ID;
    String idd;
    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;
    private Statement st;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField nama;
    @FXML
    private JFXTextField direktur;
    @FXML
    private JFXTextField wadir;
    @FXML
    private DatePicker tgl;
    @FXML
    private JFXTextField nomor;
    @FXML
    private JFXButton btnsimpan;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnhapus;
    @FXML
    private TableView<perusahaan> tabperu;
    @FXML
    private TableColumn<perusahaan, String> kolid;
    @FXML
    private TableColumn<perusahaan, String> kolnama;
    @FXML
    private TableColumn<perusahaan, String> koldirektur;
    @FXML
    private TableColumn<perusahaan, String> kolwadir;
    @FXML
    private TableColumn<perusahaan, String> koltgl;
    @FXML
    private TableColumn<perusahaan, String> kolnomor;
    @FXML
    private JFXTextField cari;

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
        String insert = "insert into perusahaan (id_perusahaan,nm_perusahaan,nm_direktur,nm_wadir,tgl_berdiri,nmr_perusahaan) values(?,?,?,?,?,?)";
        try {
            pst = (PreparedStatement) conn.prepareStatement(insert);
            pst.setString(1, id.getText());
            pst.setString(2, nama.getText());
            pst.setString(3, direktur.getText());
            pst.setString(4, wadir.getText());
            pst.setDate(5, java.sql.Date.valueOf(tgl.getValue()));
            pst.setString(6, nomor.getText());
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
        direktur.setText("");
        wadir.setText("");
        nomor.setText("");
        tgl.getEditor().setText("");
    }

    private void tabelperu() {
        konek();
        try {
            data = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String idp = set.getString(1);
                String namap = set.getString(2);
                String direkturp = set.getString(3);
                String wadirp = set.getString(4);
                String tglp = set.getString(5);
                String nomorp = set.getString(6);
                data.add(new perusahaan(idp, namap, direkturp, wadirp, tglp, nomorp));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolid.setCellValueFactory(new PropertyValueFactory<>("idp"));
        kolnama.setCellValueFactory(new PropertyValueFactory<>("namap"));
        koldirektur.setCellValueFactory(new PropertyValueFactory<>("direkturp"));
        kolwadir.setCellValueFactory(new PropertyValueFactory<>("wadirp"));
        koltgl.setCellValueFactory(new PropertyValueFactory<>("tglp"));
        kolnomor.setCellValueFactory(new PropertyValueFactory<>("nomorp"));
        tabperu.setItems(null);
        tabperu.setItems(data);
    }

    private void refresh() {
        konek();
        tabperu.getItems().clear();
        query = "select * from perusahaan";
        tabelperu();
    }

    @FXML
    private void segarkan(ActionEvent event) {
        refresh();
        hapus();
        id.setEditable(true);
        btnsimpan.setDisable(false);
        btnhapus.setDisable(true);
        btnupdate.setDisable(true);
    }

    @FXML
    private void setdaritabel(MouseEvent event) throws SQLException {
        try {
            perusahaan pr = tabperu.getItems().get(tabperu.getSelectionModel().getSelectedIndex());
            id.setText(pr.getIdp());
            nama.setText(pr.getNamap());
            direktur.setText(pr.getDirekturp());
            wadir.setText(pr.getWadirp());
            tgl.getEditor().setText(pr.getTglp());
            nomor.setText(pr.getNomorp());
            id.setEditable(false);
            btnsimpan.setDisable(true);
            btnhapus.setDisable(false);
            btnupdate.setDisable(false);
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            konek();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("HAPUS");
            alert.setHeaderText("ID Perusahaan :" + id.getText());
            alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
            String sql = "delete from perusahaan where id_perusahaan = ? ";
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
    private void cariperusahaan(KeyEvent event) {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from perusahaan where id_perusahaan LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from perusahaan where nm_perusahaan LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from perusahaan where nm_direktur LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from perusahaan where nm_wadir LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from perusahaan where nmr_perusahaan LIKE '%" + cari.getText() + "%'";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(2));
                    data.add(new perusahaan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

                }
                tabperu.setItems(data);
            } catch (SQLException ex) {
            }
        }

    }

    @FXML
    private void update(ActionEvent event) {
        konek();
        String update = "update  perusahaan set nm_perusahaan=?, nm_direktur=? , nm_wadir=?, tgl_berdiri= ?, nmr_perusahaan=? where id_perusahaan=?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(update);
            pst.setString(1, nama.getText());
            pst.setString(2, direktur.getText());
            pst.setString(3, wadir.getText());
            pst.setDate(4, java.sql.Date.valueOf(tgl.getValue()));
            pst.setString(5, nomor.getText());
            pst.setString(6, id.getText());
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
        refresh();
    }

}
