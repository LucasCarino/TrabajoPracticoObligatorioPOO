package ioo.view;
import ioo.controller.Controller;
import ioo.dto.EliminarPeticionDTO;
import ioo.dto.PeticionDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    public class EliminarPeticion extends JFrame {
        private JPanel contentPane;
        private JTextField nroPeticion;

        public EliminarPeticion() {
            setResizable(false);
            setTitle("Eliminar Peticion");

            setBounds(100, 100, 442, 327);
            contentPane = new JPanel();
            contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            setLocationRelativeTo(null);

            JLabel lbPeticionId = new JLabel("Código Petición:");
            lbPeticionId.setBounds(10, 7, 120, 14);
            contentPane.add(lbPeticionId);

            nroPeticion = new JTextField();
            nroPeticion.setBounds(124, 5, 50, 20);
            contentPane.add(nroPeticion);
            nroPeticion.setColumns(10);


            JButton btnEliminarPeticion = new JButton("Eliminar Peticion");
            btnEliminarPeticion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (nroPeticion.getText().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "el campo de nro del Peticion debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                        } else {
                            int nro_peticion = Integer.parseInt(nroPeticion.getText());

                            EliminarPeticionDTO peticion_eliminado = new EliminarPeticionDTO(nro_peticion);
                            boolean respuesta = Controller.getControlador().eliminarPeticion(peticion_eliminado);

                            if (respuesta) {
                                JOptionPane.showMessageDialog(null, "El Peticion se ha eliminado correctamente", "Peticion Eliminado!", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "El Peticion no existe en el sistema", "Peticion no existe", JOptionPane.ERROR_MESSAGE);
                            }
                            nroPeticion.setText("");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            btnEliminarPeticion.setBounds(170, 266, 140, 20);
            contentPane.add(btnEliminarPeticion);

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

