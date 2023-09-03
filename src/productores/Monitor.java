/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productores;

/**
 *
 * @author User
 */
public class Monitor {
    private int count = 0;

    public synchronized void increment() throws InterruptedException {
        while (count >= 10) {
            // Espera si el contador está en su límite
            wait();
        }
        count++;
        notifyAll(); // Notifica a los consumidores que un elemento está disponible
    }

    public synchronized void decrement() throws InterruptedException {
        while (count <= 0) {
            // Espera si el contador está en su límite inferior
            wait();
        }
        count--;
        notifyAll(); // Notifica a los productores que hay espacio disponible
    }
}
