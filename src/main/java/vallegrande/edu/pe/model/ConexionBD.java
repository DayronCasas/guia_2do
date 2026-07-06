package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.DriverManager;

// ============================================================
// PLANTILLA - CONEXIÓN A BASE DE DATOS (AWS RDS MySQL)
// Si cambias de tema, solo necesitas ajustar:
//   - ENDPOINT  → el endpoint de tu instancia RDS
//   - DATABASE  → el nombre de tu base de datos
//   - USER      → tu usuario de MySQL
//   - PASSWORD  → tu contraseña de MySQL
// El resto de la clase NO cambia entre temas.
// ============================================================
public class ConexionBD {

    // --- CAMBIA ESTOS VALORES según tu instancia RDS y base de datos ---
    private static final String ENDPOINT = "empleadosga.c4tomtl2gofc.us-east-1.rds.amazonaws.com";
    private static final String PORT     = "3306";
    private static final String DATABASE = "empleados";   // <-- nombre de tu BD
    private static final String USER     = "root";         // <-- usuario maestro RDS
    private static final String PASSWORD = "admin12345";   // <-- tu contraseña

    public static Connection getConexion() {
        try {
            String url = "jdbc:mysql://" + ENDPOINT + ":" + PORT + "/" + DATABASE
                       + "?useSSL=false&allowPublicKeyRetrieval=true";
            Connection con = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión exitosa a AWS RDS - Empleados");
            return con;
        } catch (Exception e) {
            System.out.println("ERROR DE CONEXIÓN A AWS RDS:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
