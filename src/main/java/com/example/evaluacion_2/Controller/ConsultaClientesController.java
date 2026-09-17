package com.example.evaluacion_2.Controller;

import com.example.evaluacion_2.model.Clientes;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class ConsultaClientesController {

    @FXML
    private TableView<Clientes> tablaClientes;

    @FXML
    private TableColumn<Clientes, String> colNombre;

    @FXML
    private TableColumn<Clientes, String> colTipo;

    @FXML
    private TableColumn<Clientes, String> colCiudad;

    @FXML
    private TableColumn<Clientes, LocalDate> colFecha;

    @FXML
    private TableColumn<Clientes, String> colSolicitud;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(
                celda -> new ReadOnlyStringWrapper(
                        celda.getValue().getNombreCompleto()
                )
        );
        colTipo.setCellValueFactory(
                celda -> new ReadOnlyStringWrapper(
                        celda.getValue().getTipoCliente()
                )
        );
        colCiudad.setCellValueFactory(
                celda -> new ReadOnlyStringWrapper(
                        celda.getValue().getCiudad()
                )
        );
        colFecha.setCellValueFactory(
                celda -> new ReadOnlyObjectWrapper<>(
                        celda.getValue().getFechaNacimiento()
                )
        );
        colSolicitud.setCellValueFactory(
                celda -> new ReadOnlyStringWrapper(
                        celda.getValue().getTipoSolicitud()
                )
        );
        tablaClientes.setItems(Clientes.getListaClientes());
    }

    @FXML
    private void abrirDetalle(MouseEvent evento) {
        if (evento.getClickCount() != 2) {
            return;
        }

        Clientes cliente = tablaClientes
                .getSelectionModel()
                .getSelectedItem();

        if (cliente == null) {
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Detalle del cliente");
        alerta.setHeaderText(cliente.getNombreCompleto());
        alerta.setContentText(
                "Tipo de cliente: " + cliente.getTipoCliente()
                        + "\nCiudad: " + cliente.getCiudad()
                        + "\nFecha de nacimiento: " + cliente.getFechaNacimiento()
                        + "\nSolicitud: " + cliente.getTipoSolicitud()
                        + "\nServicios: " + cliente.getServicios()
        );
        alerta.showAndWait();
    }
}
