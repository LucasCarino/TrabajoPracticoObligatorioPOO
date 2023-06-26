package ioo.view;

import ioo.controller.Controller;
import ioo.dto.EliminarPracticaDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPractica extends JFrame{

    private JPanel contentPane;
    private JTextField codigopractica;

    public EliminarPractica() {
        setResizable(false);
        setTitle("Eliminar Práctica");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbPracticaId = new JLabel("Código Práctica:");
        lbPracticaId.setBounds(10, 7, 120, 14);
        contentPane.add(lbPracticaId);

        codigopractica = new JTextField();
        codigopractica.setBounds(124, 5, 50, 20);
        contentPane.add(codigopractica);
        codigopractica.setColumns(10);


        JButton btnEliminarPractica = new JButton("Eliminar Práctica");
        btnEliminarPractica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (codigopractica.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro de práctica debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int codigo_practica = Integer.parseInt(codigopractica.getText());

                        EliminarPracticaDTO practica_eliminada = new EliminarPracticaDTO(codigo_practica);
                        int respuesta = Controller.getControlador().eliminarPractica(practica_eliminada);
                        if (respuesta == 1) {
                            JOptionPane.showMessageDialog(null, "La práctica se ha eliminado correctamente", "Práctica Eliminada!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "La práctica no existe en el sistema", "Práctica no existe", JOptionPane.ERROR_MESSAGE);
                        }
                        codigopractica.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEliminarPractica.setBounds(170, 266, 140, 20);
        contentPane.add(btnEliminarPractica);

        JButton btnNewButton = new JButton("Volver atr\u00E1s");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton.setBounds(0, 266, 130, 20);
        contentPane.add(btnNewButton);
    }
}
