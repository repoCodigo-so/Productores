/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private final JFrame ventana;
    private final JButton btnIniciar;
    private final JButton btnDetener;
    private final JButton btnReiniciarGUI;
    private final JPanel productoresPanel;
    private final JPanel consumidoresPanel;
    private final SimulationController controller;
    private final GUIRepresentation guiRepresentation;

    public MainGUI(SimulationController controller, GUIRepresentation guiRepresentation) {
        this.controller = controller;
        this.guiRepresentation = guiRepresentation;

        ventana = new JFrame("Simulación de Productor-Consumidor");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Botones para iniciar y detener la simulación
        btnIniciar = new JButton("Iniciar Simulación");
        btnDetener = new JButton("Detener Simulación");
        btnReiniciarGUI = new JButton("Reiniciar GUI");

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

        btnReiniciarGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarGUI();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panelBotones.add(btnReiniciarGUI);

        // Paneles para mostrar productores y consumidores
        productoresPanel = new JPanel();
        consumidoresPanel = new JPanel();

        JScrollPane productoresScrollPane = new JScrollPane(productoresPanel);
        JScrollPane consumidoresScrollPane = new JScrollPane(consumidoresPanel);

        ventana.setLayout(new BorderLayout());
        ventana.add(panelBotones, BorderLayout.NORTH);
        ventana.add(productoresScrollPane, BorderLayout.WEST);
        ventana.add(consumidoresScrollPane, BorderLayout.EAST);

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

    private void reiniciarGUI() {
        guiRepresentation.limpiarConsumo();
    }

    // Actualiza el estado de los botones según la simulación
    private void actualizarEstadoBotones() {
        btnIniciar.setEnabled(!controller.isSimulacionActiva());
        btnDetener.setEnabled(controller.isSimulacionActiva());
        btnReiniciarGUI.setEnabled(!controller.isSimulacionActiva());
    }

    public static void main(String[] args) {
        // Dialogs for inputting the buffer size, number of consumers, and number of producers
        int bufferSize = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del buffer:"));
        int numConsumers = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de Consumidores:"));
        int numProducers = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de Productores:"));

        // Create a simulation controller with the specified parameters
        Buffer buffer = new Buffer(bufferSize);
        GUIRepresentation guiRepresentation = new GUIRepresentation(buffer.obtenerContenido());
        SimulationController controller = new SimulationController(bufferSize, numConsumers, numProducers, guiRepresentation);

        // Create an instance of MainGUI and pass the GUIRepresentation
        MainGUI mainGUI = new MainGUI(controller, guiRepresentation);

        // Show the main window
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainGUI.mostrarVentana();
            }
        });
    }
}
