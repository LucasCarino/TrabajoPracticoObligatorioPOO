package ioo.model;

import java.util.Date;
import java.util.List;

public class Peticion {

    private int numeroPeticion;

    private Paciente numeroPaciente;
    private String obraSocial;
    private Date fechaCarga;
    private List<Practica> practicasAsociadas;
    private Date fechaEntrega;
    private Sucursal numeroSucursal;

    public Peticion(int numeroPeticion, Paciente numeroPaciente, String obraSocial, Date fechaCarga, List<Practica> practicasAsociadas, Date fechaEntrega, Sucursal numeroSucursal) {
        this.numeroPeticion = numeroPeticion;
        this.numeroPaciente = numeroPaciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.practicasAsociadas = practicasAsociadas;
        this.fechaEntrega = fechaEntrega;
        this.numeroSucursal = numeroSucursal;
    }

    public int getNumeroPeticion() {
        return numeroPeticion;
    }

    public void setNumeroPeticion(int numeroPeticion) {
        this.numeroPeticion = numeroPeticion;
    }

    public Paciente getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(Paciente numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public List<Practica> getPracticasAsociadas() {
        return practicasAsociadas;
    }

    public void setPracticasAsociadas(List<Practica> practicasAsociadas) {
        this.practicasAsociadas = practicasAsociadas;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Sucursal getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Sucursal numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public void buscarPeticion(Paciente numeroPaciente) {
        // Con el numero de paciente trae la lista de peticiones asociadas a ese paciente
    }

    public void peticionesDeSucursal(Sucursal numeroSucursal) {
        // Con el numero de sucursal me trae una lista con todas las peticiones de esa sucursal
    }

    public void getCantidadResultados() {
        // Devuelve la cantidad de resultados
    }

    public void getValoresReservados() {
        // retorna lista de valores reservados
    }

    public void buscarPeticionesValoresCriticos() {
        // Retorna una lista con peticiones que tengan valores criticos
    }
}
