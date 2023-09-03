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

public class Main {
    public static void main(String[] args) {
        // Crear un búfer compartido
        Buffer buffer = new Buffer(10); // Cambia la capacidad según tus necesidades

        // Crear una lista de productores y consumidores
        List<Productor> productores = new ArrayList<>();
        List<Consumidor> consumidores = new ArrayList<>();

        // Crear e inicializar los productores y consumidores según tus necesidades
        for (int i = 0; i < 2; i++) {
            Productor productor = new Productor(buffer, i);
            Consumidor consumidor = new Consumidor(buffer, i);
            productores.add(productor);
            consumidores.add(consumidor);
        }

        // Crear la representación gráfica
        GUIRepresentation guiRepresentation = new GUIRepresentation(buffer.obtenerContenido());

        // Crear el controlador de la simulación
        SimulationController controller = new SimulationController(buffer, productores, consumidores, guiRepresentation);

        // Crear y mostrar la interfaz gráfica principal
        MainGUI mainGUI = new MainGUI(controller);
        mainGUI.mostrarVentana();
    }
}