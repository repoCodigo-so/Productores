package productores;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        // Preguntar al usuario el tamaño del búfer
        String input = JOptionPane.showInputDialog("Ingrese el tamaño del búfer:");
        
        // Validar la entrada del usuario y crear el búfer con el tamaño especificado
        int bufferSize = 10; // Valor predeterminado
        try {
            bufferSize = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Se utilizará el tamaño predeterminado (10).");
        }
        
        Buffer buffer = new Buffer(bufferSize); // Tamaño del búfer según la entrada del usuario o valor predeterminado
        List<Productor> productores = new ArrayList<>();
        List<Consumidor> consumidores = new ArrayList<>();
        GUIRepresentation guiRepresentation = new GUIRepresentation(buffer.obtenerContenido());
        SimulationController controller = new SimulationController(buffer, productores, consumidores, guiRepresentation, null);

        MainGUI mainGUI = new MainGUI(controller, guiRepresentation);
        controller.setMainGUI(mainGUI);
        mainGUI.mostrarVentana();
    }
}


