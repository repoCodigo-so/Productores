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
import java.util.Queue;

public class Buffer {

    private Queue<Elemento> buffer; // Usamos una cola para implementar el búfer
    private int capacidad; // Capacidad máxima del búfer

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new LinkedList<>();
    }

    public synchronized void producir(Elemento elemento, Productor productor) throws InterruptedException {
        // Verificar si el búfer está lleno, si lo está, esperar
        while (buffer.size() >= capacidad) {
            productor.setEstado(EstadoProductor.ESPERANDO);
            wait();
        }

        buffer.offer(elemento); // Agregar el elemento al búfer
        productor.setEstado(EstadoProductor.PRODUCIENDO);
        notifyAll(); // Despertar a los consumidores si alguno está esperando
    }

    public synchronized Elemento consumir(Consumidor consumidor) throws InterruptedException {
        // Verificar si el búfer está vacío, si lo está, esperar
        while (buffer.isEmpty()) {
            consumidor.setEstado(EstadoConsumidor.ESPERANDO);
            wait();
        }

        Elemento elemento = buffer.poll(); // Tomar el elemento del búfer
        consumidor.setEstado(EstadoConsumidor.CONSUMIENDO);
        notifyAll(); // Despertar a los productores si alguno está esperando

        return elemento;
    }

    public int obtenerCapacidad() {
        return capacidad;
    }

    public int obtenerTamaño() {
        return buffer.size();
    }

    public synchronized Elemento[] obtenerContenido() {
        return buffer.toArray(new Elemento[0]);
    }
}
