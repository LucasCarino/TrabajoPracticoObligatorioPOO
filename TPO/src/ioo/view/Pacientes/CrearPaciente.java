package ioo.view.Pacientes;
import ioo.controller.Controller;
import ioo.dto.PacienteDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CrearPaciente extends JFrame {

    private JPanel contentPane;
    private JTextField NombrePaciente;
    private JTextField EdadPaciente;
    private JTextField SexoPaciente;
    private JTextField DNIPaciente;
    private JTextField DomicilioPaciente;
    private JTextField MailPaciente;

    public CrearPaciente() {
        setResizable(false);
        setTitle("Crear Paciente");

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

        JLabel lbNombrePaciente = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbNombrePaciente, gbc);

        NombrePaciente = new JTextField();
        NombrePaciente.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(NombrePaciente, gbc);

        JLabel lbDNIPaciente = new JLabel("DNI:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lbDNIPaciente, gbc);

        DNIPaciente = new JTextField();
        DNIPaciente.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(DNIPaciente, gbc);

        JLabel lbEdadPaciente = new JLabel("Edad");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lbEdadPaciente, gbc);

        EdadPaciente = new JTextField();
        EdadPaciente.setColumns(5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(EdadPaciente, gbc);

        JLabel lbSexoPaciente = new JLabel("Sexo:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lbSexoPaciente, gbc);

        SexoPaciente = new JTextField();
        SexoPaciente.setColumns(5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(SexoPaciente, gbc);

        JLabel lbDomicilioPaciente = new JLabel("Domicilio:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lbDomicilioPaciente, gbc);

        DomicilioPaciente = new JTextField();
        DomicilioPaciente.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(DomicilioPaciente, gbc);

        JLabel lbMail = new JLabel("Mail:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lbMail, gbc);

        MailPaciente = new JTextField();
        MailPaciente.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(MailPaciente, gbc);

        JButton btnCrearPaciente = new JButton("Crear Paciente");

        btnCrearPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (NombrePaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nombre debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (DNIPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de DNI debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (EdadPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Edad debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (SexoPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Sexo debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (DomicilioPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Domicilio debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }else if (MailPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de Mail debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        String nombre_paciente = NombrePaciente.getText();
                        int dni_paciente = Integer.parseInt(DNIPaciente.getText());
                        int edad_paciente = Integer.parseInt(EdadPaciente.getText());
                        String sexo_paciente = SexoPaciente.getText();
                        String domicilio_paciente = DomicilioPaciente.getText();
                        String mail_paciente = MailPaciente.getText();

                        PacienteDTO nuevo_paciente = new PacienteDTO(sexo_paciente, edad_paciente, dni_paciente,
                                nombre_paciente, domicilio_paciente, mail_paciente);
                        boolean respuesta = Controller.getControlador().crearPaciente(nuevo_paciente);

                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "El paciente se ha creado correctamente", "Paciente Creado", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "El paciente ya existe en el sistema", "Paciente Duplicado", JOptionPane.ERROR_MESSAGE);
                        }
                        NombrePaciente.setText("");
                        DNIPaciente.setText("");
                        EdadPaciente.setText("");
                        SexoPaciente.setText("");
                        DomicilioPaciente.setText("");
                        MailPaciente.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // Ocupa 2 columnas

        btnCrearPaciente.setBackground(Color.BLUE);
        btnCrearPaciente.setOpaque(true);
        btnCrearPaciente.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrearPaciente.setForeground(Color.WHITE);
        btnCrearPaciente.setPreferredSize(new Dimension(200, 20));

        buttonPanel.add(btnCrearPaciente, gbc);

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
