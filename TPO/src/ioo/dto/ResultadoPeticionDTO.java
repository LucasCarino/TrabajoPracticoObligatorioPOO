package ioo.dto;

import ioo.model.Practica;
import ioo.model.Resultado;

import java.util.List;

public class ResultadoPeticionDTO {
    int retorno;
    List<Practica> practicas;
    List<Resultado> resultados;

    public ResultadoPeticionDTO(int retorno, List<Practica> practicas, List<Resultado> resultados) {
        this.retorno = retorno;
        this.practicas = practicas;
        this.resultados = resultados;
    }

    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }
}
