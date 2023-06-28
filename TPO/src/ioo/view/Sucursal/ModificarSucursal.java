package ioo.view.Sucursal;

import ioo.controller.Controller;
import ioo.dto.PacienteDTO;
import ioo.dto.SucursalDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarSucursal extends JFrame {
    private JPanel contentPane;
    private JTextField nroSucursal;
    private JTextField direccion;
    private JTextField nombreResponsableTecnico;

    public ModificarSucursal() {
        setResizable(false);
        setTitle("Crear Paciente");

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

        JLabel lbnroSucursal = new JLabel("Nro sucursal:");
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

        JButton btnModificarSucursal = new JButton("Modificar Sucursal");
        btnModificarSucursal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nroSucursal.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro de sucursal debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (direccion.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de dirección debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (nombreResponsableTecnico.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nombre Responsable Técnico debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        int nro_sucursal = Integer.parseInt(nroSucursal.getText());
                        String direccion_sucursal = direccion.getText();
                        String responsable_tecnico = nombreResponsableTecnico.getText();

                        SucursalDTO modificacion_sucursal = new SucursalDTO(nro_sucursal, direccion_sucursal, responsable_tecnico, null);
                        boolean respuesta = Controller.getControlador().modificarSucursal(modificacion_sucursal);
                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "El paciente se ha modificado correctamente", "Paciente Modificado!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "El paciente no existe en el sistema", "Paciente no existe", JOptionPane.ERROR_MESSAGE);
                        }
                        nroSucursal.setText("");
                        direccion.setText("");
                        nombreResponsableTecnico.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "No ingrese caracteres en los campos de sólo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Ocupa 2 columnas

        btnModificarSucursal.setBackground(Color.BLUE);
        btnModificarSucursal.setOpaque(true);
        btnModificarSucursal.setFont(new Font("Arial", Font.BOLD, 14));
        btnModificarSucursal.setForeground(Color.WHITE);
        btnModificarSucursal.setPreferredSize(new Dimension(200, 20));

        buttonPanel.add(btnModificarSucursal, gbc);

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

