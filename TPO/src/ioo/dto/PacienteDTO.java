package ioo.dto;

public class PacienteDTO {
    private int numeroPaciente;
    private String sexo;
    private int edad;
    private int dni;
    private String nombre;
    private String domicilio;
    private String mail;

    public PacienteDTO(int numeroPaciente, String sexo, int edad, int dni, String nombre, String domicilio, String mail) {
        this.numeroPaciente = numeroPaciente;
        this.sexo = sexo;
        this.edad = edad;
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.mail = mail;
    }

    public int getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(int numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
