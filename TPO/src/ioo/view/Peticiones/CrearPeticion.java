package ioo.view.Peticiones;

import ioo.controller.Controller;
import ioo.dto.PeticionDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearPeticion extends JFrame {

    private JPanel contentPane;
    private JTextField numeroPeticion;
    private JTextField NumeroPaciente;
    private JTextField obraSocial;
    private JTextField practicasAsociadas;
    private JTextField numeroSucursal;

    public CrearPeticion() {
        setResizable(false);
        setTitle("Crear Petición");

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

        numeroPeticion = new JTextField();
        numeroPeticion.setBounds(124, 5, 50, 20);
        contentPane.add(numeroPeticion);
        numeroPeticion.setColumns(10);

        JLabel lbNumeroPaciente = new JLabel("Número Paciente:");
        lbNumeroPaciente.setBounds(10, 25, 120, 27);
        contentPane.add(lbNumeroPaciente);

        NumeroPaciente = new JTextField();
        NumeroPaciente.setBounds(124, 28, 275, 20);
        contentPane.add(NumeroPaciente);
        NumeroPaciente.setColumns(10);

        JLabel lbObraSocial = new JLabel("Obra Social:");
        lbObraSocial.setBounds(10, 55, 46, 14);
        contentPane.add(lbObraSocial);

        obraSocial = new JTextField();
        obraSocial.setBounds(124, 53, 156, 20);
        contentPane.add(obraSocial);
        obraSocial.setColumns(10);

        JLabel lbpracticaAsociada = new JLabel("Código de prácticas:");
        lbpracticaAsociada.setBounds(10, 106, 90, 14);
        contentPane.add(lbpracticaAsociada);

        practicasAsociadas = new JTextField();
        practicasAsociadas.setBounds(124, 104, 30, 20);
        contentPane.add(practicasAsociadas);
        practicasAsociadas.setColumns(10);

        JLabel lbnumerosucursal = new JLabel("Número de Sucursal:");
        lbnumerosucursal.setBounds(10, 155, 90, 14);
        contentPane.add(lbnumerosucursal);

        numeroSucursal = new JTextField();
        numeroSucursal.setBounds(124, 154, 132, 20);
        contentPane.add(numeroSucursal);
        numeroSucursal.setColumns(10);

        JButton btnCrearPeticion = new JButton("Crear Petición");
        btnCrearPeticion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (numeroPeticion.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro de petición debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (NumeroPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro paciente debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (obraSocial.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Obra Social debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (practicasAsociadas.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Prácticas Asociadas debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }else if (numeroSucursal.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro de Sucursal debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        int nro_peticion = Integer.parseInt(numeroPeticion.getText());
                        int nombre_paciente = Integer.parseInt(NumeroPaciente.getText());
                        String obra_social = (obraSocial.getText());
                        int practicas_asociadas = Integer.parseInt(practicasAsociadas.getText());
                        int nro_sucursal = Integer.parseInt(numeroSucursal.getText());

                        //PeticionDTO nueva_peticion = new PeticionDTO(nro_peticion, nombre_paciente, obra_social, practicas_asociadas, nro_sucursal);
                        //boolean respuesta = Controller.getControlador().crearPeticion(nro_peticion);

                        boolean respuesta = true;
                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "El paciente se ha creado correctamente", "Paciente Creado", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "El paciente ya existe en el sistema", "Paciente Duplicado", JOptionPane.ERROR_MESSAGE);
                        }
                        numeroPeticion.setText("");
                        NumeroPaciente.setText("");
                        obraSocial.setText("");
                        practicasAsociadas.setText("");
                        numeroSucursal.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCrearPeticion.setBounds(170, 266, 140, 20);
        contentPane.add(btnCrearPeticion);

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

