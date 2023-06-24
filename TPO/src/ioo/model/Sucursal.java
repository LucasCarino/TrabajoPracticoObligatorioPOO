package ioo.model;

import java.util.ArrayList;

public class Sucursal {
    private int nroSucursal;
    private String direccion;
    private String responsableTecnico;
    private ArrayList listaPacientes;

    public Sucursal(int nroSucursal, String direccion, String responsableTecnico, ArrayList listaPacientes) {
        this.nroSucursal = nroSucursal;
        this.direccion = direccion;
        this.responsableTecnico = responsableTecnico;
        this.listaPacientes = listaPacientes;
    }

    public int getNroSucursal() {
        return nroSucursal;
    }

    public void setNroSucursal(int nroSucursal) {
        this.nroSucursal = nroSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(String responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }

    public ArrayList getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(ArrayList listaPacientes) {
        this.listaPacientes = listaPacientes;
    }
}
