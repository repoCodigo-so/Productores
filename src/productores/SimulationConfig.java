/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
public class SimulationConfig {
    private int capacidadBuffer;
    private int numProductores;
    private int numConsumidores;
    private int velocidadProductores; // En milisegundos
    private int velocidadConsumidores; // En milisegundos

    public SimulationConfig() {
        // Valores predeterminados
        capacidadBuffer = 10;
        numProductores = 2;
        numConsumidores = 2;
        velocidadProductores = 1000;
        velocidadConsumidores = 1000;
    }

    public int getCapacidadBuffer() {
        return capacidadBuffer;
    }

    public void setCapacidadBuffer(int capacidadBuffer) {
        this.capacidadBuffer = capacidadBuffer;
    }

    public int getNumProductores() {
        return numProductores;
    }

    public void setNumProductores(int numProductores) {
        this.numProductores = numProductores;
    }

    public int getNumConsumidores() {
        return numConsumidores;
    }

    public void setNumConsumidores(int numConsumidores) {
        this.numConsumidores = numConsumidores;
    }

    public int getVelocidadProductores() {
        return velocidadProductores;
    }

    public void setVelocidadProductores(int velocidadProductores) {
        this.velocidadProductores = velocidadProductores;
    }

    public int getVelocidadConsumidores() {
        return velocidadConsumidores;
    }

    public void setVelocidadConsumidores(int velocidadConsumidores) {
        this.velocidadConsumidores = velocidadConsumidores;
    }
}