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
    private JLabel lblElementosProducidos;
    private JLabel lblElementosConsumidos;
    private JLabel lblElementosEnBuffer;
    private int contadorElementosProducidos;
    private int contadorElementosConsumidos;

    public MainGUI(SimulationController controller, GUIRepresentation guiRepresentation) {
        this.controller = controller;
        this.guiRepresentation = guiRepresentation;
        contadorElementosProducidos = 0;
        contadorElementosConsumidos = 0;

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

        // Etiquetas para contar elementos producidos, consumidos y en el búfer
        lblElementosProducidos = new JLabel("Elementos Producidos: 0");
        lblElementosConsumidos = new JLabel("Elementos Consumidos: 0");
        lblElementosEnBuffer = new JLabel("Elementos en Búfer: 0");

        // Agregar las etiquetas al panel de botones
        panelBotones.add(lblElementosProducidos);
        panelBotones.add(lblElementosConsumidos);
        panelBotones.add(lblElementosEnBuffer);

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
        contadorElementosProducidos++;
        actualizarContadorProducidos(contadorElementosProducidos);
    }

    public void elementoConsumido(int id, Elemento elemento) {
        String mensaje = "Elemento #" + id + " - Consumiendo: " + elemento.getContenido();
        agregarElementoALista(mensaje);
        contadorElementosConsumidos++;
        actualizarContadorConsumidos(contadorElementosConsumidos);
    }

    public void actualizarContadorProducidos(int contador) {
        lblElementosProducidos.setText("Elementos Producidos: " + contador);
    }

    public void actualizarContadorConsumidos(int contador) {
        lblElementosConsumidos.setText("Elementos Consumidos: " + contador);
    }

    public void actualizarContadorEnBuffer(int contador) {
        lblElementosEnBuffer.setText("Elementos en Búfer: " + contador);
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Ingrese el tamaño del búfer:");
        
        // Validar la entrada del usuario y crear el búfer con el tamaño especificado
        int bufferSize = 10; // Valor predeterminado
        try {
            bufferSize = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Se utilizará el tamaño predeterminado (10).");
        }
        
        Buffer buffer = new Buffer(bufferSize); // Tamaño del búfer según la entrada del usuario o valor predeterminado
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
