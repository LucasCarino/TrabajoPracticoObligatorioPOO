package ioo.view.Sucursal;

import ioo.controller.Controller;
import ioo.dto.SucursalDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 442, 400);
            contentPane = new JPanel();
            contentPane.setBackground(UIManager.getColor("null"));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

            contentPane.setBackground(new Color(186, 246, 200));
            setContentPane(contentPane);
            contentPane.setLayout(new BorderLayout());
            setLocationRelativeTo(null);

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridBagLayout());
            formPanel.setBackground(new Color(186, 246, 200));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(0, 2, 5, 5));
            buttonPanel.setBackground(new Color(186, 246, 200));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel lbnroSucursal = new JLabel("Número sucursal:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            formPanel.add(lbnroSucursal, gbc);

            nroSucursal = new JTextField();
            nroSucursal.setColumns(15);
            gbc.gridx = 1;
            gbc.gridy = 0;
            formPanel.add(nroSucursal, gbc);

            JLabel lbdireccion = new JLabel("Dirección:");
            gbc.gridx = 0;
            gbc.gridy = 1;
            formPanel.add(lbdireccion, gbc);

            direccion = new JTextField();
            direccion.setColumns(15);
            gbc.gridx = 1;
            gbc.gridy = 1;
            formPanel.add(direccion, gbc);

            JLabel lbnombreResponsableTecnico = new JLabel("Responsable técnico:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(lbnombreResponsableTecnico, gbc);

            nombreResponsableTecnico = new JTextField();
            nombreResponsableTecnico.setColumns(15);
            gbc.gridx = 1;
            gbc.gridy = 2;
            formPanel.add(nombreResponsableTecnico, gbc);

            JButton btnCrearSucursal = new JButton("Crear Sucursal");
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
                                JOptionPane.showMessageDialog(null, "La sucursal se ha creado correctamente", "Sucursal Creada", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "La sucursal ya existe en el sistema", "Sucursal Duplicada", JOptionPane.ERROR_MESSAGE);
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
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 1; // Ocupa 2 columnas

            btnCrearSucursal.setBackground(Color.BLUE);
            btnCrearSucursal.setOpaque(true);
            btnCrearSucursal.setFont(new Font("Arial", Font.BOLD, 14));
            btnCrearSucursal.setForeground(Color.WHITE);
            btnCrearSucursal.setPreferredSize(new Dimension(200, 20));

            buttonPanel.add(btnCrearSucursal, gbc);

            JButton btnNewButton = new JButton("Volver atrás");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            gbc.gridx = 0;
            gbc.gridy = 7; // A continuación del botón "Crear Paciente"
            gbc.gridwidth = 1; // Ocupa 2 columnas
            buttonPanel.add(btnNewButton, gbc);

            contentPane.add(formPanel, BorderLayout.CENTER);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);
        }
    }
