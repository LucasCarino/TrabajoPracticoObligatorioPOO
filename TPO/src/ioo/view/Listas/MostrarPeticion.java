//package ioo.view.Listas;
//
//import ioo.controller.Controller;
//import ioo.model.Paciente;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Locale;
//import java.util.Vector;
//
//// Referencia: https://www.tutorialspoint.com/swingexamples/create_table.htm
//
//// EL USUARIO FILTRA POR UN NUMERO DE PETICION Y SI ES CRITICO EL RESULTADO, DICE POR PANTALLA RETIRAR POR SUCURSAL. SI
//// NO ES CRITICO, MUESTRA LA INFO DE LA PETICION POR PANTALLA
//public class MostrarPeticion {
//
//    public class ConsultaPeticiones extends JFrame {
//        private JFrame mainFrame;
//        private JPanel contentPane;
//        private JTextField PeticionID;
//
//        private JScrollPane OLD_TABLE;
//
//        public ConsultaPeticionesResultadosCriticos() {
//            mainFrame = new JFrame("Consulta de Peticiones Resultados Críticos");
//            mainFrame.setSize(500, 400);
//            mainFrame.setLayout(new GridLayout(0, 1));
//
//            contentPane = new JPanel();
//            contentPane.setLayout(new FlowLayout());
//            setLocationRelativeTo(null);
//
//            JLabel lbPeticionID = new JLabel("ID de Peticion:");
//            lbPeticionID.setBounds(10, 5, 120, 28);
//            contentPane.add(lbPeticionID);
//
//            PeticionID = new JTextField();
//            PeticionID.setBounds(124, 11, 232, 20);
//            PeticionID.add(PeticionID);
//            PeticionID.setColumns(10);
//
//            JButton btnListarPeticionesValoresC = new JButton("Listar Peticiones Críticas");
//            btnListarPeticionesValoresC.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    Vector<Vector<String>> data;
//                    try {
//                        if (PeticionID.getText().equalsIgnoreCase("")) {
//                            JOptionPane.showMessageDialog(null, "el campo de nro de petición debe estar completo", "Formulario incompleto", JOptionPane.WARNING_MESSAGE);}
//                        else {
//
//
//                            int nro_peticion = Integer.parseInt(PeticionID.getText());
//                            data = Controller
//
//                            if (data == null) {
//                                JOptionPane.showMessageDialog(null, "La Fecha y/o proveedorId no son validos", "Datos invalidos", JOptionPane.WARNING_MESSAGE);
//                            } else {
//                                Vector<String> columnNames = new Vector<String>();
//                                columnNames.add("Id Factura");
//                                columnNames.add("Proveedor");
//                                columnNames.add("Dia");
//                                columnNames.add("Total A Pagar");
//                                columnNames.add("Fue Pagado?");
//
//                                if (OLD_TABLE != null) {
//                                    contentPane.remove(OLD_TABLE);
//                                }
//
//                                JTable table = new JTable(data, columnNames);
//                                JScrollPane scrollPane = new JScrollPane(table);
//                                OLD_TABLE = scrollPane;
//                                scrollPane.setSize(500, 500);
//                                contentPane.add(scrollPane);
//                                mainFrame.add(contentPane);
//                                mainFrame.setVisible(true);
//                            }
//                        } catch (ParseException ex) {
//                            JOptionPane.showMessageDialog(null, "Fecha mal formateada", "Error fecha ingresada erroneamente", JOptionPane.ERROR_MESSAGE);
//                        }
//                    }
//                });
//            btnListarPeticionesValoresC.setBounds(136, 80, 161, 23);
//            contentPane.add(btnListarPeticionesValoresC);
//
//            mainFrame.add(contentPane);
//            mainFrame.setVisible(true);
//            }
//        }
//
//        }
//    }
