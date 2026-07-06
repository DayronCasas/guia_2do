package vallegrande.edu.pe;

import vallegrande.edu.pe.view.InicioView;
import javax.swing.SwingUtilities;

// ============================================================
// PLANTILLA - PUNTO DE ENTRADA DE LA APLICACIÓN
// Si cambias de tema, NO necesitas modificar nada aquí.
// InicioView es quien crea el controlador y abre la vista principal.
// ============================================================
public class App {
    public static void main(String[] args) {
        // Inicia la UI en el hilo de eventos de Swing (buena práctica obligatoria)
        SwingUtilities.invokeLater(() -> new InicioView().setVisible(true));
    }
}

