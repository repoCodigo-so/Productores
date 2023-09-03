/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
import java.util.LinkedList;
import java.util.List;

public class Buffer {
    private LinkedList<Integer> elementos; // Cambia el tipo si tus elementos son de otro tipo

    public Buffer(int capacidad) {
        this.elementos = new LinkedList<>();
    }

    // Método para agregar un elemento al búfer
    public synchronized void agregar(int elemento) throws InterruptedException {
        // Implementa la lógica para agregar un elemento al búfer
    }

    // Método para quitar un elemento del búfer
    public synchronized int quitar() throws InterruptedException {
        // Implementa la lógica para quitar un elemento del búfer
        return 0; // Cambia esto para devolver el elemento real
    }

    // Método para obtener el contenido del búfer
    public synchronized List<Integer> obtenerContenido() {
        return new LinkedList<>(elementos); // Devuelve una copia de la lista de elementos
    }
}
