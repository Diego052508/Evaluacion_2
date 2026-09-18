package com.example.evaluacion_2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnIniciarSesion;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        if (txtUsuario.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, completa el usuario y la contraseña.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/evaluacion_2/Main-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error interno", "Fallo al cargar la interfaz principal.");
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle("Confirmar salida");
        alertaConfirmacion.setHeaderText("¿Estás seguro de que deseas cerrar la aplicación?");

        Optional<ButtonType> resultado = alertaConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}