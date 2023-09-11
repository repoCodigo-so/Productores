package productores;

import java.util.Random;

public class Productor implements Runnable {
    private int id;
    private Buffer buffer;
    private GUIRepresentation guiRepresentation;
    private MainGUI mainGUI; // Nueva referencia a MainGUI
    private int tiempoProduccion;
    private boolean running;
    private Random random;
    private EstadoProductor estado;

    public Productor(int id, Buffer buffer, GUIRepresentation guiRepresentation, MainGUI mainGUI) {
        this.id = id;
        this.buffer = buffer;
        this.guiRepresentation = guiRepresentation;
        this.mainGUI = mainGUI; // Asigna la referencia a MainGUI
        this.tiempoProduccion = 1000; // Tiempo predeterminado de producción (en milisegundos)
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

                // Agrega el elemento a la lista de la interfaz gráfica a través de MainGUI
                mainGUI.agregarElementoALista("Elemento #" + id + " - Produciendo: " + elemento.getContenido());

                // Espera un tiempo aleatorio antes de producir el siguiente elemento
                int tiempoAleatorio = random.nextInt(3000) + 1000; // Tiempo entre 1000 y 4000 ms
                Thread.sleep(tiempoAleatorio);
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
