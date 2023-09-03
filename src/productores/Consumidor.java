/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
public class Consumidor extends Thread {
    private Buffer buffer;
    private int id;

    public Consumidor(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Intentar consumir un elemento del búfer
                int elemento = buffer.quitar();
                System.out.println("Consumidor " + id + " consumió: " + elemento);
                sleep(1000); // Simular tiempo de procesamiento
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
