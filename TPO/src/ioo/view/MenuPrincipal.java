package ioo.view;

import ioo.view.Listas.ListaPacientes;
import ioo.view.Listas.ListaPeticiones;
import ioo.view.Listas.MostrarPeticion;
import ioo.view.Pacientes.CrearPaciente;
import ioo.view.Pacientes.EliminarPaciente;
import ioo.view.Pacientes.ModificarPaciente;
import ioo.view.Peticiones.CrearPeticion;
import ioo.view.Peticiones.EliminarPeticion;
import ioo.view.Resultado.CrearResultado;
import ioo.view.Sucursal.CrearSucursal;
import ioo.view.Sucursal.EliminarSucursal;
import ioo.view.Sucursal.ModificarSucursal;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import static javax.swing.UIManager.setLookAndFeel;

public class MenuPrincipal extends JFrame {

    private JPanel contentPane;

    @SneakyThrows
    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Sistema de Gestión");
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(186, 246, 200));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(186, 246, 200));
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Crear el menú "Pacientes"
        JMenu menuPacientes = new JMenu("Pacientes");
        menuPacientes.setFont(new Font("Calibri", Font.PLAIN, 14));
        menuBar.add(menuPacientes);

        // Agregar un elemento de menú al menú "Pacientes"
        JMenuItem menuItemConsultarPacientes = new JMenuItem("Consultar Lista de Pacientes");
        menuItemConsultarPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListaPacientes ventana = new ListaPacientes();
                ventana.setVisible(true);
            }
        });

        JMenuItem mntmNewMenuItem = new JMenuItem("Crear Paciente");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               CrearPaciente ventana = new CrearPaciente();
               ventana.setVisible(true);
            }
        });
        menuPacientes.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem2 = new JMenuItem("Eliminar Paciente");
        mntmNewMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarPaciente ventana = new EliminarPaciente();
                ventana.setVisible(true);
            }
        });
        menuPacientes.add(mntmNewMenuItem2);


        JMenuItem mntmNewMenuItem3 = new JMenuItem("Modificar Paciente");
        mntmNewMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModificarPaciente ventana = new ModificarPaciente();
                ventana.setVisible(true);
            }
        });
        menuPacientes.add(mntmNewMenuItem3);

        //Peticiones
        JMenu mnNewMenu_1 = new JMenu("Peticiones");
        mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu_1.setFont(new Font("Calibri", Font.PLAIN, 14));
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

        //Resultado
        JMenu mnNewMenu_4 = new JMenu("Resultados");
        mnNewMenu_4.setHorizontalAlignment(SwingConstants.CENTER);
        mnNewMenu_4.setFont(new Font("Calibri", Font.PLAIN, 14));
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
        mnNewMenu_3.setFont(new Font("Calibri", Font.PLAIN, 14));
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

        JMenuItem mntmNewMenuItem11 = new JMenuItem("Modificar Sucursal");
        mntmNewMenuItem11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModificarSucursal ventana = new ModificarSucursal();
                ventana.setVisible(true);
            }
        });
        mnNewMenu_3.add(mntmNewMenuItem11);

        JMenuItem mntmNewMenuItem12 = new JMenuItem("Eliminar Sucursal");
        mntmNewMenuItem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EliminarSucursal ventana = new EliminarSucursal();
                ventana.setVisible(true);
            }
        });
        mnNewMenu_3.add(mntmNewMenuItem12);



        JButton botonConsultarPacientes = new JButton("Consultar Lista de Pacientes");
        botonConsultarPacientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonConsultarPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListaPacientes ventana = new ListaPacientes();
                ventana.setVisible(true);
            }
        });

        Dimension botonSize = new Dimension(Integer.MAX_VALUE, 80);

        botonConsultarPacientes.setBounds(90, 50, 300, 45);
        panelBotones.add(botonConsultarPacientes);

        contentPane.add(panelBotones, BorderLayout.CENTER);


        JButton botonConsultarResultadoPeticionesResultadoCritico = new JButton("Consultar Peticiones con Resultados Críticos");
        botonConsultarResultadoPeticionesResultadoCritico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListaPeticiones ventana = new ListaPeticiones();
                ventana.setVisible(true);
            }
        });
        botonConsultarResultadoPeticionesResultadoCritico.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonConsultarResultadoPeticionesResultadoCritico.setBounds(65, 110, 350, 45);


        JButton botonMostrarPeticion = new JButton("Mostrar Peticion");
        botonMostrarPeticion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MostrarPeticion ventana = new MostrarPeticion();
                ventana.setVisible(true);
            }
        });
        botonMostrarPeticion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonMostrarPeticion.setBounds(90, 170, 300, 45);

        botonConsultarPacientes.setMaximumSize(botonSize);
        botonMostrarPeticion.setMaximumSize(botonSize);
        botonConsultarResultadoPeticionesResultadoCritico.setMaximumSize(botonSize);

        int espacioVertical = 20;
        panelBotones.add(botonMostrarPeticion);
        panelBotones.add(Box.createRigidArea(new Dimension(0, espacioVertical)));
        panelBotones.add(botonConsultarPacientes);
        panelBotones.add(Box.createRigidArea(new Dimension(0, espacioVertical)));
        panelBotones.add(botonConsultarResultadoPeticionesResultadoCritico);

        JButton botonSalida = new JButton("Salir del sistema");
        botonSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        contentPane.add(botonSalida, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        MenuPrincipal frame = new MenuPrincipal();
        frame.setVisible(true);
    }

}

