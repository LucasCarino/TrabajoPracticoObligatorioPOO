package ioo.view.Peticiones;

import ioo.controller.Controller;
import ioo.dto.PacienteDTO;
import ioo.dto.PeticionDTO;

import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

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
        JComboBox<String> comboBox;

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
        numeroPeticion.setBounds(150, 5, 50, 20);
        contentPane.add(numeroPeticion);
        numeroPeticion.setColumns(10);

        JLabel lbNumeroPaciente = new JLabel("Número Paciente:");
        lbNumeroPaciente.setBounds(10, 25, 120, 27);
        contentPane.add(lbNumeroPaciente);

        NumeroPaciente = new JTextField();
        NumeroPaciente.setBounds(150, 28, 50, 20);
        contentPane.add(NumeroPaciente);
        NumeroPaciente.setColumns(10);

        JLabel lbObraSocial = new JLabel("Obra Social:");
        lbObraSocial.setBounds(10, 55, 120, 14);
        contentPane.add(lbObraSocial);

        obraSocial = new JTextField();
        obraSocial.setBounds(150, 53, 156, 20);
        contentPane.add(obraSocial);
        obraSocial.setColumns(10);

        JLabel lbnumerosucursal = new JLabel("Número de Sucursal:");
        lbnumerosucursal.setBounds(10, 80, 150, 14);
        contentPane.add(lbnumerosucursal);

        numeroSucursal = new JTextField();
        numeroSucursal.setBounds(150, 80, 132, 20);
        contentPane.add(numeroSucursal);
        numeroSucursal.setColumns(10);


        JLabel lbpracticaAsociada = new JLabel("Código de prácticas:");
        lbpracticaAsociada.setBounds(10, 106, 120, 14);
        contentPane.add(lbpracticaAsociada);
        JCheckBox checkbox1 = new JCheckBox("Glucemia");
        JCheckBox checkbox2 = new JCheckBox("Colesterol");
        JCheckBox checkbox3 = new JCheckBox("Cloruro");
        JCheckBox checkbox4 = new JCheckBox("Creatinina");
        JCheckBox checkbox5 = new JCheckBox("HIV");
        checkbox1.setBounds(150, 106, 120, 20);
        checkbox2.setBounds(150, 126, 120, 20);
        checkbox3.setBounds(150, 146, 120, 20);
        checkbox4.setBounds(150, 166, 120, 20);
        checkbox5.setBounds(150, 186, 120, 20);
        contentPane.add(checkbox1);
        contentPane.add(checkbox2);
        contentPane.add(checkbox3);
        contentPane.add(checkbox4);
        contentPane.add(checkbox5);

        List<Integer> practicasSeleccionadas = new ArrayList<>();

        checkbox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkbox1.isSelected()) {
                    practicasSeleccionadas.add(1);
                } else {
                    practicasSeleccionadas.remove(Integer.valueOf(1));
                }
            }
        });

        checkbox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkbox2.isSelected()) {
                    practicasSeleccionadas.add(2);
                } else {
                    practicasSeleccionadas.remove(Integer.valueOf(2));
                }
            }
        });

        checkbox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkbox3.isSelected()) {
                    practicasSeleccionadas.add(3);
                } else {
                    practicasSeleccionadas.remove(Integer.valueOf(3));
                }
            }
        });

        checkbox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkbox4.isSelected()) {
                    practicasSeleccionadas.add(4);
                } else {
                    practicasSeleccionadas.remove(Integer.valueOf(4));
                }
            }
        });

        checkbox5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkbox5.isSelected()) {
                    practicasSeleccionadas.add(5);
                } else {
                    practicasSeleccionadas.remove(Integer.valueOf(5));
                }
            }
        });

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
                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Debe escoger al menos una práctica", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }else if (numeroSucursal.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro de Sucursal debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }
                    else {

                        int nro_peticion = Integer.parseInt(numeroPeticion.getText());
                        int nro_paciente = Integer.parseInt(NumeroPaciente.getText());
                        String obra_social = (obraSocial.getText());
                        int nro_sucursal = Integer.parseInt(numeroSucursal.getText());

                        PeticionDTO nueva_peticion = new PeticionDTO(nro_peticion, nro_paciente, obra_social, practicasSeleccionadas, nro_sucursal);
                        boolean respuesta = Controller.getControlador().crearPeticion(nueva_peticion);
                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "La petición se ha creado correctamente", "Petición Creada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe una petición con ese número en el sistema", "Petición Duplicada", JOptionPane.ERROR_MESSAGE);
                        }
                        numeroPeticion.setText("");
                        NumeroPaciente.setText("");
                        obraSocial.setText("");
                        numeroSucursal.setText("");

                        // TODO Ver si se puede desmarcar los checkbox
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

