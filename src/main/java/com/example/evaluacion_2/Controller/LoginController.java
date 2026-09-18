package com.example.evaluacion_2.Controller;

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

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnIniciarSesion;

    // Credenciales del usuario administrador
    private final String USUARIO_ADMIN = "admin";
    private final String PASSWORD_ADMIN = "1234";

    @FXML
    private void iniciarSesion(ActionEvent event) {

        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText();

        // Validar que los campos no estén vacíos
        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Datos incompletos",
                    "Por favor, completa el usuario y la contraseña."
            );
            return;
        }

        // Validar usuario y contraseña
        if (!usuario.equals(USUARIO_ADMIN) || !password.equals(PASSWORD_ADMIN)) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Acceso denegado",
                    "Usuario o contraseña incorrectos."
            );
            return;
        }

        // Si las credenciales son correctas, abrir el menú principal
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/evaluacion_2/Main-view.fxml"
                    )
            );

            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");

        } catch (Exception e) {
            e.printStackTrace();

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error interno",
                    "Fallo al cargar la interfaz principal."
            );
        }
    }

    @FXML
    private void salir(ActionEvent event) {

        Alert alertaConfirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);

        alertaConfirmacion.setTitle("Confirmar salida");
        alertaConfirmacion.setHeaderText(
                "¿Estás seguro de que deseas cerrar la aplicación?"
        );

        Optional<ButtonType> resultado =
                alertaConfirmacion.showAndWait();

        if (resultado.isPresent() &&
                resultado.get() == ButtonType.OK) {

            Platform.exit();
        }
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}