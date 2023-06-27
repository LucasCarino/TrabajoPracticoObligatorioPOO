package ioo.view;

import ioo.controller.Controller;
import ioo.model.Peticion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

// Referencia: https://www.tutorialspoint.com/swingexamples/create_table.htm

// LISTA TODAS LAS PETICIONES CON RESULTADO CRITICO
public class PeticionesconResultadosCriticos {

    public class ConsultaPeticiones extends JFrame {
        private JFrame mainFrame;
        private JPanel contentPane;
        private JScrollPane OLD_TABLE;

        public ConsultaPeticionesResultadosCriticos() {
            mainFrame = new JFrame("Consulta de Peticiones Resultados Críticos");
            mainFrame.setSize(500, 400);
            mainFrame.setLayout(new GridLayout(0, 1));

            contentPane = new JPanel();
            contentPane.setLayout(new FlowLayout());
            setLocationRelativeTo(null);

            JButton btnListarPeticionesValoresC = new JButton("Listar Peticiones Críticas");
            btnListarPeticionesValoresC.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    Vector<Vector<String>> data;


                        String[] columnNames = {"Name", "Salary"};

                        Controller.getControlador().listarPeticionesConValoresCriticos();

                        JTable table = new JTable(data, columnNames);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setSize(500, 500);
                        table.setFillsViewportHeight(true);
                        controlPanel.add(scrollPane);
                        mainFrame.setVisible(true);

                        if (OLD_TABLE != null) {
                                contentPane.remove(OLD_TABLE);
                            }
                        }
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Fecha mal formateada", "Error fecha ingresada erroneamente", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            btnListarPeticionesValoresC.setBounds(136, 80, 161, 23);
            contentPane.add(btnListarPeticionesValoresC);

            mainFrame.add(contentPane);
            mainFrame.setVisible(true);
        }
    }

}
