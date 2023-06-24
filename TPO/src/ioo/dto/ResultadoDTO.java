package ioo.dto;

import java.util.Date;

public class ResultadoDTO {
    private int idResultado;
    private Date fecha;
    private int valor;

    public ResultadoDTO(int idResultado, Date fecha, int valor) {
        this.idResultado = idResultado;
        this.fecha = fecha;
        this.valor = valor;
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


