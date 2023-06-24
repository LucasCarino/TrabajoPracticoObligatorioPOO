package ioo.dto;

import ioo.model.Paciente;
import ioo.model.Practica;
import ioo.model.Resultado;
import ioo.model.Sucursal;

import java.util.Date;
import java.util.List;

public class PeticionDTO {
    private int numeroPeticion;
    private Paciente numeroPaciente;
    private String obraSocial;
    private Date fechaCarga;
    private List<Practica> practicaAsociada;
    private Date fechaEntrega;
    private Sucursal numeroSucursal;

    public PeticionDTO(int numeroPeticion, Paciente numeroPaciente, String obraSocial, Date fechaCarga, List<Practica> practicaAsociada, Date fechaEntrega, Sucursal numeroSucursal) {
        this.numeroPeticion = numeroPeticion;
        this.numeroPaciente = numeroPaciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.practicaAsociada = practicaAsociada;
        this.fechaEntrega = fechaEntrega;
        this.numeroSucursal = numeroSucursal;
    }

    public int getNumeroPeticion() {
        return numeroPeticion;
    }

    public Paciente getNumeroPaciente() {
        return numeroPaciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public List<Practica> getPracticaAsociada() {
        return practicaAsociada;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public Sucursal getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroPeticion(int numeroPeticion) {
        this.numeroPeticion = numeroPeticion;
    }

    public void setNumeroPaciente(Paciente numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public void setPracticaAsociada(List<Practica> practicaAsociada) {
        this.practicaAsociada = practicaAsociada;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setNumeroSucursal(Sucursal numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }
}
