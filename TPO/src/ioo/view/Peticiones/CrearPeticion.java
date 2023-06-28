package ioo.view.Peticiones;

import ioo.controller.Controller;
import ioo.dto.*;
import ioo.model.*;

import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

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

        JLabel lbPeticionId = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbPeticionId, gbc);

        numeroPeticion = new JTextField();
        numeroPeticion.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(numeroPeticion, gbc);

        JLabel lbNumeroPaciente = new JLabel("DNI del paciente:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lbNumeroPaciente, gbc);

        NumeroPaciente = new JTextField();
        NumeroPaciente.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(NumeroPaciente, gbc);

        JLabel lbObraSocial = new JLabel("Obra social:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lbObraSocial, gbc);

        obraSocial = new JTextField();
        obraSocial.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(obraSocial, gbc);

        JLabel lbnumerosucursal = new JLabel("Número de sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lbnumerosucursal, gbc);

        numeroSucursal = new JTextField();
        numeroSucursal.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(numeroSucursal, gbc);

        JLabel lbpracticaAsociada = new JLabel("Prácticas:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lbpracticaAsociada, gbc);

        JCheckBox checkbox1 = new JCheckBox("Glucemia");
        JCheckBox checkbox2 = new JCheckBox("Colesterol");
        JCheckBox checkbox3 = new JCheckBox("Cloruro");
        JCheckBox checkbox4 = new JCheckBox("Creatinina");
        JCheckBox checkbox5 = new JCheckBox("HIV");

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(checkbox1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(checkbox2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(checkbox3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(checkbox4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(checkbox5, gbc);

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


                        // cambiar esto a un arr
                        Vector<Practica> respuestas_practicas_asociadas = new Vector<>();
                        respuestas_practicas_asociadas = Controller.getControlador().crearPeticion(nueva_peticion);

                        if (respuestas_practicas_asociadas.size() > 0) {

                            JOptionPane.showMessageDialog(null, "La petición se ha creado correctamente", "Petición Creada", JOptionPane.INFORMATION_MESSAGE);

                            // Crear los datos de ejemplo para la tabla
                            List<Practica> tablaPracticas = new ArrayList<>();

                            for (Practica obj : respuestas_practicas_asociadas) {
                                tablaPracticas.add(obj);
                            }

                            Object[][] data = new Object[tablaPracticas.size()][3];
                            for (int i = 0; i < tablaPracticas.size(); i++) {
                                Practica practica = tablaPracticas.get(i);
                                data[i][0] = practica.getCU(); // Reemplaza con el método adecuado para obtener el valor de la columna 1
                                data[i][1] = practica.getNombre(); // Reemplaza con el método adecuado para obtener el valor de la columna 2
                                data[i][2] = practica.getHoraParaResultado(); // Reemplaza con el método adecuado para obtener el valor de la columna 3
                            }

                            // Crear los nombres de columna para la tabla
                            String[] columnNames = {"Código único de la Practica", "Nombre", "Hora para resultado"};
                            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                            // Crear la tabla con el modelo
                            JTable table = new JTable(tableModel);

                            // Crear un JScrollPane para permitir desplazamiento si la tabla tiene muchos datos
                            JScrollPane scrollPane = new JScrollPane(table);

                            // Mostrar el diálogo con la tabla dentro de un JScrollPane
                            JOptionPane.showMessageDialog(null, scrollPane, "Practicas asociadas", JOptionPane.PLAIN_MESSAGE);
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
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Ocupa 2 columnas

        btnCrearPeticion.setBackground(Color.BLUE);
        btnCrearPeticion.setOpaque(true);
        btnCrearPeticion.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrearPeticion.setForeground(Color.WHITE);
        btnCrearPeticion.setPreferredSize(new Dimension(200, 20));

        buttonPanel.add(btnCrearPeticion, gbc);

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

