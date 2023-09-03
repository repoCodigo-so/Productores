/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class MainGUI {
    private JFrame ventana;
    private JButton btnIniciar;
    private JButton btnDetener;
    private SimulationController controller;

    public MainGUI(SimulationController controller) {
        this.controller = controller;

        ventana = new JFrame("Simulación de Productor-Consumidor");
        ventana.setSize(400, 150);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnIniciar = new JButton("Iniciar Simulación");
        btnDetener = new JButton("Detener Simulación");

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });

        btnDetener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detenerSimulacion();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);

        ventana.setLayout(new BorderLayout());
        ventana.add(panelBotones, BorderLayout.CENTER);

        actualizarEstadoBotones();
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }

    private void iniciarSimulacion() {
        controller.iniciarSimulacion();
        actualizarEstadoBotones();
    }

    private void detenerSimulacion() {
        controller.detenerSimulacion();
        actualizarEstadoBotones();
    }

    private void actualizarEstadoBotones() {
        btnIniciar.setEnabled(!controller.isSimulacionActiva());
        btnDetener.setEnabled(controller.isSimulacionActiva());
    }

    public static void main(String[] args) {
        // Crea un controlador de simulación y una instancia de MainGUI
        Buffer buffer = new Buffer(10); // Cambia la capacidad según tus necesidades
        // Crea productores y consumidores según tus necesidades
        List<Productor> productores = new ArrayList<>();
        List<Consumidor> consumidores = new ArrayList<>();
        GUIRepresentation guiRepresentation = new GUIRepresentation(buffer.obtenerContenido());
        SimulationController controller = new SimulationController(buffer, productores, consumidores, guiRepresentation);
        MainGUI mainGUI = new MainGUI(controller);

        // Muestra la ventana principal
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainGUI.mostrarVentana();
            }
        });
    }
}
