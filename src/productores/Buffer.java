package productores;

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

        // Notificar a todos los hilos que están esperando (en caso de que haya consumidores esperando)
        notifyAll();
    }

    public synchronized Elemento consumir(Consumidor consumidor) throws InterruptedException {
        // Verificar si el búfer está vacío, si lo está, esperar
        while (buffer.isEmpty()) {
            consumidor.setEstado(EstadoConsumidor.ESPERANDO);
            wait();
        }

        Elemento elemento = buffer.poll(); // Tomar el elemento del búfer
        consumidor.setEstado(EstadoConsumidor.CONSUMIENDO);

        // Notificar a todos los hilos que están esperando (en caso de que haya productores esperando)
        notifyAll();

        return elemento;
    }

    public int obtenerCapacidad() {
        return capacidad;
    }

    public int obtenerTamano() {
        return buffer.size();
    }

    public synchronized Elemento[] obtenerContenido() {
        // Convertir la cola de elementos en un arreglo
        Elemento[] elementos = new Elemento[buffer.size()];
        buffer.toArray(elementos);
        return elementos;
    }
}
