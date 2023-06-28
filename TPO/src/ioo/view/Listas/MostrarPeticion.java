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
        setTitle("Mostrar petición");

        setBounds(100, 100, 442, 327);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Table.selectionBackground"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lbPeticionId = new JLabel("ID Petición:");
        lbPeticionId.setBounds(10, 7, 150, 14);
        contentPane.add(lbPeticionId);

        codigoPeticion = new JTextField();
        codigoPeticion.setBounds(175, 5, 120, 20);
        contentPane.add(codigoPeticion);
        codigoPeticion.setColumns(10);

        JButton btnBuscarPeticion = new JButton("Buscar petición");
        btnBuscarPeticion.setBounds(0, 266, 130, 20);
        contentPane.add(btnBuscarPeticion);

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
                                PeticionMVC peticion = Controller.getControlador().mostrarPeticion();
                                String numeroPeticion = "Número de petición: " + peticion.getNumeroPeticion();
                                if (lblNroPeticion == null) {
                                    lblNroPeticion = new JLabel();
                                    lblNroPeticion.setBounds(10, 30, 200, 20);
                                    getContentPane().add(lblNroPeticion);
                                }
                                lblNroPeticion.setText(numeroPeticion);

                                String numeroSucursal = "Número de sucursal: " + peticion.getNumeroSucursal();
                                if (lblNroSucursal == null) {
                                    lblNroSucursal = new JLabel();
                                    lblNroSucursal.setBounds(10, 50, 400, 20);
                                    getContentPane().add(lblNroSucursal);
                                }
                                lblNroSucursal.setText(numeroSucursal);

                                String numeroPaciente = "Número de paciente: " + peticion.getNumeroPaciente();
                                if (lblNroPaciente == null) {
                                    lblNroPaciente = new JLabel();
                                    lblNroPaciente.setBounds(10, 70, 200, 20);
                                    getContentPane().add(lblNroPaciente);
                                }
                                lblNroPaciente.setText(numeroPaciente);

                                String nombrePaciente = "Nombre de paciente: " + peticion.getNombrePaciente();
                                if (lblNombrePaciente == null) {
                                    lblNombrePaciente = new JLabel();
                                    lblNombrePaciente.setBounds(10, 90, 200, 20);
                                    getContentPane().add(lblNombrePaciente);
                                }
                                lblNombrePaciente.setText(nombrePaciente);

                                String obraSocial = "Obra social: " + peticion.getObraSocial();

                                if (lblObraSocial == null) {
                                    lblObraSocial = new JLabel();
                                    lblObraSocial.setBounds(10, 110, 200, 20);
                                    getContentPane().add(lblObraSocial);
                                }
                                lblObraSocial.setText(obraSocial);

                                if (lblPracticas == null) {
                                    lblPracticas = new JLabel();
                                    lblPracticas.setBounds(10, 130, 200, 20);
                                    getContentPane().add(lblPracticas);
                                }

                                String[] columnNames = {"Práctica", "Valor del resultado"};
                                Object[][] data = new Object[resultadoPeticion.getResultados().size()][columnNames.length];

                                for (int i = 0; i < resultadoPeticion.getResultados().size(); i++) {
                                    Resultado resultado = resultadoPeticion.getResultados().get(i);
                                    Practica practica = resultadoPeticion.getPracticas().get(i);
                                    data[i][0] = practica.getNombre();
                                    data[i][1] = resultado.getValor();
                                }

                                lblMensaje = new JLabel("Resultados de la petición:");
                                lblMensaje.setFont(new Font("Arial", Font.BOLD, 12));
                                contentPane.add(lblMensaje, BorderLayout.NORTH);
                                lblMensaje.setBounds(10, 130, 200, 20);
                                getContentPane().add(lblMensaje);

                                tablaDatos = new JTable(data, columnNames);
                                JScrollPane scrollPane = new JScrollPane(tablaDatos);
                                scrollPane.setBounds(10, 160, 400, 80);
                                contentPane.add(scrollPane, BorderLayout.CENTER);

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
    }

}
