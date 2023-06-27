package ioo.view.Sucursal;

import ioo.controller.Controller;
import ioo.dto.SucursalDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearSucursal extends JFrame {

        private JPanel contentPane;
        private JTextField nroSucursal;
        private JTextField direccion;
        private JTextField nombreResponsableTecnico;

        public CrearSucursal() {
            setResizable(false);
            setTitle("Crear Sucursal");

            setBounds(100, 100, 442, 327);
            contentPane = new JPanel();
            contentPane.setBackground(UIManager.getColor("null"));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            setLocationRelativeTo(null);

            JLabel lbnroSucursal = new JLabel("Nro Sucursal:");
            lbnroSucursal.setBounds(10, 7, 120, 14);
            contentPane.add(lbnroSucursal);

            nroSucursal = new JTextField();
            nroSucursal.setBounds(124, 5, 50, 20);
            contentPane.add(nroSucursal);
            nroSucursal.setColumns(10);

            JLabel lbdireccion = new JLabel("Dirección:");
            lbdireccion.setBounds(10, 25, 120, 27);
            contentPane.add(lbdireccion);

            direccion = new JTextField();
            direccion.setBounds(124, 28, 275, 20);
            contentPane.add(direccion);
            direccion.setColumns(10);

            JLabel lbnombreResponsableTecnico = new JLabel("Responsable Técnico:");
            lbnombreResponsableTecnico.setBounds(10, 55, 46, 14);
            contentPane.add(lbnombreResponsableTecnico);

            nombreResponsableTecnico = new JTextField();
            nombreResponsableTecnico.setBounds(124, 53, 156, 20);
            contentPane.add(nombreResponsableTecnico);
            nombreResponsableTecnico.setColumns(10);

            JButton btnCrearSucursal = new JButton("Crear Paciente");
            btnCrearSucursal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (nroSucursal.getText().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "el campo de nro de sucursal debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                        } else if (direccion.getText().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "el campo de dirección debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                        } else if (nombreResponsableTecnico.getText().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "el campo del nombre del responsable técnico debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            int nro_sucursal = Integer.parseInt(nroSucursal.getText());
                            String direccion_sucursal = direccion.getText();
                            String ResponsableTecnico = nombreResponsableTecnico.getText();


                            SucursalDTO nueva_sucursal = new SucursalDTO(nro_sucursal, direccion_sucursal, ResponsableTecnico, null
                            );
                            boolean respuesta = Controller.getControlador().crearSucursal(nueva_sucursal);

                            if (respuesta) {
                                JOptionPane.showMessageDialog(null, "La sucursal se ha creado correctamente", "Paciente Creado", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "La sucursal ya existe en el sistema", "Paciente Duplicado", JOptionPane.ERROR_MESSAGE);
                            }
                            nroSucursal.setText("");
                            direccion.setText("");
                            nombreResponsableTecnico.setText("");

                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            btnCrearSucursal.setBounds(170, 266, 140, 20);
            contentPane.add(btnCrearSucursal);

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
