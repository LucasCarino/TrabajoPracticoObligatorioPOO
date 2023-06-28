package ioo.dto;

import java.util.List;

public class PeticionMVC {

    private String numeroPeticion;
    private String numeroPaciente;

    private String nombrePaciente;
    private String obraSocial;
    private List<String> practicaAsociada;
    private List<String> GrupodePracticas;
    private String numeroSucursal;
    private List<String> resultado;

    public PeticionMVC(String numeroPeticion, String numeroPaciente, String nombrePaciente, String obraSocial, List<String> practicaAsociada, List<String> grupodePracticas, String numeroSucursal, List<String> resultado) {
        this.numeroPeticion = numeroPeticion;
        this.numeroPaciente = numeroPaciente;
        this.nombrePaciente = nombrePaciente;
        this.obraSocial = obraSocial;
        this.practicaAsociada = practicaAsociada;
        GrupodePracticas = grupodePracticas;
        this.numeroSucursal = numeroSucursal;
        this.resultado = resultado;
    }

    public String getNumeroPeticion() {
        return numeroPeticion;
    }

    public void setNumeroPeticion(String numeroPeticion) {
        this.numeroPeticion = numeroPeticion;
    }

    public String getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(String numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
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

    public String getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(String numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public List<String> getResultado() {
        return resultado;
    }

    public void setResultado(List<String> resultado) {
        this.resultado = resultado;
    }
}
