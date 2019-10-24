/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import geter_seter.karyawan;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author pacevil
 */
public class KaryawanController implements Initializable {

    private ObservableList<karyawan> data;
    final ObservableList options = FXCollections.observableArrayList();
    File file = null;
    private PreparedStatement pst;
    String s;
    private Connection conn;
    private ResultSet rs;
    private Statement st;
    String query = "";
    InputStream fis = null;
    BufferedImage imgt = null;
    @FXML
    private JFXTabPane tabpane;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField nama;
    @FXML
    private JFXTextField tempat;
    @FXML
    private DatePicker tgl;
    @FXML
    private ComboBox jk;
    @FXML
    private ComboBox agama;
    @FXML
    private JFXTextField alamat, namaklas;
    @FXML
    private ComboBox stkar;
    @FXML
    private ComboBox pendidikan;
    @FXML
    private DatePicker tglma;
    @FXML
    private JFXTextField nohp;
    @FXML
    private ComboBox idkla;
    @FXML
    private JFXTextField noktp;
    @FXML
    private JFXTextField norek;
    @FXML
    private JFXTextField nobpjskenaker;
    @FXML
    private JFXTextField nobpjskesehatan;
    @FXML
    private JFXTextField npwp;
    @FXML
    private JFXTextField email;
    @FXML
    private ComboBox surat;
    @FXML
    private ComboBox fotoktp;
    @FXML
    private ComboBox fotokk;
    @FXML
    private ComboBox fotoijasah;
    @FXML
    private ComboBox fotonpwp;
    @FXML
    private ComboBox fotobpjskenaker;
    @FXML
    private ComboBox fotobpjssehat;
    @FXML
    private ComboBox pasfoto;
    @FXML
    private ImageView gambar;
    @FXML
    private JFXTextField filepath;
    @FXML
    private TableView<karyawan> tabkaryawan;
    @FXML
    private TableColumn<karyawan, String> kolid;
    @FXML
    private TableColumn<karyawan, String> kolnama;
    @FXML
    private TableColumn<karyawan, String> koltempat;
    @FXML
    private TableColumn<karyawan, String> koltgllahir;
    @FXML
    private TableColumn<karyawan, String> koljk;
    @FXML
    private TableColumn<karyawan, String> kolagama;
    @FXML
    private TableColumn<karyawan, String> kolalamat;
    @FXML
    private TableColumn<karyawan, String> kolstatus;
    @FXML
    private TableColumn<karyawan, String> kolpendidikan;
    @FXML
    private TableColumn<karyawan, String> kolidkla;
    @FXML
    private TableColumn<karyawan, String> koltglma;
    @FXML
    private TableColumn<karyawan, String> kolnohp;
    @FXML
    private TableColumn<karyawan, String> kolktp;
    @FXML
    private TableColumn<karyawan, String> kolnorek;
    @FXML
    private TableColumn<karyawan, String> kolnobpjskenaker;
    @FXML
    private TableColumn<karyawan, String> kolnobpjssehat;
    @FXML
    private TableColumn<karyawan, String> kolnpwp;
    @FXML
    private TableColumn<karyawan, String> kolemail;
    @FXML
    private TableColumn<karyawan, String> kolsurat;
    @FXML
    private TableColumn<karyawan, String> kolfotoktp;
    @FXML
    private TableColumn<karyawan, String> kolfotokk;
    @FXML
    private TableColumn<karyawan, String> kolfotoijasah;
    @FXML
    private TableColumn<karyawan, String> kolfotonpwp;
    @FXML
    private TableColumn<karyawan, String> kolfotobpjskenaker;
    @FXML
    private TableColumn<karyawan, String> kolfotobpjssehat;
    @FXML
    private TableColumn<karyawan, String> kolpasfoto;
    @FXML
    private Tab tabdata;
    @FXML
    private JFXButton btnsimpan;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXButton btnbrowse;
    @FXML
    private JFXButton btnreset;
    @FXML
    private Tab tabtabel;
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

    public void loadres() {
        jk.getItems().addAll("Laki-Laki", "Perempuan");
        jk.getSelectionModel();
        agama.getItems().addAll("Islam", "Protestan", "Katolik", "Hindu", "Budha");
        agama.getSelectionModel();
        stkar.getItems().addAll("Kontrak", "Tetap");
        stkar.getSelectionModel();
        pendidikan.getItems().addAll("SD", "SMA", "SLTA", "D3", "S1", "Lainnya");
        pendidikan.getSelectionModel();
        surat.getItems().addAll("ADA", "TIDAK ADA");
        surat.getSelectionModel();
        fotoktp.getItems().addAll("ADA", "TIDAK ADA");
        fotoktp.getSelectionModel();
        fotokk.getItems().addAll("ADA", "TIDAK ADA");
        fotokk.getSelectionModel();
        fotoijasah.getItems().addAll("ADA", "TIDAK ADA");
        fotoijasah.getSelectionModel();
        fotonpwp.getItems().addAll("ADA", "TIDAK ADA");
        fotonpwp.getSelectionModel();
        fotobpjskenaker.getItems().addAll("ADA", "TIDAK ADA");
        fotobpjskenaker.getSelectionModel();
        fotobpjssehat.getItems().addAll("ADA", "TIDAK ADA");
        fotobpjssehat.getSelectionModel();
        pasfoto.getItems().addAll("ADA", "TIDAK ADA");
        pasfoto.getSelectionModel();

    }

    private void ko() {
        konek();
        options.clear();
        try {
            rs = conn.createStatement().executeQuery("select * from klasifikasi");
            while (rs.next()) {
                options.add(rs.getString("id_klasifikasi"));
                idkla.setItems(options);
            }
        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void browse(ActionEvent event) {
        try {
            FileChooser fl = new FileChooser();
            FileChooser.ExtensionFilter exfJPG = new FileChooser.ExtensionFilter("JPG File,(*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter exfPNG = new FileChooser.ExtensionFilter("PNG File,(*.png)", "*.PNG");
            fl.getExtensionFilters().addAll(exfJPG, exfPNG);
            file = fl.showOpenDialog(null);
            String ustj = file.getAbsolutePath();
            filepath.setText(ustj);
            s = (ustj);
            FileInputStream is = new FileInputStream(ustj);
            Image image = new Image(is);
            gambar.setImage(image);
        } catch (Exception ex) {
        }
    }

    @FXML
    private void simpan(ActionEvent event) throws SQLException {
        insert();
    }

    private void insert() throws SQLException {
        konek();
        String insert = "insert into karyawan (id_karyawan,nm_karyawan,tmpt_lahir,tgl_lahir,jk,agama,alamat,st_karyawan,pendidikan,tgl_masuk,no_hp,id_klasifikasi,no_ktp,no_rek,no_bpjs_kenaker,no_bpjs_sehat,npwp,email,surat_lamaran,foto_ktp,foto_kk,foto_ijasah,foto_npwp,foto_bpjs_kenaker,foto_bpjs_kesehatan,pas_foto,foto) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            InputStream is = new FileInputStream(new File(s));
            pst = (PreparedStatement) conn.prepareStatement(insert);
            pst.setString(1, id.getText());
            pst.setString(2, nama.getText());
            pst.setString(3, tempat.getText());
            pst.setString(4, tgl.getValue().format(DateTimeFormatter.ISO_DATE));
            pst.setString(5, jk.getValue().toString());
            pst.setString(6, agama.getValue().toString());
            pst.setString(7, alamat.getText());
            pst.setString(8, stkar.getValue().toString());
            pst.setString(9, pendidikan.getValue().toString());
            pst.setString(10, tglma.getValue().format(DateTimeFormatter.ISO_DATE));
            pst.setString(11, nohp.getText());
            pst.setString(12, idkla.getValue().toString());
            pst.setString(13, noktp.getText());
            pst.setString(14, norek.getText());
            pst.setString(15, nobpjskenaker.getText());
            pst.setString(16, nobpjskesehatan.getText());
            pst.setString(17, npwp.getText());
            pst.setString(18, email.getText());
            pst.setString(19, surat.getValue().toString());
            pst.setString(20, fotoktp.getValue().toString());
            pst.setString(21, fotokk.getValue().toString());
            pst.setString(22, fotoijasah.getValue().toString());
            pst.setString(23, fotonpwp.getValue().toString());
            pst.setString(24, fotobpjskenaker.getValue().toString());
            pst.setString(25, fotobpjssehat.getValue().toString());
            pst.setString(26, pasfoto.getValue().toString());
            pst.setString(27, filepath.getText());
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

        } catch (FileNotFoundException | SQLException ex) {
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
        tempat.setText("");
        tgl.getEditor().setText("");
        jk.setValue("");
        agama.setValue("");
        alamat.setText("");
        stkar.setValue("");
        pendidikan.setValue("");
        tglma.getEditor().setText("");
        nohp.setText("");
        idkla.setValue("");
        noktp.setText("");
        norek.setText("");
        nobpjskenaker.setText("");
        nobpjskesehatan.setText("");
        npwp.setText("");
        email.setText("");
        surat.setValue("");
        fotoktp.setValue("");
        fotokk.setValue("");
        fotoijasah.setValue("");
        fotonpwp.setValue("");
        fotobpjskenaker.setValue("");
        fotobpjssehat.setValue("");
        pasfoto.setValue("");
        gambar.setImage(null);
        filepath.setText("");
        namaklas.setText("");
    }

    private void tabelkaryawan() {
        konek();
        try {
            data = FXCollections.observableArrayList();

            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                String idk = set.getString(1);
                String namak = set.getString(2);
                String tempatk = set.getString(3);
                String tglk = set.getString(4);
                String jkk = set.getString(5);
                String agamak = set.getString(6);
                String alamatk = set.getString(7);
                String stkark = set.getString(8);
                String pendidikank = set.getString(9);
                String tglmak = set.getString(10);
                String nohpk = set.getString(11);
                String idklak = set.getString(12);
                String noktpk = set.getString(13);
                String norekk = set.getString(14);
                String nobpjskenakerk = set.getString(15);
                String nobpjskesehatank = set.getString(16);
                String npwpk = set.getString(17);
                String emailk = set.getString(18);
                String suratk = set.getString(19);
                String fotoktpk = set.getString(20);
                String fotokkk = set.getString(21);
                String fotoijasahk = set.getString(22);
                String fotonpwpk = set.getString(23);
                String fotobpjskenakerk = set.getString(24);
                String fotobpjssehatk = set.getString(25);
                String pasfotok = set.getString(26);
                String fotok = set.getString(27);

                data.add(new karyawan(idk, namak, tempatk, tglk, jkk, agamak, alamatk, stkark, pendidikank, tglmak, nohpk, idklak, noktpk, norekk, nobpjskenakerk, nobpjskesehatank, npwpk, emailk, suratk, fotoktpk, fotokkk, fotoijasahk, fotonpwpk, fotobpjskenakerk, fotobpjssehatk, pasfotok, fotok));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        kolid.setCellValueFactory(new PropertyValueFactory<>("idk"));
        kolnama.setCellValueFactory(new PropertyValueFactory<>("namak"));
        koltempat.setCellValueFactory(new PropertyValueFactory<>("tempatk"));
        koltgllahir.setCellValueFactory(new PropertyValueFactory<>("tglk"));
        koljk.setCellValueFactory(new PropertyValueFactory<>("jkk"));
        kolagama.setCellValueFactory(new PropertyValueFactory<>("agamak"));
        kolalamat.setCellValueFactory(new PropertyValueFactory<>("alamatk"));
        kolstatus.setCellValueFactory(new PropertyValueFactory<>("stkark"));
        kolpendidikan.setCellValueFactory(new PropertyValueFactory<>("pendidikank"));
        koltglma.setCellValueFactory(new PropertyValueFactory<>("tglmak"));
        kolnohp.setCellValueFactory(new PropertyValueFactory<>("nohpk"));
        kolidkla.setCellValueFactory(new PropertyValueFactory<>("idklak"));
        kolktp.setCellValueFactory(new PropertyValueFactory<>("noktpk"));
        kolnorek.setCellValueFactory(new PropertyValueFactory<>("norekk"));
        kolnobpjskenaker.setCellValueFactory(new PropertyValueFactory<>("nobpjskenakerk"));
        kolnobpjssehat.setCellValueFactory(new PropertyValueFactory<>("nobpjskesehatank"));
        kolnpwp.setCellValueFactory(new PropertyValueFactory<>("npwpk"));
        kolemail.setCellValueFactory(new PropertyValueFactory<>("emailk"));
        kolsurat.setCellValueFactory(new PropertyValueFactory<>("suratk"));
        kolfotoktp.setCellValueFactory(new PropertyValueFactory<>("fotoktpk"));
        kolfotokk.setCellValueFactory(new PropertyValueFactory<>("fotokkk"));
        kolfotoijasah.setCellValueFactory(new PropertyValueFactory<>("fotoijasahk"));
        kolfotonpwp.setCellValueFactory(new PropertyValueFactory<>("fotonpwpk"));
        kolfotobpjskenaker.setCellValueFactory(new PropertyValueFactory<>("fotobpjskenakerk"));
        kolfotobpjssehat.setCellValueFactory(new PropertyValueFactory<>("fotobpjssehatk"));
        kolpasfoto.setCellValueFactory(new PropertyValueFactory<>("pasfotok"));

        tabkaryawan.setItems(null);
        tabkaryawan.setItems(data);
    }

    private void refresh() {
        konek();
        tabkaryawan.getItems().clear();
        query = "select * from karyawan";
        tabelkaryawan();
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            konek();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("HAPUS");
            alert.setHeaderText("ID Karyawan : " + id.getText());
            alert.setContentText("Apakah anda yakin ingin menghapus data ini ?");
            String sql = "delete from karyawan where id_karyawan = ? ";
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1, id.getText());
                    int i = pst.executeUpdate();
                    if (i == 1) {
                        hapus();
                        refresh();
//                        btnsimpan.setDisable(false);
//                        btnhapus.setDisable(true);
//                        btnupdate.setDisable(true);
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
    private void priviewdata(ActionEvent event) {
        tabdata.selectedProperty();

    }

    @FXML
    private void reset(ActionEvent event) {
        hapus();
    }

    @FXML
    private void refreshresetf(ActionEvent event) {
        refresh();
        hapus();
    }

    @FXML
    private void setdaritabel(MouseEvent event) throws SQLException, FileNotFoundException, IOException {
        try {
            karyawan pr = tabkaryawan.getItems().get(tabkaryawan.getSelectionModel().getSelectedIndex());
            id.setText(pr.getIdk());
            nama.setText(pr.getNamak());
            tempat.setText(pr.getTempatk());
            tgl.getEditor().setText(pr.getTglk());
            jk.setValue(pr.getJkk());
            agama.setValue(pr.getAgamak());
            alamat.setText(pr.getAlamatk());
            stkar.setValue(pr.getStkark());
            pendidikan.setValue(pr.getPendidikank());
            tglma.getEditor().setText(pr.getTglmak());
            nohp.setText(pr.getNohpk());
            idkla.setValue(pr.getIdklak());
            noktp.setText(pr.getNoktpk());
            norek.setText(pr.getNorekk());
            nobpjskenaker.setText(pr.getNobpjskenakerk());
            nobpjskesehatan.setText(pr.getNobpjskesehatank());
            npwp.setText(pr.getNpwpk());
            email.setText(pr.getEmailk());
            surat.setValue(pr.getSuratk());
            fotoktp.setValue(pr.getFotoktpk());
            fotokk.setValue(pr.getFotokkk());
            fotoijasah.setValue(pr.getFotoijasahk());
            fotonpwp.setValue(pr.getFotonpwpk());
            fotobpjskenaker.setValue(pr.getFotobpjskenakerk());
            fotobpjssehat.setValue(pr.getFotobpjssehatk());
            pasfoto.setValue(pr.getPasfotok());
            filepath.setText(pr.getFotok());
            File folo = new File(pr.getFotok());
            Image image = new Image(folo.toURI().toString());
            gambar.setImage(image);

        } catch (Exception e) {

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
            String sql = "select * from klasifikasi where id_klasifikasi = ?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, (String) idkla.getSelectionModel().getSelectedItem());
            rs = pst.executeQuery();
            if (rs.next()) {
                String namaperue = rs.getString("nm_klasifikasi");
                namaklas.setText(namaperue);
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
        String update = "update karyawan set nm_karyawan=?, tmpt_lahir=?, tgl_lahir=?, jk=?, agama=?, alamat=?, st_karyawan=?, pendidikan=?, tgl_masuk=?, no_hp=?, id_klasifikasi=?, no_ktp=?, no_rek=?, no_bpjs_kenaker=?, no_bpjs_sehat=?, npwp=?, email=?, surat_lamaran=?, foto_ktp=?, foto_kk=?, foto_ijasah=?, foto_npwp=?, foto_bpjs_kenaker=?, foto_bpjs_kesehatan=?, pas_foto=?, foto=? where id_karyawan=? ";
        try {
            pst = (PreparedStatement) conn.prepareStatement(update);
            pst.setString(1, nama.getText());
            pst.setString(2, tempat.getText());
            pst.setString(3, tgl.getValue().format(DateTimeFormatter.ISO_DATE));
            pst.setString(4, jk.getValue().toString());
            pst.setString(5, agama.getValue().toString());
            pst.setString(6, alamat.getText());
            pst.setString(7, stkar.getValue().toString());
            pst.setString(8, pendidikan.getValue().toString());
            pst.setString(9, tglma.getValue().format(DateTimeFormatter.ISO_DATE));
            pst.setString(10, nohp.getText());
            pst.setString(11, idkla.getValue().toString());
            pst.setString(12, noktp.getText());
            pst.setString(13, norek.getText());
            pst.setString(14, nobpjskenaker.getText());
            pst.setString(15, nobpjskesehatan.getText());
            pst.setString(16, npwp.getText());
            pst.setString(17, email.getText());
            pst.setString(18, surat.getValue().toString());
            pst.setString(19, fotoktp.getValue().toString());
            pst.setString(20, fotokk.getValue().toString());
            pst.setString(21, fotoijasah.getValue().toString());
            pst.setString(22, fotonpwp.getValue().toString());
            pst.setString(23, fotobpjskenaker.getValue().toString());
            pst.setString(24, fotobpjssehat.getValue().toString());
            pst.setString(25, pasfoto.getValue().toString());
            pst.setString(26, filepath.getText());
            pst.setString(27, id.getText());
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

        } catch (SQLException ex) {
            System.err.println("Error update" + ex);
            TrayNotification tn = new TrayNotification("ERROR", "Periksa Kembali Inputan Anda", NotificationType.ERROR);
            tn.setAnimationType(AnimationType.POPUP);
            tn.showAndDismiss(Duration.seconds(2));

        }
        pst.close();
        conn.close();
    }

    @FXML
    private void carikaryawan(KeyEvent event) {

        if (cari.getText().equals("")) {
            refresh();
        } else {

            data.clear();
            String sql = "select * from karyawan where id_karyawan LIKE '%" + cari.getText() + "%'"
                    + "UNION select * from karyawan where nm_karyawan LIKE '%" + cari.getText() + "%'";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println("" + rs.getString(2));
                    data.add(new karyawan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27)));

                }
                tabkaryawan.setItems(data);
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
        // TODO
        konek();
        refresh();
        loadres();

    }

}
