package ioo.dto;

import ioo.model.Resultado;
import java.util.Date;

public class PracticaResultadoDTO {
    private int codigoPractica;
    private String nombre;
    private String grupo;
    private int valoresCriticos;
    private boolean valoresReservados; // eliminar?
    private int horaParaResultado;
    private boolean practicaUsada;
    private int idResultado;
    private Date fecha;
    private int valor;

    public PracticaResultadoDTO(int codigoPractica, String nombre, String grupo, int valoresCriticos, boolean valoresReservados, int horaParaResultado, boolean practicaUsada, int idResultado, Date fecha, int valor) {
        this.codigoPractica = codigoPractica;
        this.nombre = nombre;
        this.grupo = grupo;
        this.valoresCriticos = valoresCriticos;
        this.valoresReservados = valoresReservados;
        this.horaParaResultado = horaParaResultado;
        this.practicaUsada = practicaUsada;
        this.idResultado = idResultado;
        this.fecha = fecha;
        this.valor = valor;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = codigoPractica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getValoresCriticos() {
        return valoresCriticos;
    }

    public void setValoresCriticos(int valoresCriticos) {
        this.valoresCriticos = valoresCriticos;
    }

    public boolean isValoresReservados() {
        return valoresReservados;
    }

    public void setValoresReservados(boolean valoresReservados) {
        this.valoresReservados = valoresReservados;
    }

    public int getHoraParaResultado() {
        return horaParaResultado;
    }

    public void setHoraParaResultado(int horaParaResultado) {
        this.horaParaResultado = horaParaResultado;
    }

    public boolean isPracticaUsada() {
        return practicaUsada;
    }

    public void setPracticaUsada(boolean practicaUsada) {
        this.practicaUsada = practicaUsada;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
