/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;

public class SimulationController {

    private Buffer buffer;
    private List<Productor> productores;
    private List<Consumidor> consumidores;
    private GUIRepresentation guiRepresentation;
    private boolean simulacionActiva;

    public SimulationController(int bufferSize, int numProductores, int numConsumidores, GUIRepresentation guiRepresentation) {
        this.buffer = new Buffer(bufferSize);
        this.productores = new ArrayList<>();
        this.consumidores = new ArrayList<>();
        this.guiRepresentation = guiRepresentation;
        this.simulacionActiva = false;

        // Crear productores iniciales
        for (int i = 0; i < numProductores; i++) {
            agregarProductor();
        }

        // Crear consumidores iniciales
        for (int i = 0; i < numConsumidores; i++) {
            agregarConsumidor();
        }
    }

    public void iniciarSimulacion() {
        simulacionActiva = true;
        startProductores();
        startConsumidores();
    }

    public void detenerSimulacion() {
        simulacionActiva = false;
        stopProductores();
        stopConsumidores();
    }

    public boolean isSimulacionActiva() {
        return simulacionActiva;
    }

    public void agregarProductor() {
        if (!simulacionActiva) {
            int id = productores.size() + 1;
            Productor productor = new Productor(id, buffer, guiRepresentation);
            productores.add(productor);
            guiRepresentation.agregarBotonProductor(id, productor.getTiempoProduccion());
        }
    }

    public void eliminarProductor() {
        if (!simulacionActiva && !productores.isEmpty()) {
            Productor productor = productores.remove(productores.size() - 1);
            guiRepresentation.eliminarBotonProductor(productor.getId());
        }
    }

    public void agregarConsumidor() {
        if (!simulacionActiva) {
            int id = consumidores.size() + 1;
            Consumidor consumidor = new Consumidor(id, buffer, guiRepresentation);
            consumidores.add(consumidor);
            guiRepresentation.agregarBotonConsumidor(id, consumidor.getTiempoConsumo());
        }
    }

    public void eliminarConsumidor() {
        if (!simulacionActiva && !consumidores.isEmpty()) {
            Consumidor consumidor = consumidores.remove(consumidores.size() - 1);
            guiRepresentation.eliminarBotonConsumidor(consumidor.getId());
        }
    }

    public boolean hayProductores() {
        return !productores.isEmpty();
    }

    public boolean hayConsumidores() {
        return !consumidores.isEmpty();
    }

    // Cambiar el tiempo de producción de un productor
    public void cambiarTiempoProductor(int id, int nuevoTiempo) {
        for (Productor productor : productores) {
            if (productor.getId() == id) {
                productor.cambiarTiempoProduccion(nuevoTiempo);
                break;
            }
        }
    }

    // Cambiar el tiempo de consumo de un consumidor
    public void cambiarTiempoConsumidor(int id, int nuevoTiempo) {
        for (Consumidor consumidor : consumidores) {
            if (consumidor.getId() == id) {
                consumidor.cambiarTiempoConsumo(nuevoTiempo);
                break;
            }
        }
    }

    private void startProductores() {
        for (Productor productor : productores) {
            Thread thread = new Thread(productor);
            thread.start();
        }
    }

    private void stopProductores() {
        for (Productor productor : productores) {
            productor.detener();
        }
    }

    private void startConsumidores() {
        for (Consumidor consumidor : consumidores) {
            Thread thread = new Thread(consumidor);
            thread.start();
        }
    }

    private void stopConsumidores() {
        for (Consumidor consumidor : consumidores) {
            consumidor.detener();
        }
    }
}
