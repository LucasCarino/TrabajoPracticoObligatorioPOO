package ioo.view.Pacientes;

import ioo.controller.Controller;
import ioo.dto.EliminarPacienteDTO;
import ioo.dto.PacienteDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPaciente extends JFrame {
    private JPanel contentPane;
    private JTextField nroPaciente;

    public EliminarPaciente() {
        setResizable(false);
        setTitle("Eliminar Paciente");

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

        JLabel lbPacienteId = new JLabel("Nro del Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbPacienteId, gbc);

        nroPaciente = new JTextField();
        nroPaciente.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nroPaciente, gbc);


        JButton btnEliminarPaciente = new JButton("Eliminar Paciente");
        btnEliminarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nroPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro del paciente debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int nro_paciente = Integer.parseInt(nroPaciente.getText());

                        EliminarPacienteDTO paciente_eliminado = new EliminarPacienteDTO(nro_paciente);
                        int respuesta = Controller.getControlador().eliminarPaciente(paciente_eliminado);

                        if (respuesta == 1) {
                            JOptionPane.showMessageDialog(null, "El paciente se ha eliminado correctamente", "Paciente Eliminado!", JOptionPane.INFORMATION_MESSAGE);
                        } else if (respuesta == 2) {
                            JOptionPane.showMessageDialog(null, "El paciente no se pudo eliminar porque tiene resultados", "Paciente no existe", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "El paciente no existe en el sistema", "Paciente no existe", JOptionPane.ERROR_MESSAGE);
                        }
                        nroPaciente.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Ocupa 2 columnas

        btnEliminarPaciente.setBackground(Color.RED);
        btnEliminarPaciente.setOpaque(true);
        btnEliminarPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminarPaciente.setForeground(Color.WHITE);
        btnEliminarPaciente.setPreferredSize(new Dimension(200, 20));
        buttonPanel.add(btnEliminarPaciente, gbc);

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
