package ioo.view.Practicas;

import ioo.controller.Controller;
import ioo.dto.PacienteDTO;
import ioo.dto.PracticaDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPractica extends JFrame {
    private JPanel contentPane;
    private JTextField codigoPractica;
    private JTextField nombre;
    private JTextField grupo;
    private JTextField valoresCriticos;
    private JTextField valoresReservados;
    private JTextField horaParaResultado;

    public ModificarPractica() {
        setResizable(false);
        setTitle("Modificar Paciente");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor(null));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbcodigoPractica = new JLabel("ID:");
        lbcodigoPractica.setBounds(10, 7, 120, 14);
        contentPane.add(lbcodigoPractica);

        codigoPractica = new JTextField();
        codigoPractica.setBounds(124, 5, 50, 20);
        contentPane.add(codigoPractica);
        codigoPractica.setColumns(10);

        JLabel lbNombrePaciente = new JLabel("Nombre:");
        lbNombrePaciente.setBounds(10, 25, 120, 27);
        contentPane.add(lbNombrePaciente);

        nombre = new JTextField();
        nombre.setBounds(124, 28, 275, 20);
        contentPane.add(nombre);
        nombre.setColumns(10);

        JLabel lbDNIPaciente = new JLabel("Grupo:");
        lbDNIPaciente.setBounds(10, 55, 46, 14);
        contentPane.add(lbDNIPaciente);

        grupo = new JTextField();
        grupo.setBounds(124, 53, 156, 20);
        contentPane.add(grupo);
        grupo.setColumns(10);

        JLabel lbvaloresCriticos = new JLabel("valoresCriticos");
        lbvaloresCriticos.setBounds(10, 78, 120, 28);
        contentPane.add(lbvaloresCriticos);

        valoresCriticos = new JTextField();
        valoresCriticos.setBounds(124, 80, 30, 20);
        contentPane.add(valoresCriticos);
        valoresCriticos.setColumns(10);

        JLabel lbvaloresReservados = new JLabel("Valores Reservados:");
        lbvaloresReservados.setBounds(10, 106, 90, 14);
        contentPane.add(lbvaloresReservados);

        valoresReservados = new JTextField();
        valoresReservados.setBounds(124, 104, 30, 20);
        contentPane.add(valoresReservados);
        valoresReservados.setColumns(10);


        JLabel lbhoraParaResultado = new JLabel("Hora para Resultado:");
        lbhoraParaResultado.setBounds(10, 130, 100, 14);
        contentPane.add(lbhoraParaResultado);

        horaParaResultado = new JTextField();
        horaParaResultado.setBounds(124, 128, 132, 20);
        contentPane.add(horaParaResultado);
        horaParaResultado.setColumns(10);

        JButton btnModificarPractica = new JButton("Modificar Practica");
        btnModificarPractica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (codigoPractica.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro del paciente debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (nombre.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nombre debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (grupo.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de DNI debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (valoresCriticos.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Edad debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (valoresReservados.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Sexo debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (horaParaResultado.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Domicilio debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);}
                    else {
                                int nro_practica = Integer.parseInt(codigoPractica.getText());
                                String nombre_practica = nombre.getText();
                                String grupo_practica = grupo.getText();
                                int critico = Integer.parseInt(valoresCriticos.getText());
                                boolean reservado = Boolean.parseBoolean(valoresReservados.getText());
                                int hora = Integer.parseInt(horaParaResultado.getText());

                                PracticaDTO modificacion_practica = new PracticaDTO(nro_practica, nombre_practica, grupo_practica, critico,
                                        reservado, hora);
                                boolean respuesta = Controller.getControlador().modificarPractica(modificacion_practica);

                                if (respuesta) {
                                    JOptionPane.showMessageDialog(null, "La práctica se ha modificado correctamente", "Práctica Modificada!", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "La práctica no existe en el sistema", "Práctica no existe", JOptionPane.ERROR_MESSAGE);
                                }
                        codigoPractica.setText("");
                        nombre.setText("");
                        grupo.setText("");
                        valoresCriticos.setText("");
                        valoresReservados.setText("");
                        horaParaResultado.setText("");
                            }
                        } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "No ingrese caracteres en los campos de sólo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnModificarPractica.setBounds(170, 266, 140, 20);
        contentPane.add(btnModificarPractica);

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
