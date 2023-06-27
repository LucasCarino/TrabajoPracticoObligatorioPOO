package ioo.view.Practicas;

import ioo.controller.Controller;
import ioo.dto.PracticaDTO;
import ioo.model.Resultado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CrearPractica extends JFrame {

    private JPanel contentPane;
    private JTextField codigoPractica;
    private JTextField nombre;
    private JTextField grupo;
    private JTextField valoresCriticos;

    private JTextField valoresReservados;
    private JTextField horaParaResultado;

    public CrearPractica() {
        setResizable(false);
        setTitle("Crear Práctica");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbPracticaId = new JLabel("ID Práctica:");
        lbPracticaId.setBounds(10, 7, 120, 14);
        contentPane.add(lbPracticaId);

        codigoPractica = new JTextField();
        codigoPractica.setBounds(175, 5, 120, 20);
        contentPane.add(codigoPractica);
        codigoPractica.setColumns(10);

        JLabel lbnombrePractica = new JLabel("Nombre:");
        lbnombrePractica.setBounds(10, 25, 120, 27);
        contentPane.add(lbnombrePractica);

        nombre = new JTextField();
        nombre.setBounds(175, 28, 120, 20);
        contentPane.add(nombre);
        nombre.setColumns(10);

        JLabel lbgrupo = new JLabel("Grupo:");
        lbgrupo.setBounds(10, 55, 120, 14);
        contentPane.add(lbgrupo);

        grupo = new JTextField();
        grupo.setBounds(175, 53, 120, 20);
        contentPane.add(grupo);
        grupo.setColumns(10);

        JLabel lbvaloresCriticos = new JLabel("Valores Críticos:");
        lbvaloresCriticos.setBounds(10, 78, 150, 28);

        contentPane.add(lbvaloresCriticos);

        valoresCriticos = new JTextField();
        valoresCriticos.setBounds(175, 80, 120, 20);

        contentPane.add(valoresCriticos);
        valoresCriticos.setColumns(10);

        JLabel lbvaloresReservados = new JLabel("Valores Reservados:");
        lbvaloresReservados.setBounds(10, 106, 150, 14);
        contentPane.add(lbvaloresReservados);

        valoresReservados = new JTextField();
        valoresReservados.setBounds(175, 104, 120, 20);
        contentPane.add(valoresReservados);
        valoresReservados.setColumns(10);

        JLabel lbhoraParaResultado = new JLabel("Horas para resultado:");
        lbhoraParaResultado.setBounds(10, 130, 150, 14);
        contentPane.add(lbhoraParaResultado);

        horaParaResultado = new JTextField();
        horaParaResultado.setBounds(175, 128, 120, 20);
        contentPane.add(horaParaResultado);
        horaParaResultado.setColumns(10);

        JButton btnCrearPractica = new JButton("Crear Practica");
        btnCrearPractica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (codigoPractica.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo del código de la práctica debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (nombre.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nombre debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (grupo.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de grupo debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (valoresCriticos.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de valores críticos debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (valoresReservados.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de valores reservados debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (horaParaResultado.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de horaParaResultado debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        int codigo_practica = Integer.parseInt(codigoPractica.getText());
                        String nombre_practica = nombre.getText();
                        String grupo_practica = grupo.getText();
                        int valores_criticos = Integer.parseInt(valoresCriticos.getText());
                        boolean valores_reservados = Boolean.parseBoolean(valoresReservados.getText());
                        int horas_para_resultado = Integer.parseInt(horaParaResultado.getText());
                        
                        PracticaDTO nueva_practica = new PracticaDTO(codigo_practica, nombre_practica, grupo_practica, valores_criticos,
                                valores_reservados, horas_para_resultado);
                        boolean respuesta = Controller.getControlador().crearPractica(nueva_practica);

                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "La práctica se ha creado correctamente", "Práctica Creada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "La práctica ya existe en el sistema", "Práctica Duplicada", JOptionPane.ERROR_MESSAGE);
                        }
                        codigoPractica.setText("");
                        nombre.setText("");
                        grupo.setText("");
                        valoresCriticos.setText("");
                        valoresReservados.setText("");
                        horaParaResultado.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo números", "Error caracter ingresado erróneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCrearPractica.setBounds(170, 266, 140, 20);
        contentPane.add(btnCrearPractica);

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


