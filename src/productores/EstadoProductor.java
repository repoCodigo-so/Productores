/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

import java.awt.Color;

/**
 *
 * @author User
 */
public enum EstadoProductor {
    ESPERANDO("Esperando", Color.YELLOW),
    PRODUCIENDO("Produciendo", Color.GREEN);

    private final String nombre;
    private final Color color;

    EstadoProductor(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
