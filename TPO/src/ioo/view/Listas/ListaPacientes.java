package ioo.view.Listas;

import ioo.controller.Controller;
import javax.swing.*;
import java.util.Vector;

public class ListaPacientes extends JFrame{
    private JList lista_de_pacientes;
    private JPanel Panel;

    public ListaPacientes(){
        super("ListaPacientes");

        setTitle("Lista de Pacientes");

        System.out.println("ListaPacientes");
        Vector<String> pacientes = Controller.getControlador().getPacientes();
        lista_de_pacientes = pacientes != null ? new JList(pacientes) : new JList();
        setBounds(100, 100, 442, 327);
        setContentPane(Panel);
        setContentPane(lista_de_pacientes);
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
