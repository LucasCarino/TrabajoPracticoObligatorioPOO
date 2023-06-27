package ioo.dto;

import ioo.model.Paciente;
import ioo.model.Practica;
import ioo.model.Resultado;
import ioo.model.Sucursal;

import java.util.Date;
import java.util.List;

public class PeticionDTO {
    private int numeroPeticion;
    private int numeroPaciente;
    private String obraSocial;
    private List<Integer> practicaAsociada;
    private int numeroSucursal;

    public PeticionDTO(int numeroPeticion, int numeroPaciente, String obraSocial, List<Integer> practicaAsociada, int numeroSucursal) {
        this.numeroPeticion = numeroPeticion;
        this.numeroPaciente = numeroPaciente;
        this.obraSocial = obraSocial;
        this.practicaAsociada = practicaAsociada;
        this.numeroSucursal = numeroSucursal;
    }

    public int getNumeroPeticion() {
        return numeroPeticion;
    }

    public int getNumeroPaciente() {
        return numeroPaciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public List<Integer> getPracticaAsociada() {
        return practicaAsociada;
    }

    public int getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroPeticion(int numeroPeticion) {
        this.numeroPeticion = numeroPeticion;
    }

    public void setNumeroPaciente(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public void setPracticaAsociada(List<Integer> practicaAsociada) {
        this.practicaAsociada = practicaAsociada;
    }

    public void setNumeroSucursal(int numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }
}
