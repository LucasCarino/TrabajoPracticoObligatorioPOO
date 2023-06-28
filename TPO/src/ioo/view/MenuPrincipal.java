package ioo.view;

import ioo.view.Listas.ListaPacientes;
import ioo.view.Listas.MostrarPeticion;
import ioo.view.Pacientes.CrearPaciente;
import ioo.view.Pacientes.EliminarPaciente;
import ioo.view.Pacientes.ModificarPaciente;
import ioo.view.Peticiones.CrearPeticion;
import ioo.view.Peticiones.EliminarPeticion;
import ioo.view.Practicas.CrearPractica;
import ioo.view.Practicas.EliminarPractica;
import ioo.view.Resultado.CrearResultado;
import ioo.view.Sucursal.CrearSucursal;
import ioo.view.Sucursal.EliminarSucursal;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.UIManager.setLookAndFeel;

public class MenuPrincipal extends JFrame {

    private JPanel contentPane;

    @SneakyThrows
    public MenuPrincipal() {
        setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        setTitle("Sistema de Gestión de Laboratorio");
        setResizable(false);
        setBounds(100, 100, 476, 310);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("null"));
        contentPane.setToolTipText("");
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0, 185)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 470, 24);
        contentPane.add(menuBar);

        //Paciente
        JMenu mnNewMenu = new JMenu("Pacientes");
        mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu.setFont(new Font("Calibri", Font.PLAIN, 16));
        mnNewMenu.setBackground(Color.darkGray);
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Crear Paciente");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               CrearPaciente ventana = new CrearPaciente();
               ventana.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem2 = new JMenuItem("Eliminar Paciente");
        mntmNewMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarPaciente ventana = new EliminarPaciente();
                ventana.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem2);


        JMenuItem mntmNewMenuItem3 = new JMenuItem("Modificar Paciente");
        mntmNewMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModificarPaciente ventana = new ModificarPaciente();
                ventana.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem3);

        //Peticiones
        JMenu mnNewMenu_1 = new JMenu("Peticiones");
        mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu_1.setFont(new Font("Calibri", Font.PLAIN, 16));
        mnNewMenu_1.setBackground(Color.gray);
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem4 = new JMenuItem("Crear Petición");
        mntmNewMenuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearPeticion ventana = new CrearPeticion();
                ventana.setVisible(true);

            }
        });
        mnNewMenu_1.add(mntmNewMenuItem4);

        JMenuItem mntmNewMenuItem6 = new JMenuItem("Eliminar Petición");
        mntmNewMenuItem6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarPeticion ventana = new EliminarPeticion();
                ventana.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem6);

        JMenuItem mntmNewMenuItem5 = new JMenuItem("Modificar Petición");
        mntmNewMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ModificarPetición ventana = new ModificarPetición();
//                ventana.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem5);;

        //Prácticas
        JMenu mnNewMenu_2 = new JMenu("Prácticas");
        mnNewMenu_2.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu_2.setFont(new Font("Calibri", Font.PLAIN, 16));
        mnNewMenu_2.setBackground(Color.gray);
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem7 = new JMenuItem("Crear Práctica");
        mntmNewMenuItem7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               CrearPractica ventana = new CrearPractica();
               ventana.setVisible(true);

            }
        });
        mnNewMenu_2.add(mntmNewMenuItem7);

        JMenuItem mntmNewMenuItem9 = new JMenuItem("Eliminar Práctica");
        mntmNewMenuItem9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarPractica ventana = new EliminarPractica();
                ventana.setVisible(true);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem9);

        JMenuItem mntmNewMenuItem8 = new JMenuItem("Modificar Práctica");
        mntmNewMenuItem9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ModificarPractica ventana = new ModificarPractica();
//                ventana.setVisible(true);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem8);

        //Resultado
        JMenu mnNewMenu_4 = new JMenu("Resultados");
        mnNewMenu_4.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu_4.setFont(new Font("Calibri", Font.PLAIN, 16));
        mnNewMenu_4.setBackground(Color.gray);
        menuBar.add(mnNewMenu_4);

        JMenuItem mntmNewMenuItem13 = new JMenuItem("Crear Resultado");
        mntmNewMenuItem13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearResultado ventana = new CrearResultado();
                ventana.setVisible(true);
            }
        });
        mnNewMenu_4.add(mntmNewMenuItem13);

        //Sucursales
        JMenu mnNewMenu_3 = new JMenu("Sucursales");
        mnNewMenu_3.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu_3.setFont(new Font("Calibri", Font.PLAIN, 16));
        mnNewMenu_3.setBackground(Color.gray);
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem10 = new JMenuItem("Crear Sucursal");
        mntmNewMenuItem10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearSucursal ventana = new CrearSucursal();
                ventana.setVisible(true);

            }
        });
        mnNewMenu_3.add(mntmNewMenuItem10);

        JMenuItem mntmNewMenuItem12 = new JMenuItem("Eliminar Sucursal");
        mntmNewMenuItem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarSucursal ventana = new EliminarSucursal();
                ventana.setVisible(true);
            }
        });
        mnNewMenu_3.add(mntmNewMenuItem12);

        JMenuItem mntmNewMenuItem11 = new JMenuItem("Modificar Sucursal");
        mntmNewMenuItem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ModificarSucursal ventana = new ModificarSucursal();
//                ventana.setVisible(true);
            }
        });
        mnNewMenu_3.add(mntmNewMenuItem11);


        JButton botonSalida = new JButton("Salir del sistema");
        botonSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        botonSalida.setBounds(321, 247, 149, 23);
        contentPane.add(botonSalida);

        JButton botonConsultarPacientes = new JButton("Consultar Lista de Pacientes");
        botonConsultarPacientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonConsultarPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListaPacientes ventana = new ListaPacientes();
                ventana.setVisible(true);
            }
        });
        botonConsultarPacientes.setBounds(90, 50, 300, 45);
        contentPane.add(botonConsultarPacientes);

        JButton botonConsultarResultadoPeticionesResultadoCritico = new JButton("Consultar Peticiones con Resultados Críticos");
        botonConsultarResultadoPeticionesResultadoCritico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ListarPeticionesconValoresCriticos ventana = new ListarPeticionesconValoresCriticos();
//                ventana.setVisible(true);
            }
        });
        botonConsultarResultadoPeticionesResultadoCritico.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonConsultarResultadoPeticionesResultadoCritico.setBounds(90, 110, 300, 45);
        contentPane.add(botonConsultarResultadoPeticionesResultadoCritico);

        JButton MostrarPeticion = new JButton("Mostrar Peticion");
        MostrarPeticion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MostrarPeticion ventana = new MostrarPeticion();
                ventana.setVisible(true);

            }
        });
        MostrarPeticion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        MostrarPeticion.setBounds(90, 170, 300, 45);
        contentPane.add(MostrarPeticion);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        MenuPrincipal frame = new MenuPrincipal();
        frame.setVisible(true);
    }

}

