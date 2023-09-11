package productores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainGUI {
    private final JFrame ventana;
    private final JButton btnIniciar;
    private final JButton btnDetener;
    private final JButton btnAgregarProductor;
    private final JButton btnEliminarProductor;
    private final JButton btnAgregarConsumidor;
    private final JButton btnEliminarConsumidor;
    private final DefaultListModel<String> elementosListModel;
    private final JList<String> elementosList;
    private final JScrollPane elementosScrollPane;
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

        // Botones para agregar y eliminar productores y consumidores
        btnAgregarProductor = new JButton("Agregar Productor");
        btnEliminarProductor = new JButton("Eliminar Productor");
        btnAgregarConsumidor = new JButton("Agregar Consumidor");
        btnEliminarConsumidor = new JButton("Eliminar Consumidor");

        btnAgregarProductor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProductor();
            }
        });

        btnEliminarProductor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarProductor();
            }
        });

        btnAgregarConsumidor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarConsumidor();
            }
        });

        btnEliminarConsumidor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarConsumidor();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panelBotones.add(btnAgregarProductor);
        panelBotones.add(btnEliminarProductor);
        panelBotones.add(btnAgregarConsumidor);
        panelBotones.add(btnEliminarConsumidor);

        // Lista para mostrar los elementos
        elementosListModel = new DefaultListModel<>();
        elementosList = new JList<>(elementosListModel);
        elementosScrollPane = new JScrollPane(elementosList);

        ventana.setLayout(new BorderLayout());
        ventana.add(panelBotones, BorderLayout.NORTH);
        ventana.add(elementosScrollPane, BorderLayout.CENTER);

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

    private void agregarProductor() {
        controller.agregarProductor();
        actualizarEstadoBotones();
    }

    private void eliminarProductor() {
        controller.eliminarProductor();
        actualizarEstadoBotones();
    }

    private void agregarConsumidor() {
        controller.agregarConsumidor();
        actualizarEstadoBotones();
    }

    private void eliminarConsumidor() {
        controller.eliminarConsumidor();
        actualizarEstadoBotones();
    }

    private void actualizarEstadoBotones() {
        btnIniciar.setEnabled(!controller.isSimulacionActiva());
        btnDetener.setEnabled(controller.isSimulacionActiva());
        btnAgregarProductor.setEnabled(!controller.isSimulacionActiva());
        btnEliminarProductor.setEnabled(!controller.isSimulacionActiva() && controller.hayProductores());
        btnAgregarConsumidor.setEnabled(!controller.isSimulacionActiva());
        btnEliminarConsumidor.setEnabled(!controller.isSimulacionActiva() && controller.hayConsumidores());
    }

    public void agregarElementoALista(String elemento) {
        elementosListModel.addElement(elemento);
    }

    public void elementoProducido(int id, Elemento elemento) {
        String mensaje = "Elemento #" + id + " - Produciendo: " + elemento.getContenido();
        agregarElementoALista(mensaje);
    }

    public void elementoConsumido(int id, Elemento elemento) {
        String mensaje = "Elemento #" + id + " - Consumiendo: " + elemento.getContenido();
        agregarElementoALista(mensaje);
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); // Cambia la capacidad según tus necesidades
        List<Productor> productores = new ArrayList<>();
        List<Consumidor> consumidores = new ArrayList<>();
        GUIRepresentation guiRepresentation = new GUIRepresentation(buffer.obtenerContenido());
        SimulationController controller = new SimulationController(buffer, productores, consumidores, guiRepresentation, null);

        MainGUI mainGUI = new MainGUI(controller, guiRepresentation);
        controller.setMainGUI(mainGUI);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainGUI.mostrarVentana();
            }
        });
    }
}
