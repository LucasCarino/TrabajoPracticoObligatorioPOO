package ioo.view.Sucursal;
import ioo.controller.Controller;
import ioo.dto.EliminarSucursalDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarSucursal extends JFrame {
    private JPanel contentPane;
    private JTextField nroSucursalOrigen;
    private JTextField nroSucursalDestino;

    public EliminarSucursal() {
        setResizable(false);
        setTitle("Eliminar Sucursal");

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

        JLabel lbNroSucursalOrigen = new JLabel("Número de sucursal a eliminar:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lbNroSucursalOrigen, gbc);

        nroSucursalOrigen = new JTextField();
        nroSucursalOrigen.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(nroSucursalOrigen, gbc);

        JLabel lbNroSucursalDestino = new JLabel("Número de sucursal a derivar peticiones:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbNroSucursalDestino, gbc);

        nroSucursalDestino = new JTextField();
        nroSucursalDestino.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nroSucursalDestino, gbc);

        JButton btnEliminarSucursal = new JButton("Eliminar Sucursal");
        btnEliminarSucursal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nroSucursalOrigen.getText().equalsIgnoreCase("") && nroSucursalDestino.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Los campos de sucursal de origen y destino deben estar completos.", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (nroSucursalOrigen.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "El campos de sucursal de origen debe estar completo.", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (nroSucursalDestino.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "El campos de sucursal de destino debe estar completo.", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else
                    {
                        EliminarSucursalDTO sucursal_origen = new EliminarSucursalDTO(Integer.parseInt(nroSucursalOrigen.getText()));
                        EliminarSucursalDTO sucursal_destino = new EliminarSucursalDTO(Integer.parseInt(nroSucursalDestino.getText()));

                        int respuesta = Controller.getControlador().eliminarSucursal(sucursal_origen, sucursal_destino);

                        switch(respuesta) {
                            case 0:
                                JOptionPane.showMessageDialog(null, "La sucursal de origen no fue encontrada", "Sucursal de origen no encontrada", JOptionPane.WARNING_MESSAGE);
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(null, "La sucursal de destino no fue encontrada", "Sucursal de destino no encontrada", JOptionPane.WARNING_MESSAGE);
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "La sucursal de origen fue eliminada y las prácticas fueron derivadas a la sucursal de destino", "Sucursal de origen eliminada y prácticas derivadas", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, "La sucursal de origen fue eliminada, no se encontraron prácticas para derivar a la sucursal de destino", "Sucursal de origen eliminada", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 4:
                                JOptionPane.showMessageDialog(null, "La sucursal de origen no se puede eliminar porque tiene peticiones con resultados finalizados", "Sucursal de origen eliminada", JOptionPane.INFORMATION_MESSAGE);
                                break;
                        }
                        nroSucursalOrigen.setText("");
                        nroSucursalDestino.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Los campos ingresados no son válidos, ingrese un número.", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Ocupa 2 columnas

        btnEliminarSucursal.setBackground(Color.RED);
        btnEliminarSucursal.setOpaque(true);
        btnEliminarSucursal.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminarSucursal.setForeground(Color.WHITE);
        btnEliminarSucursal.setPreferredSize(new Dimension(200, 20));

        buttonPanel.add(btnEliminarSucursal, gbc);

        JButton btnNewButton = new JButton("Volver atrás");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Ocupa 2 columnas
        buttonPanel.add(btnNewButton, gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}
