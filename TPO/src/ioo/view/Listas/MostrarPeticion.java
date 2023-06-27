package ioo.view.Listas;

import ioo.controller.Controller;
import ioo.dto.PeticionMVC;
import ioo.dto.PracticaDTO;
import ioo.model.Resultado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

// Referencia: https:www.tutorialspoint.com/swingexamples/create_table.htm

 //        EL USUARIO FILTRA POR UN NUMERO DE PETICION Y SI ES CRITICO EL RESULTADO, DICE POR PANTALLA RETIRAR POR SUCURSAL. SI
 //        NO ES CRITICO, MUESTRA LA INFO DE LA PETICION POR PANTALLA
public class MostrarPeticion extends JFrame{


    private JPanel contentPane;

    private JTextField codigoPeticion;

    public MostrarPeticion() {

        setResizable(false);
        setTitle("Mostrar petición");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbPeticionId = new JLabel("ID Petición:");
        lbPeticionId.setBounds(10, 7, 150, 14);
        contentPane.add(lbPeticionId);

        codigoPeticion = new JTextField();
        codigoPeticion.setBounds(175, 5, 120, 20);
        contentPane.add(codigoPeticion);
        codigoPeticion.setColumns(10);


        JButton btnBuscarPeticion = new JButton("Buscar petición");
        btnBuscarPeticion.setBounds(0, 266, 130, 20);
        contentPane.add(btnBuscarPeticion);

        btnBuscarPeticion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    if (codigoPeticion.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo del código de la petición debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int nro =Integer.parseInt(codigoPeticion.getText());
                        int respuesta = Controller.getControlador().sePuedeMostrarPeticion(nro);
                        switch(respuesta) {
                            case 0:
                                JOptionPane.showMessageDialog(null, "No existe petición", "No existe petición", JOptionPane.WARNING_MESSAGE);
                                break;
                            case 1:
                                PeticionMVC peticion = Controller.getControlador().mostrarPeticion();
                                JOptionPane.showMessageDialog(null, "Obra social "+peticion.getObraSocial(), "Formulario incompleto", JOptionPane.WARNING_MESSAGE);

                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "Retirar por sucursal", "Retirar por sucursal", JOptionPane.INFORMATION_MESSAGE);
                                break;
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo números", "Error caracter ingresado erróneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
    });
    }



 }

