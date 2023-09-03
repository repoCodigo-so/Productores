/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
import java.util.Random;

public class Productor extends Thread {
    private Buffer buffer;
    private int id;
    private Random random = new Random();

    public Productor(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Generar un elemento
                int elemento = random.nextInt(100);
                // Intentar colocar el elemento en el búfer
                buffer.agregar(elemento);
                System.out.println("Productor " + id + " produjo: " + elemento);
                sleep(random.nextInt(1000)); // Simular tiempo de producción variable
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
