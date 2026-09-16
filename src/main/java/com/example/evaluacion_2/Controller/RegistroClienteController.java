package com.example.evaluacion_2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.evaluacion_2.model.Clientes

import java.io.File;
import java.time.LocalDate;

public class RegistroClienteController {
    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private ComboBox<String> cmbCiudad;

    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private RadioButton rbConsulta;

    @FXML
    private RadioButton rbSoporte;

    @FXML
    private RadioButton rbServicio;

    @FXML
    private ToggleGroup grupoSolicitud;

    @FXML
    private CheckBox chkInternet;

    @FXML
    private CheckBox chkSoporte;

    @FXML
    private CheckBox chkSoftware;

    @FXML
    private ImageView imgFotografia;

    private String rutaFotografia = "";

    @FXML
    public void initialize() {

        cmbTipoCliente.getItems().addAll(
                "Persona Natural",
                "Empresa",
                "Institución"
        );

        cmbCiudad.getItems().addAll(
                "Managua",
                "León",
                "Masaya",
                "Granada",
                "Matagalpa",
                "Chinandega"
        );

        grupoSolicitud = new ToggleGroup();

        rbConsulta.setToggleGroup(grupoSolicitud);
        rbSoporte.setToggleGroup(grupoSolicitud);
        rbServicio.setToggleGroup(grupoSolicitud);


        txtNombres.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    txtApellidos.requestFocus();
                    break;

                case ESCAPE:
                    break;
            }
        });

    }
    @FXML
    private void seleccionarFotografia(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar fotografía");

        FileChooser.ExtensionFilter filtro =
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                );

        fileChooser.getExtensionFilters().add(filtro);

        Stage stage = (Stage) imgFotografia.getScene().getWindow();

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {

            rutaFotografia = archivo.toURI().toString();

            Image imagen = new Image(rutaFotografia);

            imgFotografia.setImage(imagen);
        }
    }
    @FXML
    private void seleccionarCarpeta(ActionEvent event) {

        DirectoryChooser directoryChooser =
                new DirectoryChooser();

        directoryChooser.setTitle("Seleccionar carpeta");

        Stage stage =
                (Stage) imgFotografia.getScene().getWindow();

        File carpeta =
                directoryChooser.showDialog(stage);

        if (carpeta != null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Carpeta seleccionada");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Carpeta seleccionada:\n" +
                            carpeta.getAbsolutePath()
            );

            alert.showAndWait();
        }
    }

    @FXML
    private void guardarCliente(ActionEvent event) {

        if (!validarFormulario()) {
            return;
        }

        String nombres =
                txtNombres.getText().trim();

        String apellidos =
                txtApellidos.getText().trim();

        String tipoCliente =
                cmbTipoCliente.getValue();

        String ciudad =
                cmbCiudad.getValue();

        LocalDate fecha =
                dpFechaNacimiento.getValue();

        RadioButton radioSeleccionado =
                (RadioButton) grupoSolicitud.getSelectedToggle();

        String tipoSolicitud =
                radioSeleccionado.getText();


        Clientes cliente = new Clientes(
                nombres,
                apellidos,
                tipoCliente,
                ciudad,
                fecha,
                tipoSolicitud,
                servicios,
                rutaFotografia
        );


        // Alert de información
        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Cliente registrado");
        alert.setHeaderText("Registro exitoso");
        alert.setContentText(
                "El cliente " +
                        cliente.getNombres() +
                        " ha sido registrado correctamente."
        );

        alert.showAndWait();

        limpiarFormulario();
    }



}
