package vallegrande.edu.pe.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// PLANTILLA - DAO (Data Access Object)
// Contiene todo el SQL que toca la base de datos.
// Si cambias de tema, ajusta:
//   1. El nombre de la clase  → MiEntidadDAO
//   2. El nombre de la tabla  → "mi_tabla" en cada consulta SQL
//   3. Las columnas SQL       → según los campos de tu entidad
//   4. El tipo de retorno     → List<MiEntidad>, MiEntidad
//   5. La tabla auxiliar      → si usas JOIN (aquí: departamento)
//
// Script SQL de referencia para crear las tablas en MySQL:
// ------------------------------------------------------------
// CREATE DATABASE empleados;
// USE empleados;
//
// CREATE TABLE departamento (
//     id     INT PRIMARY KEY AUTO_INCREMENT,
//     nombre VARCHAR(50) NOT NULL
// );
//
// INSERT INTO departamento (nombre) VALUES
//     ('Sistemas'), ('Contabilidad'), ('Logística'), ('Recursos Humanos');
//
// CREATE TABLE empleado (
//     id              INT PRIMARY KEY AUTO_INCREMENT,
//     nombre          VARCHAR(100)   NOT NULL,
//     salario         DECIMAL(10,2)  NOT NULL,
//     departamento_id INT            NOT NULL,
//     turno           VARCHAR(20)    NOT NULL,
//     activo          TINYINT(1)     NOT NULL DEFAULT 1,
//     FOREIGN KEY (departamento_id) REFERENCES departamento(id)
// );
// ============================================================
public class EmpleadoDAO {

    // ----------------------------------------------------------
    // LISTAR: trae todos los registros con JOIN a departamento
    // Si cambias de tema → ajusta tabla, columnas y JOIN
    // ----------------------------------------------------------
    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT e.id, e.nombre, e.salario, " +
                     "d.nombre AS departamento, e.turno, e.activo " +
                     "FROM empleado e " +
                     "INNER JOIN departamento d ON e.departamento_id = d.id " +
                     "ORDER BY e.id";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setSalario(rs.getDouble("salario"));
                e.setDepartamento(rs.getString("departamento"));
                e.setTurno(rs.getString("turno"));
                e.setActivo(rs.getBoolean("activo"));
                lista.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // ----------------------------------------------------------
    // INSERTAR: agrega un nuevo registro
    // El subquery (SELECT id FROM departamento ...) resuelve el FK
    // Si cambias de tema → ajusta tabla, columnas y el subquery del FK
    // ----------------------------------------------------------
    public void insertar(Empleado e) {
        String sql = "INSERT INTO empleado (nombre, salario, departamento_id, turno, activo) " +
                     "VALUES (?, ?, (SELECT id FROM departamento WHERE nombre = ?), ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setDouble(2, e.getSalario());
            ps.setString(3, e.getDepartamento()); // resuelve el FK por nombre
            ps.setString(4, e.getTurno());
            ps.setBoolean(5, e.isActivo());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // MODIFICAR: actualiza un registro existente por ID
    // Si cambias de tema → ajusta columnas SET y el subquery del FK
    // ----------------------------------------------------------
    public void modificar(Empleado e) {
        String sql = "UPDATE empleado SET " +
                     "nombre = ?, salario = ?, " +
                     "departamento_id = (SELECT id FROM departamento WHERE nombre = ?), " +
                     "turno = ?, activo = ? " +
                     "WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setDouble(2, e.getSalario());
            ps.setString(3, e.getDepartamento());
            ps.setString(4, e.getTurno());
            ps.setBoolean(5, e.isActivo());
            ps.setInt(6, e.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // ELIMINAR: borra un registro por su ID
    // Si cambias de tema → solo cambia el nombre de la tabla
    // ----------------------------------------------------------
    public void eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
