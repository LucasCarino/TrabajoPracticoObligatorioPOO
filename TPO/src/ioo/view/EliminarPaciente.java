package ioo.view;

import ioo.controller.Controller;
import ioo.dto.EliminarPacienteDTO;
import ioo.dto.PacienteDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarPaciente extends JFrame {
    private JPanel contentPane;
    private JTextField nroPaciente;

    public EliminarPaciente() {
        setResizable(false);
        setTitle("Eliminar Paciente");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbPacienteId = new JLabel("Nro de Paciente:");
        lbPacienteId.setBounds(10, 7, 120, 14);
        contentPane.add(lbPacienteId);

        nroPaciente = new JTextField();
        nroPaciente.setBounds(124, 5, 50, 20);
        contentPane.add(nroPaciente);
        nroPaciente.setColumns(10);


        JButton btnModificarPaciente = new JButton("Eliminar Paciente");
        btnModificarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nroPaciente.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo de nro del paciente debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int nro_paciente = Integer.parseInt(nroPaciente.getText());

                        EliminarPacienteDTO paciente_eliminado = new EliminarPacienteDTO(nro_paciente);
                        int respuesta = Controller.getControlador().eliminarPaciente(paciente_eliminado);
<<<<<<< HEAD
                        Controller.getPacientes();
=======
>>>>>>> 4683259556f59d20e722092bd3a1b33c2ec710b4

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
