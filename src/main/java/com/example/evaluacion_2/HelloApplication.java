package com.example.evaluacion_2;

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RegistroCliente-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 560, 650);
        stage.setTitle("Registro de clientes");
        stage.setScene(scene);
        stage.show();
    }

    public static void mostrarConsulta() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ConsultaClientes-view.fxml"));
            Stage ventana = new Stage();
            ventana.initOwner(primaryStage);
            ventana.setTitle("Consulta de clientes");
            ventana.setScene(new Scene(loader.load(), 780, 430));
            ventana.show();
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo abrir la consulta de clientes", exception);
        }
    }
}
