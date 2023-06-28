package ioo.view.Peticiones;
import ioo.controller.Controller;
import ioo.dto.EliminarPeticionDTO;
import ioo.dto.PeticionDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    public class EliminarPeticion extends JFrame {
        private JPanel contentPane;
        private JTextField nroPeticion;

        public EliminarPeticion() {
            setResizable(false);
            setTitle("Eliminar Paciente");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 442, 327);
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

            JLabel lbPeticionId = new JLabel("Código petición:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            formPanel.add(lbPeticionId, gbc);

            nroPeticion = new JTextField();
            nroPeticion.setColumns(15);
            gbc.gridx = 1;
            gbc.gridy = 0;
            formPanel.add(nroPeticion, gbc);

            JButton btnEliminarPeticion = new JButton("Eliminar petición");
            btnEliminarPeticion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (nroPeticion.getText().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "el campo de nro del Petición debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                        } else {
                            int nro_peticion = Integer.parseInt(nroPeticion.getText());

                            EliminarPeticionDTO peticion_eliminada = new EliminarPeticionDTO(nro_peticion);
                            boolean respuesta = Controller.getControlador().eliminarPeticion(peticion_eliminada);

                            if (respuesta) {
                                JOptionPane.showMessageDialog(null, "La Petición se ha eliminado correctamente", "Peticion Eliminada!", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "La Petición no existe en el sistema", "Peticion no existe", JOptionPane.ERROR_MESSAGE);
                            }
                            nroPeticion.setText("");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 1; // Ocupa 2 columnas

            btnEliminarPeticion.setBackground(Color.RED);
            btnEliminarPeticion.setOpaque(true);
            btnEliminarPeticion.setFont(new Font("Arial", Font.BOLD, 14));
            btnEliminarPeticion.setForeground(Color.WHITE);
            btnEliminarPeticion.setPreferredSize(new Dimension(200, 20));
            buttonPanel.add(btnEliminarPeticion, gbc);

            JButton btnNewButton = new JButton("Volver atrás");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 1;
            buttonPanel.add(btnNewButton, gbc);

            contentPane.add(formPanel, BorderLayout.CENTER);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);
        }
    }

