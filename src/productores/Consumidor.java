package productores;

import java.awt.Color;
import javax.swing.SwingUtilities;

public class Consumidor implements Runnable {

    private Buffer buffer;
    private GUIRepresentation guiRepresentation;
    private MainGUI mainGUI; // Nueva referencia a MainGUI
    private int id;
    private EstadoConsumidor estado;
    private int tiempoConsumo; // Variable para almacenar el tiempo de consumo
    private boolean running;
    private int contadorElementosConsumidos = 0;

    public Consumidor(int id, Buffer buffer, GUIRepresentation guiRepresentation, MainGUI mainGUI) {
        this.id = id;
        this.buffer = buffer;
        this.guiRepresentation = guiRepresentation;
        this.mainGUI = mainGUI; // Asigna la referencia a MainGUI
        this.estado = EstadoConsumidor.INACTIVO;
        this.tiempoConsumo = 300; // Tiempo predeterminado de consumo (en milisegundos)
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                estado = EstadoConsumidor.ESPERANDO;
                Elemento elemento = buffer.consumir(this);
                estado = EstadoConsumidor.CONSUMIENDO;

                // Actualiza la GUIRepresentation para reflejar el consumo a través de MainGUI
                String mensaje = "Consumidor #" + id + " - Consumiendo id: " + elemento.getId() + " - Consumiendo contenido: " + elemento.getContenido();
                mainGUI.agregarElementoALista(mensaje);

                contadorElementosConsumidos++;

                // Actualiza el contador de elementos consumidos en MainGUI
                mainGUI.actualizarContadorConsumidos();
        
                mainGUI.actualizarContadorEnBuffer(buffer.obtenerTamano());
                
                mainGUI.elementoConsumido(id);
                // Simula el proceso de consumo (aquí puedes agregar tu lógica)
                // En este ejemplo, simplemente dormimos al hilo durante el tiempo de consumo configurado.
                Thread.sleep(tiempoConsumo);
                // Limpia el consumo a través de MainGUI
                // mainGUI.limpiarListaElementos();
                mainGUI.regresarEstado(id);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setEstado(EstadoConsumidor estado) {
        this.estado = estado;
        guiRepresentation.cambiarColorBotonConsumidor(id, estado.getColor());
    }

    public EstadoConsumidor getEstado() {
        return estado;
    }

    public int getId() {
        return id;
    }

    // Método para cambiar el tiempo de consumo
    public void cambiarTiempoConsumo(int nuevoTiempo) {
        // Verifica que el nuevo tiempo sea válido (por ejemplo, no negativo)
        if (nuevoTiempo >= 0) {
            this.tiempoConsumo = nuevoTiempo;
        } else {
            // En caso de que el nuevo tiempo no sea válido, puedes manejarlo de acuerdo a tus necesidades
            System.out.println("Error: El tiempo de consumo debe ser un valor no negativo.");
        }
    }

    // Método para detener el consumidor
    public void detener() {
        running = false;
    }

    // Método para obtener el tiempo de consumo actual
    public int getTiempoConsumo() {
        return tiempoConsumo;
    }
}
