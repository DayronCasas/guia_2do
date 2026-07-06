package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.EmpleadoController;

import javax.swing.*;
import java.awt.*;

// ============================================================
// PLANTILLA - VISTA DE INICIO (Pantalla de bienvenida)
// Si cambias de tema, ajusta:
//   - El título de la ventana  (setTitle)
//   - El texto del JLabel      (titulo, descripcion)
//   - El texto del botón       (btnEmpleados)
//   - La vista que abre        (new EmpleadoView → new MiEntidadView)
//   - El controlador que crea  (new EmpleadoController → new MiController)
// ============================================================
public class InicioView extends JFrame {

    public InicioView() {
        // --- CAMBIA: título de la ventana ---
        setTitle("Sistema de Gestión de Personal");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // JLabel obligatorio - Título principal
        // --- CAMBIA: texto del título ---
        JLabel titulo = new JLabel("BIENVENIDO", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(0, 120, 215));
        panel.add(titulo, BorderLayout.NORTH);

        // JLabel obligatorio - Descripción del sistema
        // --- CAMBIA: descripción según tu tema ---
        JLabel descripcion = new JLabel(
                "<html><div style='text-align:center;'>"
                + "Sistema de gestión de <b>empleados</b><br>"
                + "Patrón MVC · Java Swing · MySQL AWS RDS"
                + "</div></html>",
                JLabel.CENTER
        );
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // JButton obligatorio - Navegación a la vista principal
        // --- CAMBIA: texto del botón y la vista que abre ---
        JButton btnEmpleados = new JButton("Gestionar Empleados");
        btnEmpleados.setFocusPainted(false);
        btnEmpleados.setBackground(new Color(0, 120, 215));
        btnEmpleados.setForeground(Color.WHITE);
        btnEmpleados.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Crea el controlador e inyecta en la vista principal
        btnEmpleados.addActionListener(e -> {
            EmpleadoController controller = new EmpleadoController(); // <-- cambia si cambias de tema
            new EmpleadoView(controller).setVisible(true);            // <-- cambia si cambias de tema
            dispose();
        });

        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 15));
        centro.setBackground(Color.WHITE);
        centro.add(descripcion);
        centro.add(btnEmpleados);

        panel.add(centro, BorderLayout.CENTER);
        add(panel);
    }
}
