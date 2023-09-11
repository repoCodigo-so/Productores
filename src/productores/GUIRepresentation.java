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

public class GUIRepresentation {

    private final JFrame frame;
    private final JPanel panel;
    private final JButton[] buttons;
    private final JLabel[] labels;
    private final Color[] colors;
    private final Elemento[] elementos;
    private final JButton btnLimpiarConsumo;
    private final JButton btnReiniciarGUI;

    public GUIRepresentation(Elemento[] elementos) {
        this.elementos = elementos;
        int numElementos = elementos.length;
        frame = new JFrame("Representación de la Simulación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(numElementos + 2, 3)); // Tres columnas por elemento

        buttons = new JButton[numElementos];
        labels = new JLabel[numElementos];
        colors = new Color[numElementos];

        for (int i = 0; i < numElementos; i++) {
            labels[i] = new JLabel("Elemento #" + elementos[i].getId());
            buttons[i] = new JButton("Consumir");
            buttons[i].setBackground(Color.WHITE);
            colors[i] = Color.WHITE;

            final int id = i; // ID del elemento actual
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    consumirElemento(id);
                }
            });

            panel.add(labels[i]);
            panel.add(buttons[i]);
        }

        btnLimpiarConsumo = new JButton("Limpiar Consumo");
        btnLimpiarConsumo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarConsumo();
            }
        });

        btnReiniciarGUI = new JButton("Reiniciar GUI");
        btnReiniciarGUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarGUI();
            }
        });

        panel.add(btnLimpiarConsumo);
        panel.add(btnReiniciarGUI);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void consumirElemento(int id) {
        SwingUtilities.invokeLater(() -> {
            if (id >= 0 && id < buttons.length) {
                buttons[id].setBackground(Color.YELLOW);
                colors[id] = Color.YELLOW;
            }
        });
    }

    public void consumirElemento(int id, Elemento elemento) {
        SwingUtilities.invokeLater(() -> {
            if (id >= 0 && id < buttons.length) {
                buttons[id].setBackground(Color.YELLOW);
                colors[id] = Color.YELLOW;
            }
        });
    }

    public void limpiarConsumo(int id) {
        SwingUtilities.invokeLater(() -> {
            if (id >= 0 && id < buttons.length) {
                buttons[id].setBackground(Color.WHITE);
                colors[id] = Color.WHITE;
            }
        });
    }

    public void limpiarConsumo() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setBackground(Color.WHITE);
                colors[i] = Color.WHITE;
            }
        });
    }

    public void reiniciarGUI() {
        SwingUtilities.invokeLater(() -> {
            // Limpia el consumo de elementos (reinicia los colores)
            limpiarConsumo();

            // Restaura cualquier otro estado de la GUI a su estado original
            // Por ejemplo, si tienes botones, etiquetas u otros componentes que deban reiniciarse,
            // puedes hacerlo aquí.
            // Ejemplo de restauración de estado de botones:
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setEnabled(true); // Habilita todos los botones
            }

            // Puedes agregar más lógica de restauración según tus necesidades.
            // Asegúrate de que la interfaz esté actualizada y lista para su uso.
            frame.pack();
        });
    }

    public void agregarBotonProductor(int id, int tiempo) {
        JButton botonProductor = new JButton("Productor " + id + " - Tiempo: " + tiempo);
        botonProductor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para cambiar el tiempo del productor si es necesario.
            }
        });
        panel.add(botonProductor);
        frame.pack();
    }

    public void eliminarBotonProductor(int id) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().startsWith("Productor " + id)) {
                    panel.remove(button);
                    frame.pack();
                    break;
                }
            }
        }
    }

    public void agregarBotonConsumidor(int id, int tiempo) {
        JButton botonConsumidor = new JButton("Consumidor " + id + " - Tiempo: " + tiempo);
        botonConsumidor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para cambiar el tiempo del consumidor si es necesario.
            }
        });
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
                    frame.pack();
                    break;
                }
            }
        }
    }

    public void cambiarColorBotonProductor(int id, Color color) {
        if (id >= 0 && id < buttons.length) {
            buttons[id].setBackground(color);
            colors[id] = color;
        }
    }

    public void cambiarColorBotonConsumidor(int id, Color color) {
        if (id >= 0 && id < buttons.length) {
            buttons[id].setBackground(color);
            colors[id] = color;
        }
    }

    public Color[] getColors() {
        return colors;
    }
}
