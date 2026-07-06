package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.EmpleadoController;
import vallegrande.edu.pe.model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// ============================================================
// PLANTILLA - VISTA PRINCIPAL (Formulario + Tabla CRUD)
// Contiene TODOS los componentes Swing obligatorios:
//   JLabel, JTextField, JRadioButton, JComboBox,
//   JCheckBox, JTable, JButton
//
// Si cambias de tema, ajusta las secciones marcadas con:
//   // --- CAMBIA: ...
// ============================================================
public class EmpleadoView extends JFrame {

    // --- Componentes de tabla ---
    private final JTable             tabla;
    private final DefaultTableModel  modeloTabla;

    // --- Componentes del formulario (campos obligatorios) ---
    private final JTextField  txtNombre;      // JTextField obligatorio
    private final JTextField  txtSalario;     // JTextField obligatorio (numérico)
    private final JComboBox<String> cbDepartamento; // JComboBox obligatorio
    private final JRadioButton rbManana;            // JRadioButton obligatorio
    private final JRadioButton rbTarde;             // JRadioButton obligatorio
    private final ButtonGroup  grupTurno;
    private final JCheckBox    chkActivo;           // JCheckBox obligatorio

    // Referencia al controlador (inyectado por App.java)
    private final EmpleadoController controller;

    public EmpleadoView(EmpleadoController controller) {
        this.controller = controller;

        // --- CAMBIA: título de la ventana ---
        setTitle("Mantenimiento de Empleados - Valle Grande");
        setSize(920, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel raíz con márgenes
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // -------------------------------------------------------
        // NORTE: JLabel obligatorio - Título de la pantalla
        // --- CAMBIA: texto del título ---
        // -------------------------------------------------------
        JLabel titulo = new JLabel("GESTIÓN Y LISTADO DE EMPLEADOS", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 120, 215));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // -------------------------------------------------------
        // OESTE: Formulario con todos los componentes obligatorios
        // -------------------------------------------------------
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 10));
        panelFormulario.setBackground(Color.WHITE);
        // --- CAMBIA: título del borde del formulario ---
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));

        // JTextField obligatorio - campo de texto principal
        // --- CAMBIA: etiqueta "Nombre:" según tu entidad ---
        txtNombre  = new JTextField(12);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        // JTextField obligatorio - campo numérico
        // --- CAMBIA: etiqueta "Salario:" según tu entidad (ej. "Precio:", "Nota:") ---
        txtSalario = new JTextField(8);
        panelFormulario.add(new JLabel("Salario (S/.):"));
        panelFormulario.add(txtSalario);

        // JComboBox obligatorio - lista de categorías / áreas
        // --- CAMBIA: etiqueta y opciones según tu entidad ---
        cbDepartamento = new JComboBox<>(new String[]{
                "Sistemas", "Contabilidad", "Logística", "Recursos Humanos"
        });
        panelFormulario.add(new JLabel("Departamento:"));
        panelFormulario.add(cbDepartamento);

        // JRadioButton obligatorio - opciones excluyentes
        // --- CAMBIA: etiqueta y textos de las opciones ---
        rbManana = new JRadioButton("Mañana", true);
        rbTarde  = new JRadioButton("Tarde");
        rbManana.setBackground(Color.WHITE);
        rbTarde.setBackground(Color.WHITE);
        grupTurno = new ButtonGroup();
        grupTurno.add(rbManana);
        grupTurno.add(rbTarde);

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelRadio.setBackground(Color.WHITE);
        panelRadio.add(rbManana);
        panelRadio.add(rbTarde);

        panelFormulario.add(new JLabel("Turno:"));
        panelFormulario.add(panelRadio);

        // JCheckBox obligatorio - estado booleano
        // --- CAMBIA: texto del checkbox según tu entidad ---
        chkActivo = new JCheckBox("Empleado Activo");
        chkActivo.setBackground(Color.WHITE);
        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(chkActivo);

        // Espaciadores para completar el GridLayout (7 filas x 2 cols)
        panelFormulario.add(new JLabel());
        panelFormulario.add(new JLabel());
        panelFormulario.add(new JLabel());
        panelFormulario.add(new JLabel());

        panelPrincipal.add(panelFormulario, BorderLayout.WEST);

        // -------------------------------------------------------
        // CENTRO: JTable obligatoria con encabezado coloreado
        // --- CAMBIA: nombres de columnas según tu entidad ---
        // -------------------------------------------------------
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Salario", "Departamento", "Turno", "Activo"}, 0
        ) {
            // Las celdas no son editables directamente en la tabla
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Encabezado con color institucional
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0, 120, 215));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setOpaque(true);
        tabla.getTableHeader().setDefaultRenderer(headerRenderer);

        JScrollPane scroll = new JScrollPane(tabla);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // -------------------------------------------------------
        // SUR: JButton obligatorios - Acciones CRUD + Volver
        // --- CAMBIA: textos de botones si quieres ---
        // -------------------------------------------------------
        JButton btnAgregar   = crearBoton("Agregar",   new Color(40, 167, 69));
        JButton btnModificar = crearBoton("Modificar", new Color(255, 153, 0));
        JButton btnEliminar  = crearBoton("Eliminar",  new Color(220, 53, 69));
        JButton btnVolver    = crearBoton("Volver",    new Color(108, 117, 125));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // -------------------------------------------------------
        // EVENTOS DE BOTONES
        // -------------------------------------------------------

        // AGREGAR: lee el formulario y llama al controlador
        btnAgregar.addActionListener(e -> {
            try {
                // --- CAMBIA: los campos leídos según tu entidad ---
                String  nombre       = txtNombre.getText().trim();
                double  salario      = Double.parseDouble(txtSalario.getText().trim());
                String  departamento = cbDepartamento.getSelectedItem().toString();
                String  turno        = rbManana.isSelected() ? "Mañana" : "Tarde";
                boolean activo       = chkActivo.isSelected();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
                    return;
                }

                controller.agregarEmpleado(nombre, salario, departamento, turno, activo);
                recargarTabla();
                limpiarFormulario();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un salario numérico válido.");
            }
        });

        // MODIFICAR: lee la fila seleccionada y actualiza con los datos del formulario
        btnModificar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar.");
                return;
            }
            try {
                int     id           = (int) modeloTabla.getValueAt(fila, 0);
                // --- CAMBIA: los campos leídos según tu entidad ---
                String  nombre       = txtNombre.getText().trim();
                double  salario      = Double.parseDouble(txtSalario.getText().trim());
                String  departamento = cbDepartamento.getSelectedItem().toString();
                String  turno        = rbManana.isSelected() ? "Mañana" : "Tarde";
                boolean activo       = chkActivo.isSelected();

                controller.modificarEmpleado(id, nombre, salario, departamento, turno, activo);
                recargarTabla();
                limpiarFormulario();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese datos válidos para modificar.");
            }
        });

        // ELIMINAR: elimina el registro de la fila seleccionada
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
                return;
            }
            int opcion = JOptionPane.showConfirmDialog(
                    this, "¿Está seguro de eliminar este empleado?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                int id = (int) modeloTabla.getValueAt(fila, 0);
                controller.eliminarEmpleado(id);
                recargarTabla();
                limpiarFormulario();
            }
        });

        // SELECCIÓN DE FILA: auto-completa todos los componentes del formulario
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    // --- CAMBIA: columnas e índices según tus campos ---
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtSalario.setText(modeloTabla.getValueAt(fila, 2).toString());
                    cbDepartamento.setSelectedItem(modeloTabla.getValueAt(fila, 3).toString());

                    String turno = modeloTabla.getValueAt(fila, 4).toString();
                    rbManana.setSelected(turno.equals("Mañana"));
                    rbTarde.setSelected(turno.equals("Tarde"));

                    String activoStr = modeloTabla.getValueAt(fila, 5).toString();
                    chkActivo.setSelected(activoStr.equals("Sí"));
                }
            }
        });

        // VOLVER: regresa a la pantalla de inicio
        btnVolver.addActionListener(e -> {
            new InicioView().setVisible(true);
            dispose();
        });

        // Carga inicial de datos al abrir la ventana
        recargarTabla();
    }

    // -------------------------------------------------------
    // Recarga la JTable consultando todos los registros al controlador
    // --- CAMBIA: controller.obtenerEmpleados() → controller.obtenerMiEntidades()
    // -------------------------------------------------------
    private void recargarTabla() {
        modeloTabla.setRowCount(0);
        List<Empleado> lista = controller.obtenerEmpleados(); // <-- cambia si cambias de tema
        for (Empleado emp : lista) {
            // --- CAMBIA: getters según los campos de tu entidad ---
            modeloTabla.addRow(new Object[]{
                    emp.getId(),
                    emp.getNombre(),
                    String.format("%.2f", emp.getSalario()),
                    emp.getDepartamento(),
                    emp.getTurno(),
                    emp.isActivo() ? "Sí" : "No"
            });
        }
    }

    // -------------------------------------------------------
    // Limpia todos los campos del formulario tras una acción CRUD
    // --- CAMBIA: reinicia los valores por defecto de cada componente
    // -------------------------------------------------------
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtSalario.setText("");
        cbDepartamento.setSelectedIndex(0);
        rbManana.setSelected(true);
        chkActivo.setSelected(false);
        tabla.clearSelection();
    }

    // -------------------------------------------------------
    // Utilidad: crea un JButton con color de fondo personalizado
    // No necesitas cambiar este método entre temas
    // -------------------------------------------------------
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return btn;
    }
}
