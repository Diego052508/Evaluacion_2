package com.example.evaluacion_2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public void showConsulta() {
        HelloApplication.mostrarConsulta();
    }

    @FXML
    public void showRegistro() {
        HelloApplication.mostrarRegistro();
    }

    @FXML
    public void cerrarSesion(ActionEvent event) {
        try {
            MenuItem menuItem = (MenuItem) event.getSource();

            Stage stage = (Stage) menuItem
                    .getParentPopup()
                    .getOwnerWindow();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/evaluacion_2/Login-view.fxml")
            );

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Inicio de Sesión");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void helpVer(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText(null);
        alert.setContentText("Revisa información disponible sobre los clientes.");
        alert.showAndWait();
    }

    @FXML
    public void helpNuevo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText(null);
        alert.setContentText("Agrega nuevos clientes a la lista.");
        alert.showAndWait();
    }
}
