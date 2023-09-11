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
public enum EstadoConsumidor {
    ESPERANDO("Esperando", Color.YELLOW),
    INACTIVO("Inactivo", Color.YELLOW),
    CONSUMIENDO("Consumiendo", Color.GREEN); // Cambio aquí

    private final String nombre;
    private final Color color;

    EstadoConsumidor(String nombre, Color color) { // Cambio aquí
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
