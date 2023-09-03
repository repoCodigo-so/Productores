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
import java.util.List;

public class GUIRepresentation extends JPanel {
    private List<Integer> buffer; // Cambia el tipo de datos si tu búfer almacena elementos de otro tipo

    public GUIRepresentation(List<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja el contenido del búfer en la GUI
        int x = 50;
        int y = 50;
        int elementWidth = 30;
        int elementHeight = 30;

        for (Integer elemento : buffer) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, elementWidth, elementHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, elementWidth, elementHeight);
            g.drawString(elemento.toString(), x + 10, y + 20);

            x += 40; // Alinea los elementos horizontalmente
        }
    }

    public void actualizarBuffer(List<Integer> nuevoBuffer) {
        this.buffer = nuevoBuffer;
        repaint(); // Vuelve a dibujar la representación gráfica con el nuevo contenido
    }
}
