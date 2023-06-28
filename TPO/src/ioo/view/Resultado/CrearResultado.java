package ioo.view.Resultado;

import ioo.controller.Controller;
import ioo.dto.PacienteDTO;
import ioo.dto.ResultadoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearResultado extends JFrame {

    private JPanel contentPane;
    private JTextField CodigoPractica;
    private JTextField Valor;

    public CrearResultado() {
        setResizable(false);
        setTitle("Crear resultado");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("null"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbCodigoPractica = new JLabel("Código practica:");
        lbCodigoPractica.setBounds(10, 25, 120, 27);
        contentPane.add(lbCodigoPractica);

        CodigoPractica = new JTextField();
        CodigoPractica.setBounds(124, 28, 275, 20);
        contentPane.add(CodigoPractica);
        CodigoPractica.setColumns(10);

        JLabel lbValor = new JLabel("Valor:");
        lbValor.setBounds(10, 55, 46, 14);
        contentPane.add(lbValor);

        Valor = new JTextField();
        Valor.setBounds(124, 53, 156, 20);
        contentPane.add(Valor);
        Valor.setColumns(10);

        JButton btnCrearResultado = new JButton("Crear Resultado");
        btnCrearResultado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (CodigoPractica.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo del código de práctica debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (Valor.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo del valor debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int codigo_practica = Integer.parseInt(CodigoPractica.getText());
                        int valor = Integer.parseInt(Valor.getText());

                        ResultadoDTO nuevo_resultado = new ResultadoDTO(codigo_practica, valor);
                        boolean respuesta = Controller.getControlador().crearResultado(nuevo_resultado);

                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "El resultado se ha creado correctamente", "Resultado Creado", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe un resultado para esta práctica", "Resultado Duplicado", JOptionPane.ERROR_MESSAGE);
                        }
                        CodigoPractica.setText("");
                        Valor.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCrearResultado.setBounds(170, 266, 140, 20);
        contentPane.add(btnCrearResultado);

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
