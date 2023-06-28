package ioo.view.Listas;

import ioo.controller.Controller;
import ioo.dto.PeticionMVC;
import ioo.dto.PracticaDTO;
import ioo.dto.ResultadoPeticionDTO;
import ioo.model.Practica;
import ioo.model.Resultado;
import ioo.view.Sucursal.EliminarSucursal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;

// Referencia: https:www.tutorialspoint.com/swingexamples/create_table.htm

//        EL USUARIO FILTRA POR UN NUMERO DE PETICION Y SI ES CRITICO EL RESULTADO, DICE POR PANTALLA RETIRAR POR SUCURSAL. SI
//        NO ES CRITICO, MUESTRA LA INFO DE LA PETICION POR PANTALLA
public class MostrarPeticion extends JFrame {

    private JPanel contentPane;

    private JLabel lblObraSocial;
    private JLabel lblNroPeticion;
    private JLabel lblNroPaciente;
    private JLabel lblNombrePaciente;
    private JLabel lblPracticas;
    private JLabel lblNroSucursal;
    private JTextField codigoPeticion;

    // ejemplos
    private JLabel lblMensaje;
    private JTable tablaDatos;

    public MostrarPeticion() {
        setResizable(false);
        setTitle("Mostrar Petición");

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

        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(new Color(186, 246, 200));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 5, 5));
        buttonPanel.setBackground(new Color(186, 246, 200));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lbNombrePaciente = new JLabel("ID Petición:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lbNombrePaciente, gbc);

        codigoPeticion = new JTextField();
        codigoPeticion.setColumns(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(codigoPeticion, gbc);


        JButton btnBuscarPeticion = new JButton("Buscar petición");
        btnBuscarPeticion.setBounds(0, 266, 130, 20);
        buttonPanel.add(btnBuscarPeticion);

        JButton btnNewButton = new JButton("Volver atrás");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Ocupa 2 columnas
        buttonPanel.add(btnNewButton, gbc);

        btnBuscarPeticion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (codigoPeticion.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "el campo del código de la petición debe estar completo",
                                "Formulario incompleto", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int nro = Integer.parseInt(codigoPeticion.getText());
                        ResultadoPeticionDTO resultadoPeticion = Controller.getControlador().sePuedeMostrarPeticion(nro);

                        switch (resultadoPeticion.getRetorno()) {
                            case 0:
                                JOptionPane.showMessageDialog(null, "No existe petición", "No existe petición",
                                        JOptionPane.WARNING_MESSAGE);
                                break;
                            case 1:
                                lblMensaje = new JLabel("Resultados de la petición:");
                                lblMensaje.setFont(new Font("Arial", Font.BOLD, 12));
                                contentPane.add(lblMensaje, BorderLayout.NORTH);

                                GridBagConstraints gbc = new GridBagConstraints();
                                gbc.anchor = GridBagConstraints.WEST;
                                gbc.insets = new Insets(5, 5, 5, 5);

                                PeticionMVC peticion = Controller.getControlador().mostrarPeticion();
                                String numeroPeticion = "Número de petición: " + peticion.getNumeroPeticion();
                                if (lblNroPeticion == null) {
                                    lblNroPeticion = new JLabel();
                                    gbc.gridx = 0;
                                    gbc.gridy = 1;
                                    formPanel.add(lblNroPeticion,gbc);
                                }
                                lblNroPeticion.setText(numeroPeticion);

                                String numeroSucursal = "Número de sucursal: " + peticion.getNumeroSucursal();
                                if (lblNroSucursal == null) {
                                    lblNroSucursal = new JLabel();
                                    gbc.gridx = 0;
                                    gbc.gridy = 2;
                                    formPanel.add(lblNroSucursal, gbc);
                                }
                                lblNroSucursal.setText(numeroSucursal);

                                String numeroPaciente = "Número de paciente: " + peticion.getNumeroPaciente();
                                if (lblNroPaciente == null) {
                                    lblNroPaciente = new JLabel();
                                    gbc.gridx = 0;
                                    gbc.gridy = 3;
                                    formPanel.add(lblNroPaciente, gbc);
                                }
                                lblNroPaciente.setText(numeroPaciente);

                                String nombrePaciente = "Nombre de paciente: " + peticion.getNombrePaciente();
                                if (lblNombrePaciente == null) {
                                    lblNombrePaciente = new JLabel();
                                    gbc.gridx = 0;
                                    gbc.gridy = 4;
                                    formPanel.add(lblNombrePaciente, gbc);
                                }
                                lblNombrePaciente.setText(nombrePaciente);

                                String obraSocial = "Obra social: " + peticion.getObraSocial();

                                if (lblObraSocial == null) {
                                    lblObraSocial = new JLabel();
                                    gbc.gridx = 0;
                                    gbc.gridy = 5;
                                    formPanel.add(lblObraSocial, gbc);
                                }
                                lblObraSocial.setText(obraSocial);

                                if (lblPracticas == null) {
                                    lblPracticas = new JLabel();
                                    gbc.gridx = 0;
                                    gbc.gridy = 6;
                                    formPanel.add(lblPracticas, gbc);
                                }

                                String[] columnNames = {"Práctica", "Valor del resultado"};
                                Object[][] data = new Object[resultadoPeticion.getResultados().size()][columnNames.length];

                                for (int i = 0; i < resultadoPeticion.getResultados().size(); i++) {
                                    Resultado resultado = resultadoPeticion.getResultados().get(i);
                                    Practica practica = resultadoPeticion.getPracticas().get(i);
                                    data[i][0] = practica.getNombre();
                                    data[i][1] = resultado.getValor();
                                }

                                GridBagConstraints gbcTable = new GridBagConstraints();
                                gbcTable.gridx = 0;
                                gbcTable.gridy = 6;
                                gbcTable.gridwidth = 2;
                                gbcTable.fill = GridBagConstraints.BOTH;
                                gbcTable.insets = new Insets(5, 5, 5, 5);

                                tablaDatos = new JTable(data, columnNames);
                                JScrollPane scrollPane = new JScrollPane(tablaDatos);
                                scrollPane.setBounds(10, 160, 400, 80);

                                tablePanel.add(tablaDatos, gbcTable);
                                formPanel.add(tablePanel, gbcTable);

                                // Agregar el formPanel y el tablePanel al contentPane
                                JPanel contentPane = new JPanel();
                                contentPane.setBackground(new Color(186, 246, 200));
                                contentPane.setLayout(new BorderLayout());
                                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                                contentPane.add(formPanel, BorderLayout.NORTH);
                                contentPane.add(tablePanel, BorderLayout.CENTER);
                                contentPane.add(buttonPanel, BorderLayout.SOUTH);
                                setContentPane(contentPane);

                                revalidate();
                                repaint();
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "Retirar por sucursal", "Retirar por sucursal",
                                        JOptionPane.INFORMATION_MESSAGE);
                                break;
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "no ingrese caracteres en los campos de solo números",
                            "Error caracter ingresado erróneamente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(formPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

}
