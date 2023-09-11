/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
public class Elemento {
    private int id; // Un identificador único para el elemento
    private String contenido; // Puedes personalizar el contenido según tus necesidades

    public Elemento(int id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "Elemento #" + id + ": " + contenido;
    }
}
