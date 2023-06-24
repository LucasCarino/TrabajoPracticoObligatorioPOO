package ioo.dto;

public class ClienteDomicilioDTO {
    private String nombreCliente;
    private String apellidoCliente;
    private String dniCliente;
    private String calleDomicilioCliente;
    private String numeroDomicilioCliente;

    public ClienteDomicilioDTO(String nombreCliente, String apellidoCliente, String dniCliente, String calleDomicilioCliente, String numeroDomicilioCliente) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dniCliente = dniCliente;
        this.calleDomicilioCliente = calleDomicilioCliente;
        this.numeroDomicilioCliente = numeroDomicilioCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getCalleDomicilioCliente() {
        return calleDomicilioCliente;
    }

    public void setCalleDomicilioCliente(String calleDomicilioCliente) {
        this.calleDomicilioCliente = calleDomicilioCliente;
    }

    public String getNumeroDomicilioCliente() {
        return numeroDomicilioCliente;
    }

    public void setNumeroDomicilioCliente(String numeroDomicilioCliente) {
        this.numeroDomicilioCliente = numeroDomicilioCliente;
    }
}
