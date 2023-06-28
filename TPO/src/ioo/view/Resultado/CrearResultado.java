package ioo.view.Resultado;

import ioo.controller.Controller;
import ioo.dto.PacienteDTO;
import ioo.dto.ResultadoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearResultado extends JFrame {

    private JPanel contentPane;
    private JTextField CodigoPractica;
    private JTextField Valor;

    public CrearResultado() {
        setResizable(false);
        setTitle("Crear Resultado");

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

        JLabel lbCodigoPractica = new JLabel("Código de práctica:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbCodigoPractica, gbc);

        CodigoPractica = new JTextField();
        CodigoPractica.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(CodigoPractica, gbc);

        JLabel lbValor = new JLabel("Valor del resultado:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lbValor, gbc);

        Valor = new JTextField();
        Valor.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(Valor, gbc);

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
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Ocupa 2 columnas

        btnCrearResultado.setBackground(Color.BLUE);
        btnCrearResultado.setOpaque(true);
        btnCrearResultado.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrearResultado.setForeground(Color.WHITE);
        btnCrearResultado.setPreferredSize(new Dimension(200, 20));

        buttonPanel.add(btnCrearResultado, gbc);

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
