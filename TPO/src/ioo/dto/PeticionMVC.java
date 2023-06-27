package ioo.dto;

import ioo.model.Sucursal;

import java.util.Date;
import java.util.List;

public class PeticionMVC {

    private int numeroPeticion;
    private int numeroPaciente;
    private String obraSocial;
    private List<String> practicaAsociada;
    private List<String> GrupodePracticas;
    private Sucursal numeroSucursal;
    private List<Integer> resultado;

    public PeticionMVC(int numeroPeticion, int numeroPaciente, String obraSocial, List<String> practicaAsociada, List<String> grupodePracticas, Sucursal numeroSucursal, List<Integer> resultado) {
        this.numeroPeticion = numeroPeticion;
        this.numeroPaciente = numeroPaciente;
        this.obraSocial = obraSocial;
        this.practicaAsociada = practicaAsociada;
        this.GrupodePracticas = grupodePracticas;
        this.numeroSucursal = numeroSucursal;
        this.resultado = resultado;
    }

    public int getNumeroPeticion() {
        return numeroPeticion;
    }

    public void setNumeroPeticion(int numeroPeticion) {
        this.numeroPeticion = numeroPeticion;
    }

    public int getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public List<String> getPracticaAsociada() {
        return practicaAsociada;
    }

    public void setPracticaAsociada(List<String> practicaAsociada) {
        this.practicaAsociada = practicaAsociada;
    }

    public List<String> getGrupodePracticas() {
        return GrupodePracticas;
    }

    public void setGrupodePracticas(List<String> grupodePracticas) {
        GrupodePracticas = grupodePracticas;
    }

    public Sucursal getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Sucursal numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public List<Integer> getResultado() {
        return resultado;
    }

    public void setResultado(List<Integer> resultado) {
        this.resultado = resultado;
    }


}
