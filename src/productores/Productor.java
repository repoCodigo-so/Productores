package productores;

import java.awt.Color;
import java.util.Random;
import javax.swing.SwingUtilities;

public class Productor implements Runnable {
    private int id;
    private Buffer buffer;
    private GUIRepresentation guiRepresentation;
    private MainGUI mainGUI; // Nueva referencia a MainGUI
    private int tiempoProduccion;
    private boolean running;
    private Random random;
    private EstadoProductor estado;
    private int contadorElementosProducidos = 0;

    public Productor(int id, Buffer buffer, GUIRepresentation guiRepresentation, MainGUI mainGUI) {
        this.id = id;
        this.buffer = buffer;
        this.guiRepresentation = guiRepresentation;
        this.mainGUI = mainGUI; // Asigna la referencia a MainGUI
        this.tiempoProduccion = 300; // Tiempo predeterminado de producción (en milisegundos)
        this.running = true;
        this.random = new Random();
        this.estado = EstadoProductor.ESPERANDO;
    }

    public void run() {
        while (running) {
            try {
                // Producción de un elemento
                Elemento elemento = new Elemento(id, "Elemento #" + id);

                // Cambia el color del botón del productor a PRODUCIENDO
                setEstado(EstadoProductor.PRODUCIENDO);

                // Agrega el elemento al búfer
                buffer.producir(elemento, this);

                // Cambia el color del botón del productor a ESPERANDO
                setEstado(EstadoProductor.ESPERANDO);

                // Actualiza el contador de elementos consumidos en MainGUI
                mainGUI.actualizarContadorProducidos();
                // Agrega el elemento a la lista de la interfaz gráfica a través de MainGUI
                mainGUI.agregarElementoALista("Productor #" + id + " - Producciendo id: " + elemento.getId() + " - Produciendo contenido: " + elemento.getContenido());
                
                mainGUI.actualizarContadorEnBuffer(buffer.obtenerTamano());
                
                //mainGUI.elementoProducido(id);

                Thread.sleep(tiempoProduccion);
                
                mainGUI.regresarEstado(id);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void detener() {
        running = false;
    }

    public int getId() {
        return id;
    }

    public int getTiempoProduccion() {
        return tiempoProduccion;
    }

    public void setTiempoProduccion(int tiempoProduccion) {
        this.tiempoProduccion = tiempoProduccion;
    }

    public void cambiarTiempoProduccion(int nuevoTiempo) {
        // Verifica que el nuevo tiempo sea válido (por ejemplo, no negativo)
        if (nuevoTiempo >= 0) {
            this.tiempoProduccion = nuevoTiempo;
        } else {
            // En caso de que el nuevo tiempo no sea válido, puedes manejarlo de acuerdo a tus necesidades
            System.out.println("Error: El tiempo de consumo debe ser un valor no negativo.");
        }
    }

    public EstadoProductor getEstado() {
        return estado;
    }

    public void setEstado(EstadoProductor estado) {
        this.estado = estado;
        guiRepresentation.cambiarColorBotonProductor(id, estado.getColor());
    }
}
