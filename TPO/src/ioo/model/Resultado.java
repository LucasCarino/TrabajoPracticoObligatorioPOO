package ioo.model;

import java.util.Date;

public class Resultado {

    private int idResultado;

    private int codigoPractica;
    private Date fecha;
    private int valor;

    public Resultado(int idResultado, int codigoPractica, Date fecha, int valor) {
        this.idResultado = idResultado;
        this.codigoPractica = codigoPractica;
        this.fecha = fecha;
        this.valor = valor;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = codigoPractica;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}


