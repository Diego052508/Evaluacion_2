package com.example.evaluacion_2.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource(
                        "/com/example/evaluacion_2/Login-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load(), 600, 400);

        stage.setTitle("Gestión de clientes");
        stage.setScene(scene);
        stage.show();
    }

    public static void mostrarRegistro() {
        mostrarVentana(
                "Registro de clientes",
                "RegistroCliente-view.fxml",
                560,
                650
        );
    }

    public static void mostrarConsulta() {
        mostrarVentana(
                "Consulta de clientes",
                "ConsultaClientes-view.fxml",
                780,
                430
        );
    }

    private static void mostrarVentana(
            String titulo,
            String archivoFxml,
            int ancho,
            int alto
    ) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource(
                            "/com/example/evaluacion_2/" + archivoFxml
                    )
            );

            Stage ventana = new Stage();

            ventana.initOwner(primaryStage);
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(loader.load(), ancho, alto));
            ventana.show();

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "No se pudo abrir la ventana: " + titulo,
                    exception
            );
        }
    }

    public static void mostrarLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    HelloApplication.class.getResource("Login-view.fxml")
            );

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setTitle("Inicio de Sesión");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}