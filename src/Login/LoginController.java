/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.TRANSPARENT;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author pacevil
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane root, rooot;
    @FXML
    private AnchorPane anroot;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;
    private double initX;
    private double initY;

    @FXML
    private void kliklogin(ActionEvent event) {

        try {
            if (user.getText().equals("admin") && pass.getText().equals("admin")) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent roort = FXMLLoader.load(getClass().getResource("/Admin/admin.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(roort);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            } else {
                TrayNotification tn = new TrayNotification("PERINGATAN", "Periksa Kembali Username & Password Anda !", NotificationType.NOTICE);
                tn.setAnimationType(AnimationType.POPUP);
                tn.showAndDismiss(Duration.seconds(1));
            }           
        } catch (IOException ex) {

        }

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

    @FXML
    private void drag(MouseEvent event) {
        Stage stage = (Stage) rooot.getScene().getWindow();
        stage.setX(event.getScreenX() - initX);
        stage.setY(event.getScreenY() - initY);
    }

    @FXML
    private void pres(MouseEvent event) {
        Stage stage = (Stage) rooot.getScene().getWindow();
        initX = event.getScreenX() - stage.getX();
        initY = event.getScreenY() - stage.getY();
    }

    @FXML
    private void sembunyi(MouseEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setIconified(true);
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
    }

}
