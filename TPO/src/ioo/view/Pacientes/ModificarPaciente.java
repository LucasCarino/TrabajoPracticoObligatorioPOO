package ioo.view.Pacientes;

import ioo.controller.Controller;
import ioo.dto.PacienteDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarPaciente extends JFrame {
    private JPanel contentPane;
    private JTextField nroPaciente;
    private JTextField NombrePaciente;
    private JTextField EdadPaciente;
    private JTextField SexoPaciente;
    private JTextField DNIPaciente;
    private JTextField DomicilioPaciente;
    private JTextField MailPaciente;

    public ModificarPaciente() {
        setResizable(false);
        setTitle("Modificar Paciente");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbPacienteId = new JLabel("ID:");
        lbPacienteId.setBounds(10, 7, 120, 14);
        contentPane.add(lbPacienteId);

        nroPaciente = new JTextField();
        nroPaciente.setBounds(124, 5, 50, 20);
        contentPane.add(nroPaciente);
        nroPaciente.setColumns(10);

        JLabel lbNombrePaciente = new JLabel("Nombre:");
        lbNombrePaciente.setBounds(10, 25, 120, 27);
        contentPane.add(lbNombrePaciente);

        NombrePaciente = new JTextField();
        NombrePaciente.setBounds(124, 28, 275, 20);
        contentPane.add(NombrePaciente);
        NombrePaciente.setColumns(10);

        JLabel lbDNIPaciente = new JLabel("DNI:");
        lbDNIPaciente.setBounds(10, 55, 46, 14);
        contentPane.add(lbDNIPaciente);

        DNIPaciente = new JTextField();
        DNIPaciente.setBounds(124, 53, 156, 20);
        contentPane.add(DNIPaciente);
        DNIPaciente.setColumns(10);

        JLabel lbEdadPaciente = new JLabel("Edad");
        lbEdadPaciente.setBounds(10, 78, 120, 28);

        contentPane.add(lbEdadPaciente);

        EdadPaciente = new JTextField();
        EdadPaciente.setBounds(124, 80, 30, 20);

        contentPane.add(EdadPaciente);
        EdadPaciente.setColumns(10);

        JLabel lbSexoPaciente = new JLabel("Sexo:");
        lbSexoPaciente.setBounds(10, 106, 90, 14);
        contentPane.add(lbSexoPaciente);

        SexoPaciente = new JTextField();
        SexoPaciente.setBounds(124, 104, 30, 20);
        contentPane.add(SexoPaciente);
        SexoPaciente.setColumns(10);


        JLabel lbDomicilioPaciente = new JLabel("Domicilio:");
        lbDomicilioPaciente.setBounds(10, 130, 100, 14);
        contentPane.add(lbDomicilioPaciente);

        DomicilioPaciente = new JTextField();
        DomicilioPaciente.setBounds(124, 128, 132, 20);
        contentPane.add(DomicilioPaciente);
        DomicilioPaciente.setColumns(10);


        JLabel lbMail = new JLabel("Mail:");
        lbMail.setBounds(10, 155, 90, 14);
        contentPane.add(lbMail);

        MailPaciente = new JTextField();
        MailPaciente.setBounds(124, 154, 132, 20);
        contentPane.add(MailPaciente);
        MailPaciente.setColumns(10);

        JButton btnModificarPaciente = new JButton("Modificar Paciente");
        btnModificarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nroPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro del paciente debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else if (NombrePaciente.getText().equalsIgnoreCase("")) {
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
                        int nro_paciente = Integer.parseInt(nroPaciente.getText());
                        String nombre_paciente = NombrePaciente.getText();
                        int dni_paciente = Integer.parseInt(DNIPaciente.getText());
                        int edad_paciente = Integer.parseInt(EdadPaciente.getText());
                        String sexo_paciente = SexoPaciente.getText();
                        String domicilio_paciente = DomicilioPaciente.getText();
                        String mail_paciente = MailPaciente.getText();

                        PacienteDTO modificacion_paciente = new PacienteDTO(nro_paciente, sexo_paciente, edad_paciente, dni_paciente,
                                nombre_paciente, domicilio_paciente, mail_paciente);
                        boolean respuesta = Controller.getControlador().modificarPaciente(modificacion_paciente);

                        if (respuesta) {
                            JOptionPane.showMessageDialog(null, "El paciente se ha modificado correctamente", "Paciente Modificado!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "El paciente no existe en el sistema", "Paciente no existe", JOptionPane.ERROR_MESSAGE);
                        }
                        nroPaciente.setText("");
                        NombrePaciente.setText("");
                        DNIPaciente.setText("");
                        EdadPaciente.setText("");
                        SexoPaciente.setText("");
                        DomicilioPaciente.setText("");
                        MailPaciente.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "No ingrese caracteres en los campos de sólo numeros", "Error caracter ingresado erroneamente", JOptionPane.ERROR_MESSAGE);
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
