package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Empleado;
import vallegrande.edu.pe.model.EmpleadoDAO;
import java.util.List;

// ============================================================
// PLANTILLA - CONTROLADOR (Capa MVC)
// Actúa como intermediario entre la Vista y el Modelo (DAO).
// La Vista NUNCA llama al DAO directamente, siempre pasa por aquí.
//
// Si cambias de tema, ajusta:
//   1. Nombre de la clase → MiEntidadController
//   2. Importa tu DAO     → MiEntidadDAO
//   3. Importa tu modelo  → MiEntidad
//   4. Parámetros de cada método → según los campos de tu entidad
// ============================================================
public class EmpleadoController {

    // El controlador instancia el DAO (única dependencia al modelo)
    private final EmpleadoDAO dao = new EmpleadoDAO();

    // ----------------------------------------------------------
    // Retorna la lista completa de empleados para mostrar en la JTable
    // Si cambias de tema → cambia el tipo de retorno List<MiEntidad>
    // ----------------------------------------------------------
    public List<Empleado> obtenerEmpleados() {
        return dao.listar();
    }

    // ----------------------------------------------------------
    // Recibe los datos del formulario (desde la Vista) y crea la entidad
    // Si cambias de tema → ajusta los parámetros y setters
    // ----------------------------------------------------------
    public void agregarEmpleado(String nombre, double salario,
                                String departamento, String turno, boolean activo) {
        Empleado e = new Empleado();
        e.setNombre(nombre);
        e.setSalario(salario);
        e.setDepartamento(departamento);
        e.setTurno(turno);
        e.setActivo(activo);
        dao.insertar(e);
    }

    // ----------------------------------------------------------
    // Recibe el ID de la fila seleccionada + nuevos datos del formulario
    // Si cambias de tema → ajusta los parámetros y setters
    // ----------------------------------------------------------
    public void modificarEmpleado(int id, String nombre, double salario,
                                  String departamento, String turno, boolean activo) {
        Empleado e = new Empleado();
        e.setId(id);
        e.setNombre(nombre);
        e.setSalario(salario);
        e.setDepartamento(departamento);
        e.setTurno(turno);
        e.setActivo(activo);
        dao.modificar(e);
    }

    // ----------------------------------------------------------
    // Elimina el empleado con el ID de la fila seleccionada
    // Si cambias de tema → el nombre del método cambia, el ID se mantiene
    // ----------------------------------------------------------
    public void eliminarEmpleado(int id) {
        dao.eliminar(id);
    }
}
