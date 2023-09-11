package productores;

import javax.swing.SwingUtilities;

public class Consumidor implements Runnable {
    private Buffer buffer;
    private GUIRepresentation guiRepresentation;
    private int id;
    private EstadoConsumidor estado;
    private int tiempoConsumo; // Variable para almacenar el tiempo de consumo
    private boolean running;

    public Consumidor(int id, Buffer buffer, GUIRepresentation guiRepresentation) {
        this.id = id;
        this.buffer = buffer;
        this.guiRepresentation = guiRepresentation;
        this.estado = EstadoConsumidor.INACTIVO;
        this.tiempoConsumo = 1000; // Tiempo predeterminado de consumo (en milisegundos)
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                estado = EstadoConsumidor.ESPERANDO;
                Elemento elemento = buffer.consumir(this);
                estado = EstadoConsumidor.CONSUMIENDO;

                // Simula el proceso de consumo (aquí puedes agregar tu lógica)
                // En este ejemplo, simplemente dormimos al hilo durante el tiempo de consumo configurado.
                Thread.sleep(tiempoConsumo);

                // Actualiza la GUIRepresentation para reflejar el consumo y luego límpialo
                SwingUtilities.invokeLater(() -> {
                    guiRepresentation.consumirElemento(id, elemento);
                });

                // Limpia el estado en la GUI después de un tiempo
                Thread.sleep(1000);
                SwingUtilities.invokeLater(() -> {
                    guiRepresentation.limpiarConsumo(id);
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setEstado(EstadoConsumidor estado) {
        this.estado = estado;
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
