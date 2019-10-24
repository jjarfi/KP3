/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pacevil
 */
public class AdminController implements Initializable {

    private PreparedStatement pst;
    private Connection conn;
    private ResultSet rs;
    private Statement st;

    public static AnchorPane rootp;
    @FXML
    private AnchorPane root, jendela;
    private double initX;
    private double initY;
    AnchorPane perusahaan, bagian, klasifikasi, karyawan, safety, dashboard;

    @FXML
    private void drag(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(event.getScreenX() - initX);
        stage.setY(event.getScreenY() - initY);
    }

    @FXML
    private void pres(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        initX = event.getScreenX() - stage.getX();
        initY = event.getScreenY() - stage.getY();
    }

    @FXML
    private void sembunyi(MouseEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
    }

    private void setNode(Node node) {
        jendela.getChildren().setAll();
        jendela.getChildren().add(node);
//        FadeTransition ft = new FadeTransition(Duration.millis(1500));
//        ft.setNode(node);
//        ft.setFromValue(0.1);
//        ft.setToValue(1);
//        ft.setCycleCount(1);
//        ft.setAutoReverse(false);
//        ft.play();
    }

    @FXML
    private void klikperusahaan(ActionEvent event) {
        setNode(perusahaan);
          konek();
    }

    @FXML
    private void klikbagian(ActionEvent event) {
        setNode(bagian);
          konek();
    }

    @FXML
    private void klikklasifikasi(ActionEvent event) {
        setNode(klasifikasi);
          konek();
    }

    @FXML
    private void klikkaryawan(ActionEvent event) {
        setNode(karyawan);
          konek();
    }

    @FXML
    private void kliksafety(ActionEvent event) {
        setNode(safety);
          konek();
    }

    @FXML
    private void klikdashboard(ActionEvent event) {
        setNode(dashboard);
    }

    @FXML
    private void tutup(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("KELUAR");
        alert.setContentText("Yakin Ingin Keluar ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {

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
        try {
          
            perusahaan = FXMLLoader.load(getClass().getResource("/perusahaan/perusahaan.fxml"));
            bagian = FXMLLoader.load(getClass().getResource("/bagian/bagian.fxml"));
            klasifikasi = FXMLLoader.load(getClass().getResource("/klasifikasi/klasifikasi.fxml"));
            karyawan = FXMLLoader.load(getClass().getResource("/karyawan/karyawan.fxml"));
            safety = FXMLLoader.load(getClass().getResource("/safety/safety.fxml"));
            dashboard = FXMLLoader.load(getClass().getResource("/dashboard/dashboard.fxml"));

        } catch (IOException ex) {

        }
        setNode(dashboard);

    }

    private void konek() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demi", "root", "admin");
            pst = (PreparedStatement) conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Exception" + e.getMessage());
        }
        try {
            conn.close();
            pst.close();
        } catch (SQLException ex) {

        }

    }

}
