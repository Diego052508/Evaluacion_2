package com.example.evaluacion_2.Controller;

import com.example.evaluacion_2.model.Clientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private CheckBox chkInternet;

    @FXML
    private CheckBox chkSoporte;

    @FXML
    private CheckBox chkSoftware;

    @FXML
    private ImageView imgFotografia;

    private final ToggleGroup grupoSolicitud = new ToggleGroup();
    private String rutaFotografia;

    @FXML
    public void initialize() {
        cmbTipoCliente.getItems().addAll(
                "Persona natural",
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

        rbConsulta.setToggleGroup(grupoSolicitud);
        rbSoporte.setToggleGroup(grupoSolicitud);
        rbServicio.setToggleGroup(grupoSolicitud);

        dpFechaNacimiento.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate fecha, boolean vacio) {
                super.updateItem(fecha, vacio);
                setDisable(!vacio && fecha.isAfter(LocalDate.now()));
            }
        });
    }

    @FXML
    private void seleccionarFotografia(ActionEvent evento) {
        FileChooser selector = new FileChooser();
        selector.setTitle("Seleccionar fotografía");
        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg",
                        "*.gif"
                )
        );

        File archivo = selector.showOpenDialog(obtenerStage());

        if (archivo != null) {
            rutaFotografia = archivo.toURI().toString();
            imgFotografia.setImage(new Image(rutaFotografia));
        }
    }

    @FXML
    private void guardarCliente(ActionEvent evento) {
        if (!validarFormulario()) {
            return;
        }

        String servicios = obtenerServiciosSeleccionados();
        RadioButton solicitudSeleccionada =
                (RadioButton) grupoSolicitud.getSelectedToggle();

        Clientes cliente = new Clientes(
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                cmbTipoCliente.getValue(),
                cmbCiudad.getValue(),
                dpFechaNacimiento.getValue(),
                solicitudSeleccionada.getText(),
                servicios,
                rutaFotografia
        );

        Clientes.getListaClientes().add(cliente);
        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Cliente registrado",
                "El cliente fue guardado correctamente."
        );
        limpiarFormulario(evento);
    }

    @FXML
    private void limpiarFormulario(ActionEvent evento) {
        txtNombres.clear();
        txtApellidos.clear();
        cmbTipoCliente.setValue(null);
        cmbCiudad.setValue(null);
        dpFechaNacimiento.setValue(null);
        grupoSolicitud.selectToggle(null);
        chkInternet.setSelected(false);
        chkSoporte.setSelected(false);
        chkSoftware.setSelected(false);
        imgFotografia.setImage(null);
        rutaFotografia = null;
        txtNombres.requestFocus();
    }

    @FXML
    private void cancelar(ActionEvent evento) {
        obtenerStage().close();
    }

    @FXML
    private void abrirConsulta(ActionEvent evento) {
        HelloApplication.mostrarConsulta();
    }

    private String obtenerServiciosSeleccionados() {
        return Stream.of(chkInternet, chkSoporte, chkSoftware)
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.joining(", "));
    }

    private boolean validarFormulario() {

        if (txtNombres.getText().trim().isEmpty()
                || txtApellidos.getText().trim().isEmpty()) {
            return mostrarError("Debe ingresar nombres y apellidos.");
        }

        if (!txtNombres.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return mostrarError("El nombre solo puede contener letras.");
        }

        if (!txtApellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return mostrarError("El apellido solo puede contener letras.");
        }

        if (cmbTipoCliente.getValue() == null || cmbCiudad.getValue() == null) {
            return mostrarError("Seleccione el tipo de cliente y la ciudad.");
        }

        if (dpFechaNacimiento.getValue() == null
                || dpFechaNacimiento.getValue().isAfter(LocalDate.now())) {
            return mostrarError("Ingrese una fecha de nacimiento válida.");
        }

        if (grupoSolicitud.getSelectedToggle() == null) {
            return mostrarError("Seleccione un tipo de solicitud.");
        }

        if (!chkInternet.isSelected()
                && !chkSoporte.isSelected()
                && !chkSoftware.isSelected()) {
            return mostrarError("Seleccione al menos un servicio de interés.");
        }

        return true;
    }

    private boolean mostrarError(String mensaje) {
        mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", mensaje);
        return false;
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje
    ) {
        Alert alerta = new Alert(tipo, mensaje, ButtonType.OK);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }

    private Stage obtenerStage() {
        return (Stage) txtNombres.getScene().getWindow();
    }
}
