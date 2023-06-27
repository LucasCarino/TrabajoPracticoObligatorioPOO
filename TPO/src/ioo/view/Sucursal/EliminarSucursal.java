package ioo.view.Sucursal;
import ioo.controller.Controller;
import ioo.dto.EliminarSucursalDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarSucursal extends JFrame {
    private JPanel contentPane;
    private JTextField nroSucursalOrigen;
    private JTextField nroSucursalDestino;

    public EliminarSucursal() {
        setResizable(false);
        setTitle("Eliminar sucursal");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbNroSucursalOrigen = new JLabel("Número de sucursal a eliminar:");
        lbNroSucursalOrigen.setBounds(10, 7, 260, 14);
        contentPane.add(lbNroSucursalOrigen);

        nroSucursalOrigen = new JTextField();
        nroSucursalOrigen.setBounds(265, 5, 50, 20);
        contentPane.add(nroSucursalOrigen);
        nroSucursalOrigen.setColumns(10);

        JLabel lbNroSucursalDestino = new JLabel("Número de sucursal para derivar peticiones:");
        lbNroSucursalDestino.setBounds(10, 25, 260, 27);
        contentPane.add(lbNroSucursalDestino);

        nroSucursalDestino = new JTextField();
        nroSucursalDestino.setBounds(265, 28, 50, 20);
        contentPane.add(nroSucursalDestino);
        nroSucursalDestino.setColumns(10);


        JButton btnModificarPaciente = new JButton("Eliminar Sucursal");
        btnModificarPaciente.addActionListener(new ActionListener() {
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
        btnModificarPaciente.setBounds(170, 266, 140, 20);
        contentPane.add(btnModificarPaciente);

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
