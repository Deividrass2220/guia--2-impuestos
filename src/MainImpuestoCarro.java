import controller.ControladorImpuestoCarro;
import view.VistaImpuestoCarro;
import javax.swing.SwingUtilities;

/**
 * Clase principal del Sistema de Cálculo de Impuestos de Vehículos
 * Implementa el patrón de diseño MVC (Modelo-Vista-Controlador)
 *
 * Esta aplicación calcula el impuesto del vehículo con base en:
 * - Marca y modelo del vehículo
 * - Año de fabricación (antigüedad)
 * - Cilindraje (capacidad del motor)
 * - Avalúo comercial
 * - Tipo de uso (particular/público)
 */
public class MainImpuestoCarro {

    public static void main(String[] args) {
        // Usando el look and feel por defecto de Swing

        // Crear la GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                crearYMostrarGUI();
            }
        });
    }

    /**
     * Crea y muestra la ventana principal de la aplicación
     * Siguiendo el patrón MVC:
     * 1. Crear la Vista (VistaImpuestoCarro)
     * 2. Crear el Controlador (ControladorImpuestoCarro)
     * 3. El Controlador gestiona la interacción entre la Vista y el Modelo
     */
    private static void crearYMostrarGUI() {
        try {
            // Crear la vista
            VistaImpuestoCarro vista = new VistaImpuestoCarro();

            // Crear el controlador y pasarle la vista
            ControladorImpuestoCarro controlador = new ControladorImpuestoCarro(vista);

            // Iniciar la aplicación
            controlador.iniciar();

            System.out.println("¡Sistema de Cálculo de Impuestos de Vehículos iniciado correctamente!");
            System.out.println("Arquitectura MVC:");
            System.out.println("- Modelo: Vehiculo.java, CalculadoraImpuestos.java");
            System.out.println("- Vista: VistaImpuestoCarro.java");
            System.out.println("- Controlador: ControladorImpuestoCarro.java");

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
