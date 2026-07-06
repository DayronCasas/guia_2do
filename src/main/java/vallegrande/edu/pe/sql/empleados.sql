-- ============================================================
-- BASE DE DATOS: empleados
-- Tema: Gestión de Empleados
-- Conexión: MySQL · AWS RDS
-- Ejecutar este script en MySQL Workbench o desde la terminal
-- ============================================================

-- 1. Crear y seleccionar la base de datos
CREATE DATABASE IF NOT EXISTS empleados;
USE empleados;

-- ============================================================
-- 2. Tabla auxiliar: departamento
--    Usada por el JComboBox en la vista
-- ============================================================
CREATE TABLE IF NOT EXISTS departamento (
    id     INT          PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50)  NOT NULL UNIQUE
);

-- Registros iniciales del JComboBox (deben coincidir con el código Java)
INSERT INTO departamento (nombre) VALUES
    ('Sistemas'),
    ('Contabilidad'),
    ('Logística'),
    ('Recursos Humanos');

-- ============================================================
-- 3. Tabla principal: empleado
--    Cada columna corresponde a un componente Swing:
--      nombre          → JTextField
--      salario         → JTextField  (numérico)
--      departamento_id → JComboBox   (FK a tabla departamento)
--      turno           → JRadioButton ('Mañana' | 'Tarde')
--      activo          → JCheckBox   (1 = activo, 0 = inactivo)
-- ============================================================
CREATE TABLE IF NOT EXISTS empleado (
    id              INT           PRIMARY KEY AUTO_INCREMENT,
    nombre          VARCHAR(100)  NOT NULL,
    salario         DECIMAL(10,2) NOT NULL,
    departamento_id INT           NOT NULL,
    turno           VARCHAR(20)   NOT NULL,
    activo          TINYINT(1)    NOT NULL DEFAULT 1,
    FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);

-- ============================================================
-- 4. Registros de prueba
-- ============================================================
INSERT INTO empleado (nombre, salario, departamento_id, turno, activo) VALUES
    ('Ana Torres',    2500.00, 1, 'Mañana', 1),
    ('Luis Ramos',    3100.50, 2, 'Tarde',  1),
    ('María Flores',  2800.00, 3, 'Mañana', 0),
    ('Carlos Quispe', 3500.00, 4, 'Tarde',  1);

-- ============================================================
-- 5. Verificar que los datos se insertaron correctamente
-- ============================================================
SELECT
    e.id,
    e.nombre,
    e.salario,
    d.nombre AS departamento,
    e.turno,
    IF(e.activo = 1, 'Sí', 'No') AS activo
FROM empleado e
INNER JOIN departamento d ON e.departamento_id = d.id
ORDER BY e.id;

-- ============================================================
-- CRUD - Operaciones de prueba / consulta manual
-- Puedes ejecutar cada bloque por separado en Workbench
-- seleccionando las líneas y presionando Ctrl + Shift + Enter
-- ============================================================


-- ------------------------------------------------------------
-- CREATE (INSERT) - Agregar un nuevo empleado
-- Equivale al botón "Agregar" en la aplicación Java
-- ------------------------------------------------------------
INSERT INTO empleado (nombre, salario, departamento_id, turno, activo)
VALUES (
    'Rosa Méndez',                                      -- nombre     (JTextField)
    2750.00,                                            -- salario    (JTextField)
    (SELECT id FROM departamento WHERE nombre = 'Logística'), -- departamento (JComboBox → FK)
    'Mañana',                                           -- turno      (JRadioButton: 'Mañana' | 'Tarde')
    1                                                   -- activo     (JCheckBox: 1=Sí | 0=No)
);


-- ------------------------------------------------------------
-- READ (SELECT) - Listar todos los empleados
-- Equivale a la carga inicial de la JTable
-- El JOIN trae el nombre del departamento en lugar del ID
-- ------------------------------------------------------------
SELECT
    e.id,
    e.nombre,
    e.salario,
    d.nombre                          AS departamento,
    e.turno,
    IF(e.activo = 1, 'Sí', 'No')     AS activo
FROM empleado e
INNER JOIN departamento d ON e.departamento_id = d.id
ORDER BY e.id;


-- ------------------------------------------------------------
-- READ con filtro - Buscar por departamento (consulta extra útil)
-- ------------------------------------------------------------
SELECT
    e.id,
    e.nombre,
    e.salario,
    d.nombre                          AS departamento,
    e.turno,
    IF(e.activo = 1, 'Sí', 'No')     AS activo
FROM empleado e
INNER JOIN departamento d ON e.departamento_id = d.id
WHERE d.nombre = 'Sistemas'          -- cambia el departamento a filtrar
ORDER BY e.nombre;


-- ------------------------------------------------------------
-- UPDATE (modificar) - Actualizar datos de un empleado por ID
-- Equivale al botón "Modificar" en la aplicación Java
-- Cambia el número del WHERE id = ? por el ID que quieras editar
-- ------------------------------------------------------------
UPDATE empleado
SET
    nombre          = 'Rosa M. Méndez',                             -- JTextField
    salario         = 3000.00,                                      -- JTextField
    departamento_id = (SELECT id FROM departamento WHERE nombre = 'Contabilidad'), -- JComboBox
    turno           = 'Tarde',                                      -- JRadioButton
    activo          = 1                                             -- JCheckBox
WHERE id = 5;   -- <-- pon aquí el ID de la fila seleccionada en la JTable


-- ------------------------------------------------------------
-- DELETE - Eliminar un empleado por ID
-- Equivale al botón "Eliminar" en la aplicación Java
-- CUIDADO: esta operación no se puede deshacer
-- ------------------------------------------------------------
DELETE FROM empleado
WHERE id = 5;   -- <-- pon aquí el ID de la fila seleccionada en la JTable


-- ------------------------------------------------------------
-- SELECT de verificación final - ver cómo quedó la tabla
-- ------------------------------------------------------------
SELECT
    e.id,
    e.nombre,
    e.salario,
    d.nombre                          AS departamento,
    e.turno,
    IF(e.activo = 1, 'Sí', 'No')     AS activo
FROM empleado e
INNER JOIN departamento d ON e.departamento_id = d.id
ORDER BY e.id;
