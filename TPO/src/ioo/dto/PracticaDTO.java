package ioo.dto;

import ioo.model.Resultado;

public class PracticaDTO {

    private int codigoPractica;
    private String nombre;
    private String grupo;
    private int valoresCriticos;

    private boolean valoresReservados;
    private int horaParaResultado;
    private Resultado resultado;

    public PracticaDTO(int codigoPractica, String nombre, String grupo, int valoresCriticos, boolean valoresReservados, int horaParaResultado, Resultado resultado) {
        this.codigoPractica = codigoPractica;
        this.nombre = nombre;
        this.grupo = grupo;
        this.valoresCriticos = valoresCriticos;
        this.valoresReservados = valoresReservados;
        this.horaParaResultado = horaParaResultado;
        this.resultado = resultado;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public int getValoresCriticos() {
        return valoresCriticos;
    }

    public boolean isValoresReservados() {
        return valoresReservados;
    }

    public int getHoraParaResultado() {
        return horaParaResultado;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = codigoPractica;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setValoresCriticos(int valoresCriticos) {
        this.valoresCriticos = valoresCriticos;
    }

    public void setValoresReservados(boolean valoresReservados) {
        this.valoresReservados = valoresReservados;
    }

    public void setHoraParaResultado(int horaParaResultado) {
        this.horaParaResultado = horaParaResultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
