package com.example.evaluacion_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainController {

    @FXML
    public void cerrarSesion(ActionEvent event) {
        // Retorna a tu ventana de inicio de sesión
        cambiarVentana(event, "Login-view.fxml", "Inicio de Sesión");
    }

    @FXML
    public void showRegistro(ActionEvent event) {
        // Abre la ventana de registro
        cambiarVentana(event, "Registro-view.fxml", "Registro de Clientes");
    }

    @FXML
    public void showConsulta(ActionEvent event) {
        // Por ahora imprime en consola. Cuando hagan la vista, descomenta la línea de abajo
        System.out.println("Mostrar ventana consulta");
        // cambiarVentana(event, "Consulta-view.fxml", "Consulta de Clientes");
    }

    @FXML
    public void helpVer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText(null);
        alert.setContentText("Revisa información disponible sobre los clientes.");
        alert.showAndWait();
    }

    @FXML
    public void helpNuevo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText(null);
        alert.setContentText("Agrega nuevos clientes a la lista.");
        alert.showAndWait();
    }

    // Método utilitario para no repetir el código de cambio de pantallas
    private void cambiarVentana(ActionEvent event, String fxml, String titulo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();

            Stage stage;
            // Evaluamos el origen para evitar un NullPointerException al usar los menús superiores
            if (event.getSource() instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) event.getSource();
                stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
            } else {
                javafx.scene.Node source = (javafx.scene.Node) event.getSource();
                stage = (Stage) source.getScene().getWindow();
            }

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}