# Gestión de Empleados

Aplicación de escritorio en Java (Swing) que permite realizar un CRUD completo sobre una tabla de empleados. Usa el patrón MVC con capas bien separadas: modelo, vista y controlador.

## Tecnologías

- Java 17
- Swing (interfaz gráfica de escritorio)
- Maven
- MySQL 8 vía `mysql-connector-j 8.3.0`

## Conexión a la base de datos

La aplicación se conecta a una instancia **MySQL en AWS RDS** (`us-east-1`). Los parámetros de conexión están centralizados en `ConexionBD.java`:

| Parámetro  | Descripción                        |
|------------|------------------------------------|
| `ENDPOINT` | Host de la instancia RDS           |
| `PORT`     | Puerto MySQL (3306 por defecto)    |
| `DATABASE` | Nombre de la base de datos         |
| `USER`     | Usuario maestro de MySQL           |
| `PASSWORD` | Contraseña del usuario             |

Si necesitas apuntar a otra instancia, solo edita esos cuatro valores en `ConexionBD.java`; el resto del proyecto no cambia.

## Estructura del proyecto

```
src/main/java/vallegrande/edu/pe/
├── model/
│   ├── Empleado.java      # Entidad (POJO)
│   ├── EmpleadoDAO.java   # Acceso a datos (CRUD)
│   └── ConexionBD.java    # Conexión a AWS RDS
├── view/
│   ├── InicioView.java    # Pantalla de inicio
│   └── EmpleadoView.java  # Formulario principal
├── controller/
│   └── EmpleadoController.java
└── App.java               # Punto de entrada
```

## Campos de la entidad `Empleado`

| Campo          | Tipo      | Control UI    |
|----------------|-----------|---------------|
| `id`           | `int`     | —             |
| `nombre`       | `String`  | JTextField    |
| `salario`      | `double`  | JTextField    |
| `departamento` | `String`  | JComboBox     |
| `turno`        | `String`  | JRadioButton  |
| `activo`       | `boolean` | JCheckBox     |
