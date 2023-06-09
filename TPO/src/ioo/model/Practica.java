package ioo.model;

public class Practica {
    @Override
    public String toString() {
        return "Practica [codigoPractica=" + codigoPractica + ", nombre=" + nombre + ", grupo=" + grupo
                + ", valorCritico=" + valorCritico + ", valoresReservados=" + valoresReservados
                + ", horaParaResultado=" + horaParaResultado +  "]";
    }

    private int codigoPractica;

    private int CU;
    private String nombre;
    private String grupo;
    private int valorCritico;
    private boolean valoresReservados;
    private int horaParaResultado;

    public Practica(int codigoPractica, int CU, String nombre, String grupo, int valorCritico, boolean valoresReservados, int horaParaResultado) {
        this.codigoPractica = codigoPractica;
        this.CU = CU;
        this.nombre = nombre;
        this.grupo = grupo;
        this.valorCritico = valorCritico;
        this.valoresReservados = valoresReservados;
        this.horaParaResultado = horaParaResultado;
    }

    public int getCodigoPractica() {
        return codigoPractica;
    }

    public void setCodigoPractica(int codigoPractica) {
        this.codigoPractica = codigoPractica;
    }

    public int getCU() {
        return CU;
    }

    public void setCU(int CU) {
        this.CU = CU;
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

    public int getValorCritico() {
        return valorCritico;
    }

    public void setValorCritico(int valorCritico) {
        this.valorCritico = valorCritico;
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
}
