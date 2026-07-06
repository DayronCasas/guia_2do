package vallegrande.edu.pe.model;

// ============================================================
// PLANTILLA - MODELO (Entidad)
// Si cambias de tema, renombra esta clase y ajusta los campos:
//   - id          → siempre se mantiene (clave primaria)
//   - nombre      → campo de texto principal  (JTextField)
//   - salario      → campo numérico            (JTextField)
//   - departamento → campo de lista            (JComboBox)
//   - turno        → campo de opciones únicas  (JRadioButton)
//   - activo       → campo booleano            (JCheckBox)
//
// Ejemplo de otros temas:
//   Alumno   : nombre, nota,    carrera,      turno,    matriculado
//   Libro    : titulo, precio,  genero,       estado,   disponible
//   Paciente : nombre, edad,    especialidad, turno,    activo
// ============================================================
public class Empleado {

    // --- CAMPOS: cambia nombres y tipos según tu entidad ---
    private int    id;
    private String nombre;       // JTextField
    private double salario;      // JTextField  (numérico)
    private String departamento; // JComboBox   (lista de opciones)
    private String turno;        // JRadioButton (opciones excluyentes)
    private boolean activo;      // JCheckBox   (booleano)

    // Constructor vacío (requerido por el DAO al usar setters)
    public Empleado() {}

    // Constructor completo (usado en el DAO al leer ResultSet)
    public Empleado(int id, String nombre, double salario,
                    String departamento, String turno, boolean activo) {
        this.id          = id;
        this.nombre      = nombre;
        this.salario     = salario;
        this.departamento = departamento;
        this.turno       = turno;
        this.activo      = activo;
    }

    // --- GETTERS ---
    public int    getId()            { return id; }
    public String getNombre()        { return nombre; }
    public double getSalario()       { return salario; }
    public String getDepartamento()  { return departamento; }
    public String getTurno()         { return turno; }
    public boolean isActivo()        { return activo; }

    // --- SETTERS ---
    public void setId(int id)                    { this.id = id; }
    public void setNombre(String nombre)         { this.nombre = nombre; }
    public void setSalario(double salario)       { this.salario = salario; }
    public void setDepartamento(String dep)      { this.departamento = dep; }
    public void setTurno(String turno)           { this.turno = turno; }
    public void setActivo(boolean activo)        { this.activo = activo; }
}
