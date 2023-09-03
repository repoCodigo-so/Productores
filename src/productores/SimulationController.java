/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
import java.util.List; // Para utilizar List en la declaración de variables
import java.util.ArrayList; // Si estás usando ArrayList u otras implementaciones de List
import java.util.LinkedList; 

public class SimulationController {
    private Buffer buffer;
    private List<Productor> productores;
    private List<Consumidor> consumidores;
    private GUIRepresentation guiRepresentation;
    private boolean simulacionActiva;

    public SimulationController(Buffer buffer, List<Productor> productores, List<Consumidor> consumidores, GUIRepresentation guiRepresentation) {
        this.buffer = buffer;
        this.productores = productores;
        this.consumidores = consumidores;
        this.guiRepresentation = guiRepresentation;
        this.simulacionActiva = false;
    }

    public void iniciarSimulacion() {
        if (!simulacionActiva) {
            simulacionActiva = true;

            // Iniciar productores y consumidores
            for (Productor productor : productores) {
                productor.start();
            }

            for (Consumidor consumidor : consumidores) {
                consumidor.start();
            }

            // Actualizar la GUI
            actualizarGUI();
        }
    }

    public void detenerSimulacion() {
        if (simulacionActiva) {
            simulacionActiva = false;

            // Detener productores y consumidores
            for (Productor productor : productores) {
                productor.interrupt();
            }

            for (Consumidor consumidor : consumidores) {
                consumidor.interrupt();
            }

            // Actualizar la GUI
            actualizarGUI();
        }
    }

    public boolean isSimulacionActiva() {
        return simulacionActiva;
    }

    public void actualizarGUI() {
        List<Integer> contenidoBuffer = buffer.obtenerContenido();
        guiRepresentation.actualizarBuffer(contenidoBuffer);
    }
}
