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
    private List<Practica> practicaAsociada;
    private Sucursal numeroSucursal;

    public PeticionDTO(int numeroPeticion, Paciente numeroPaciente, String obraSocial, List<Practica> practicaAsociada, Sucursal numeroSucursal) {
        this.numeroPeticion = numeroPeticion;
        this.numeroPaciente = numeroPaciente;
        this.obraSocial = obraSocial;
        this.practicaAsociada = practicaAsociada;
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

    public List<Practica> getPracticaAsociada() {
        return practicaAsociada;
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

    public void setPracticaAsociada(List<Practica> practicaAsociada) {
        this.practicaAsociada = practicaAsociada;
    }

    public void setNumeroSucursal(Sucursal numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }
}
