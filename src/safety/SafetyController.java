/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safety;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import geter_seter.safety;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class SafetyController implements Initializable {

    final ObservableList options = FXCollections.observableArrayList();
    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;
    private Statement st;
    private ObservableList<safety> data;
    String query = "";
    @FXML
    private JFXButton btnsimpan;
    @FXML
    private TextField ids;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnhapus;
    
    @FXML
    private TableView<safety> tabsafety;
    @FXML
    private TableColumn<safety, String> kolid;
    @FXML
    private TableColumn<safety, Integer> kolsepatu;
    @FXML
    private TableColumn<safety, Integer> kolcelana;
    @FXML
    private TableColumn<safety, String> kolbaju;
    @FXML
    private TableColumn<safety, String> kolhelm;
    @FXML
    private TableColumn<safety, Integer> kolsarung;
    @FXML
    private JFXTextField cari;
     @FXML
    private JFXTextField namakar;
    @FXML
    private ComboBox id;
    @FXML
    private Spinner sepatu;
    @FXML
    private Spinner celana;
    @FXML
    private Spinner baju;
    @FXML
    private Spinner helm;
    @FXML
    private Spinner sarung;
    final int nilaisarung = 1;
    final int nilaicelana = 1;
    final int nilaisepatu = 1;
    

    SpinnerValueFactory<Integer> v
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 70, nilaisarung);
    SpinnerValueFactory<Integer> vv
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 70, nilaicelana);
    SpinnerValueFactory<Integer> vvv
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 70, nilaisepatu);
    

    ObservableList<String> ubaju = FXCollections.observableArrayList("S", "L", "M", "XL", "XXL");

    SpinnerValueFactory<String> vs = new SpinnerValueFactory.ListSpinnerValueFactory<>(ubaju);
    
    ObservableList<String> uhelm = FXCollections.observableArrayList("S", "L", "M", "XL", "XXL");

    SpinnerValueFactory<String> vis = new SpinnerValueFactory.ListSpinnerValueFactory<>(ubaju);

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

    private void ko() throws SQLException {
        konek();
        options.clear();
        try {
            rs = conn.createStatement().executeQuery("select * from karyawan");
            while (rs.next()) {
                options.add(rs.getString("id_karyawan"));
                id.setItems(options);
            }
        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }
        rs.close();
        conn.close();
    }

    private void spin() {
        sarung.setValueFactory(v);
        celana.setValueFactory(vv);
        sepatu.setValueFactory(vvv);
        baju.setValueFactory(vs);
        helm.setValueFactory(vis);
    }

    @FXML
    private void simpan(ActionEvent event) throws SQLException {
        insert();
    }

    private void insert() throws SQLException {
        konek();
        String insert = "insert into safety (id_safety,no_sepatu,no_baju,no_celana,no_helm,no_sarung,id_karyawan) values(null,?,?,?,?,?,?)";
        try {
            pst = (PreparedStatement) conn.prepareStatement(insert);

            pst.setString(1, sepatu.getValue().toString());
            pst.setString(2, baju.getValue().toString());
            pst.setString(3, celana.getValue().toString());
            pst.setString(4, helm.getValue().toString());
            pst.setString(5, sarung.getValue().toString());
            pst.setString(6, id.getValue().toString());
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

    private void tabelsafety() throws SQLException {
        konek();
        try {
            data = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                int idsaf = set.getInt(1);
                int nosepatu = set.getInt(2);
                String nobaju = set.getString(3);
                int nocelana = set.getInt(4);
                String nohelm = set.getString(5);
                int nosarung = set.getInt(6);
                String idkars = set.getString(7);
                data.add(new safety(idsaf, nosepatu, nobaju, nocelana, nohelm, nosarung, idkars));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolid.setCellValueFactory(new PropertyValueFactory<>("idkars"));
        kolsepatu.setCellValueFactory(new PropertyValueFactory<>("nosepatu"));
        kolcelana.setCellValueFactory(new PropertyValueFactory<>("nocelana"));
        kolbaju.setCellValueFactory(new PropertyValueFactory<>("nobaju"));
        kolhelm.setCellValueFactory(new PropertyValueFactory<>("nohelm"));
        kolsarung.setCellValueFactory(new PropertyValueFactory<>("nosarung"));
        tabsafety.setItems(null);
        tabsafety.setItems(data);
    }

    private void refresh() throws SQLException {
        konek();
        tabsafety.getItems().clear();
        query = "select * from safety";
        tabelsafety();
    }

    private void hapus() {
        id.setValue("");
        namakar.setText("");
        cari.setText("");
        spin();
    }

    @FXML
    private void setdaritabel(MouseEvent event) throws SQLException {
        try {
            safety pr = tabsafety.getItems().get(tabsafety.getSelectionModel().getSelectedIndex());
            id.setValue(pr.getIdkars());
            sepatu.getValueFactory().setValue(pr.getNosepatu());
            celana.getValueFactory().setValue(pr.getNocelana());
            baju.getValueFactory().setValue(pr.getNobaju());
            helm.getValueFactory().setValue(pr.getNohelm());
            sarung.getValueFactory().setValue(pr.getNosarung());
            ids.setText(pr.getIdsaf().toString());
            btnsimpan.setDisable(true);
            btnhapus.setDisable(false);
            btnupdate.setDisable(false);
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    @FXML
    private void segarkan(ActionEvent event) throws SQLException {
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
            alert.setHeaderText("ID Karyawan :" + id.getValue());
            alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
            String sql = "delete from safety where id_safety = ? ";
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1, ids.getText());
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
    private void loadmanakombo(MouseEvent event) throws SQLException {
        try{
            ko();
        }catch(Exception e){
            
        }
    }
     @FXML
    private void loadmanakomboo(ActionEvent event) throws SQLException {
        loadnama();
    }


    private void loadnama() throws SQLException {
        konek();
        try {
            String sql = "select * from karyawan where id_karyawan = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, (String)id.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            if (rs.next()) {
                String namaperue = rs.getString("nm_karyawan");
                namakar.setText(namaperue);
            }
        } catch (Exception ex) {

        }
    }
  
    
     @FXML
    private void update(ActionEvent event) throws SQLException {
        updated();
    }

    private void updated() throws SQLException {
        konek();
        String update = "update safety set no_sepatu=?, no_baju=?, no_celana=?, no_helm=?, no_sarung=?, id_karyawan=? where id_safety=?";
        
        try {
            pst = (PreparedStatement) conn.prepareStatement(update);

            pst.setString(1, sepatu.getValue().toString());
            pst.setString(2, baju.getValue().toString());
            pst.setString(3, celana.getValue().toString());
            pst.setString(4, helm.getValue().toString());
            pst.setString(5, sarung.getValue().toString());
            pst.setString(6, id.getValue().toString());
            pst.setString(7,ids.getText());
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
     @FXML
    private void carisafety(KeyEvent event) throws SQLException {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from safety where id_karyawan LIKE '%" + cari.getText() + "%'";
                   
                   
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(7));
                    data.add(new safety(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7)));

                }
                tabsafety.setItems(data);
            } catch (SQLException ex) {
            }
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
        try {
            // TODO
            konek();
            spin();
            refresh();
        } catch (SQLException ex) {

        }
    }

}
