package productores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIRepresentation {

    private final JFrame frame;
    private final JPanel panel;
    private final JButton[] buttons;
    private final JLabel[] labels;
    private final Color[] colors;
    private final Elemento[] elementos;
    private final Elemento[] elementosProducidos;
    private final Elemento[] elementosConsumidos;
    private final JButton btnReiniciarGUI;
    private final DefaultListModel<String> elementosListModel;
    private SimulationController controller;
    private final ArrayList<JButton> productorButtons;
    private final ArrayList<JButton> consumidorButtons;

    public GUIRepresentation(Elemento[] elementos) {
        this.elementos = elementos;
        int numElementos = elementos.length;
        elementosProducidos = new Elemento[numElementos];
        elementosConsumidos = new Elemento[numElementos];
        frame = new JFrame("Representación de la Simulación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(numElementos + 2, 3));

        buttons = new JButton[numElementos];
        labels = new JLabel[numElementos];
        colors = new Color[numElementos];

        elementosListModel = new DefaultListModel<>();

        productorButtons = new ArrayList<>();
        consumidorButtons = new ArrayList<>();

        for (int i = 0; i < numElementos; i++) {
            labels[i] = new JLabel("Elemento #" + elementos[i].getId());
            buttons[i] = new JButton("Consumir");
            buttons[i].setBackground(Color.WHITE);
            colors[i] = Color.WHITE;

            final int id = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    consumirElemento(id);
                }
            });

            panel.add(labels[i]);
            panel.add(buttons[i]);
        }

        btnReiniciarGUI = new JButton("Reiniciar GUI");
        btnReiniciarGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarGUI();
            }
        });

        panel.add(btnReiniciarGUI);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void setController(SimulationController controller) {
        this.controller = controller;
    }

    public void producirElemento(int id, Elemento elemento) {
        elementosProducidos[id] = elemento;
        SwingUtilities.invokeLater(() -> {
            labels[id].setText("Elemento #" + elementos[id].getId() + " - Contenido: " + elemento.getContenido());
            buttons[id].setBackground(Color.GREEN);
            colors[id] = Color.GREEN;
            elementosListModel.addElement("Elemento #" + elementos[id].getId() + " - Contenido: " + elemento.getContenido());
        });
    }

    public void consumirElemento(int id) {
        SwingUtilities.invokeLater(() -> {
            if (id >= 0 && id < buttons.length) {
                if (elementosProducidos[id] != null) {
                    elementosConsumidos[id] = elementosProducidos[id];
                    elementosProducidos[id] = null;
                    labels[id].setText("Elemento #" + elementos[id].getId() + " - Consumido: " + elementosConsumidos[id].getContenido());
                    elementosListModel.addElement("Elemento #" + elementos[id].getId() + " - Consumido: " + elementosConsumidos[id].getContenido());
                }
                buttons[id].setBackground(Color.YELLOW);
                colors[id] = Color.YELLOW;
            }
        });
    }

    public void limpiarConsumo(int id) {
        SwingUtilities.invokeLater(() -> {
            if (id >= 0 && id < buttons.length) {
                elementosConsumidos[id] = null;
                labels[id].setText("Elemento #" + elementos[id].getId());
                buttons[id].setBackground(Color.WHITE);
                colors[id] = Color.WHITE;
            }
        });
    }

    public void limpiarConsumo() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < buttons.length; i++) {
                elementosConsumidos[i] = null;
                labels[i].setText("Elemento #" + elementos[i].getId());
                buttons[i].setBackground(Color.WHITE);
                colors[i] = Color.WHITE;
            }
        });
    }

    public void reiniciarGUI() {
        frame.dispose();
        String input = JOptionPane.showInputDialog("Ingrese el tamaño del búfer:");

        int bufferSize = 10;
        try {
            bufferSize = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Se utilizará el tamaño predeterminado (10).");
        }

        Buffer buffer = new Buffer(bufferSize);
        java.util.List<Productor> productores = new ArrayList<>();
        java.util.List<Consumidor> consumidores = new ArrayList<>();
        GUIRepresentation guiRepresentation = new GUIRepresentation(buffer.obtenerContenido());
        SimulationController controller = new SimulationController(buffer, productores, consumidores, guiRepresentation, null);

        MainGUI mainGUI = new MainGUI(controller, guiRepresentation);
        controller.setMainGUI(mainGUI);
        mainGUI.mostrarVentana();
    }

    public void eliminarBotonProductor(int id) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().startsWith("Productor " + id)) {
                    panel.remove(button);
                    productorButtons.remove(button);
                    frame.pack();
                    break;
                }
            }
        }
    }

    public void agregarBotonProductor(int id, int tiempo) {
        JButton botonProductor = new JButton("Productor " + id + " - Tiempo: " + tiempo);
        botonProductor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Nuevo tiempo de producción para el Productor " + id + ":");
                try {
                    int nuevoTiempo = Integer.parseInt(input);
                    if (controller != null) {
                        controller.cambiarTiempoProduccion(id, nuevoTiempo);
                    }
                    botonProductor.setText("Productor " + id + " - Tiempo: " + nuevoTiempo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada no válida. El tiempo de producción no se ha modificado.");
                }
            }
        });
        
        productorButtons.add(botonProductor);
        panel.add(botonProductor);
        frame.pack();
    }

    public void agregarBotonConsumidor(int id, int tiempo) {
        JButton botonConsumidor = new JButton("Consumidor " + id + " - Tiempo: " + tiempo);
        botonConsumidor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Nuevo tiempo de consumo para el Consumidor " + id + ":");
                try {
                    int nuevoTiempo = Integer.parseInt(input);
                    if (controller != null) {
                        controller.cambiarTiempoConsumidor(id, nuevoTiempo);
                    }
                    botonConsumidor.setText("Consumidor " + id + " - Tiempo: " + nuevoTiempo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada no válida. El tiempo de consumo no se ha modificado.");
                }
            }
        });
        
        consumidorButtons.add(botonConsumidor);
        panel.add(botonConsumidor);
        frame.pack();
    }

    public void eliminarBotonConsumidor(int id) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().startsWith("Consumidor " + id)) {
                    panel.remove(button);
                    consumidorButtons.remove(button);
                    frame.pack();
                    break;
                }
            }
        }
    }

    public void cambiarColorBotonProductor(int id, Color color) {
        if (id >= 0 && id < productorButtons.size()) {
            SwingUtilities.invokeLater(() -> {
                productorButtons.get(id).setBackground(color);
            });
        }
    }

    public void cambiarColorBotonConsumidor(int id, Color color) {
        if (id >= 0 && id < consumidorButtons.size()) {
            SwingUtilities.invokeLater(() -> {
                consumidorButtons.get(id).setBackground(color);
            });
        }
    }

    public Color[] getColors() {
        return colors;
    }
}
