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
        // Validación de campos vacíos
        if (txtUsuario.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, completa el usuario y la contraseña.");
            return;
        }

        // Si la información es válida, aquí debes programar la apertura de la ventana principal.
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/evaluacion_2/Main-view.fxml"));
            Parent root = fxmlLoader.load();

            // Extraemos el Stage (ventana) actual utilizando el botón de login
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
        // Alert de confirmación antes de cerrar
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle("Confirmar salida");
        alertaConfirmacion.setHeaderText("¿Estás seguro de que deseas cerrar la aplicación?");

        Optional<ButtonType> resultado = alertaConfirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Platform.exit(); // Cierra la aplicación de JavaFX correctamente
        }
    }

    // Método auxiliar para no repetir código al crear Alerts
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}