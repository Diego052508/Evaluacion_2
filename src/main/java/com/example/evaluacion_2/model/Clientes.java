package com.example.evaluacion_2.model;

import java.time.LocalDate;

public class Clientes {
    private String nombres;
    private String apellidos;
    private String tipoCliente;
    private String ciudad;
    private String fechaNacimiento;
    private String tipoSolicitud ;
    private String servicios ;
    private String rutaFotografia;

    public Clientes(String nombres, String rutaFotografia, String servicios, String tipoSolicitud, String fechaNacimiento, String ciudad, String tipoCliente, String apellidos) {
        this.nombres = nombres;
        this.rutaFotografia = rutaFotografia;
        this.servicios = servicios;
        this.tipoSolicitud = tipoSolicitud;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.tipoCliente = tipoCliente;
        this.apellidos = apellidos;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getRutaFotografia() {
        return rutaFotografia;
    }

    public void setRutaFotografia(String rutaFotografia) {
        this.rutaFotografia = rutaFotografia;
    }
}
