package com.example.evaluacion_2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MainController {

    @FXML
    public void showConsulta(){
        System.out.println("Mostrar ventana consulta");
    }

    @FXML
    public void showRegistro(){
        System.out.println("Mostrar ventana registro");
    }

    @FXML
    public void cerrarSesion(){
        System.out.println("Cerrar sesion");
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
