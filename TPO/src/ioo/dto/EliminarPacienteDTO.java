package ioo.dto;

public class EliminarPacienteDTO {
    int numeroPaciente;

    public EliminarPacienteDTO(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public int getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }
}
