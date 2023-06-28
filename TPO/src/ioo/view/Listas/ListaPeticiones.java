package ioo.view.Listas;

import ioo.controller.Controller;
import javax.swing.*;
import java.util.Vector;

public class ListaPeticiones extends JFrame{
    private JList lista_de_peticiones;
    private JPanel Panel = new JPanel();

    public ListaPeticiones(){
        super("ListaPeticiones");

        setTitle("Lista de Peticiones");

        System.out.println("ListaPeticiones");
        Vector<String> peticiones = Controller.getControlador().getPeticionesConResultadoCritico();
        lista_de_peticiones = peticiones != null ? new JList(peticiones) : new JList();
        setBounds(100, 100, 442, 327);
        setContentPane(Panel);
        setContentPane(lista_de_peticiones);
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
