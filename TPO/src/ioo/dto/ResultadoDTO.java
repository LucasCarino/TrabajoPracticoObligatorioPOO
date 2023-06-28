package ioo.dto;

import java.util.Date;

public class ResultadoDTO {
    private int codigoPractica;
    private int valor;

    public ResultadoDTO(int codigoPractica, int valor) {
        this.codigoPractica = codigoPractica;
        this.valor = valor;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = codigoPractica;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}


