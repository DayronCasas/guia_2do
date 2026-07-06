-- ============================================================
-- PLANTILLA SQL - PARA ADAPTAR A CUALQUIER TEMA
-- ============================================================
-- INSTRUCCIONES:
--   1. Busca y reemplaza cada valor marcado con  <<  >>
--   2. Sigue el orden de los pasos (1 → 5)
--   3. Asegúrate que las opciones de la tabla auxiliar
--      coincidan EXACTAMENTE con las del JComboBox en Java
--
-- EJEMPLOS DE TEMAS Y SUS CAMPOS:
-- ┌─────────────┬──────────────┬──────────┬───────────────┬────────────────┬────────────┐
-- │ Tema        │ Tabla princ. │ Tabla FK │ Campo texto   │ Campo numérico │ Campo bool │
-- ├─────────────┼──────────────┼──────────┼───────────────┼────────────────┼────────────┤
-- │ Alumno      │ alumno       │ carrera  │ nombre        │ nota           │ matriculado│
-- │ Libro       │ libro        │ genero   │ titulo        │ precio         │ disponible │
-- │ Paciente    │ paciente     │ especialidad│ nombre     │ edad           │ activo     │
-- │ Producto    │ producto     │ categoria│ nombre        │ precio         │ disponible │
-- │ Vehículo    │ vehiculo     │ tipo     │ placa         │ año            │ operativo  │
-- └─────────────┴──────────────┴──────────┴───────────────┴────────────────┴────────────┘
-- ============================================================


-- ============================================================
-- PASO 1: Crear y seleccionar la base de datos
-- <<nombre_bd>> → ej: alumnos | libros | pacientes
-- ============================================================
CREATE DATABASE IF NOT EXISTS <<nombre_bd>>;
USE <<nombre_bd>>;


-- ============================================================
-- PASO 2: Tabla auxiliar (para el JComboBox)
-- <<tabla_aux>>  → ej: carrera | genero | especialidad | categoria
-- <<campo_aux>>  → generalmente "nombre" (no cambia casi nunca)
-- ============================================================
CREATE TABLE IF NOT EXISTS <<tabla_aux>> (
    id     INT         PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE  -- <<campo_aux>>
);

-- Opciones del JComboBox — DEBEN coincidir exactamente con el código Java
-- <<tabla_aux>>  → mismo nombre que arriba
-- Agrega o quita filas según tus opciones reales
INSERT INTO <<tabla_aux>> (nombre) VALUES
    ('<<opcion_1>>'),   -- ej: 'Sistemas'      | 'Terror'    | 'Cardiología'
    ('<<opcion_2>>'),   -- ej: 'Contabilidad'  | 'Romance'   | 'Neurología'
    ('<<opcion_3>>'),   -- ej: 'Logística'     | 'Ciencia'   | 'Pediatría'
    ('<<opcion_4>>');   -- ej: 'Recursos Humanos' | 'Historia' | 'Traumatología'


-- ============================================================
-- PASO 3: Tabla principal
-- <<tabla_principal>> → ej: alumno | libro | paciente | vehiculo
-- <<campo_texto>>     → ej: nombre | titulo | placa
-- <<campo_numero>>    → ej: salario | precio | nota | edad  (tipo: DECIMAL o INT)
-- <<tabla_aux_id>>    → nombre_fk: ej: carrera_id | genero_id | especialidad_id
-- <<campo_radio>>     → ej: turno | estado | condicion   (valores tipo texto)
-- <<campo_checkbox>>  → ej: activo | disponible | matriculado | operativo
-- ============================================================
CREATE TABLE IF NOT EXISTS <<tabla_principal>> (
    id              INT            PRIMARY KEY AUTO_INCREMENT,
    <<campo_texto>> VARCHAR(100)   NOT NULL,                        -- JTextField
    <<campo_numero>>DECIMAL(10,2)  NOT NULL,                        -- JTextField numérico
    <<tabla_aux_id>>INT            NOT NULL,                        -- JComboBox  (FK)
    <<campo_radio>> VARCHAR(30)    NOT NULL,                        -- JRadioButton
    <<campo_checkbox>> TINYINT(1)  NOT NULL DEFAULT 1,              -- JCheckBox
    FOREIGN KEY (<<tabla_aux_id>>) REFERENCES <<tabla_aux>>(id)
);


-- ============================================================
-- PASO 4: Registros de prueba (mínimo 4)
-- Reemplaza los valores entre comillas y números
-- <<tabla_principal>> y columnas deben coincidir con el PASO 3
-- ============================================================
INSERT INTO <<tabla_principal>> (<<campo_texto>>, <<campo_numero>>, <<tabla_aux_id>>, <<campo_radio>>, <<campo_checkbox>>) VALUES
    ('<<valor_texto_1>>', <<valor_numero_1>>, 1, '<<valor_radio_A>>', 1),
    ('<<valor_texto_2>>', <<valor_numero_2>>, 2, '<<valor_radio_B>>', 1),
    ('<<valor_texto_3>>', <<valor_numero_3>>, 3, '<<valor_radio_A>>', 0),
    ('<<valor_texto_4>>', <<valor_numero_4>>, 4, '<<valor_radio_B>>', 1);

-- EJEMPLO ya reemplazado (tema Alumnos):
-- INSERT INTO alumno (nombre, nota, carrera_id, turno, matriculado) VALUES
--     ('Ana Torres',    18.5, 1, 'Mañana', 1),
--     ('Luis Ramos',    14.0, 2, 'Tarde',  1),
--     ('María Flores',  16.5, 3, 'Mañana', 0),
--     ('Carlos Quispe', 19.0, 4, 'Tarde',  1);


-- ============================================================
-- PASO 5: Verificar inserción con el mismo JOIN que usa el DAO
-- Este SELECT debe devolver los datos tal como aparecen en la JTable
-- ============================================================
SELECT
    p.id,
    p.<<campo_texto>>,
    p.<<campo_numero>>,
    a.nombre          AS <<tabla_aux>>,   -- nombre del JComboBox
    p.<<campo_radio>>,
    IF(p.<<campo_checkbox>> = 1, 'Sí', 'No') AS <<campo_checkbox>>
FROM <<tabla_principal>> p
INNER JOIN <<tabla_aux>> a ON p.<<tabla_aux_id>> = a.id
ORDER BY p.id;


-- ============================================================
-- PASO 6: CRUD manual - para probar en Workbench
-- Reemplaza los mismos <<marcadores>> que usaste arriba
-- Ejecuta cada bloque por separado: selecciona + Ctrl+Shift+Enter
-- ============================================================


-- ------------------------------------------------------------
-- CREATE (INSERT) - Agregar un registro nuevo
-- <<tabla_principal>> → nombre de tu tabla
-- <<campo_*>>         → nombres de tus columnas
-- <<tabla_aux>>       → tabla del JComboBox
-- ------------------------------------------------------------
INSERT INTO <<tabla_principal>> (<<campo_texto>>, <<campo_numero>>, <<tabla_aux_id>>, <<campo_radio>>, <<campo_checkbox>>)
VALUES (
    '<<valor_texto>>',                                              -- JTextField  (texto)
    <<valor_numero>>,                                               -- JTextField  (número)
    (SELECT id FROM <<tabla_aux>> WHERE nombre = '<<opcion_X>>'),  -- JComboBox   (FK por nombre)
    '<<valor_radio>>',                                             -- JRadioButton (ej: 'Mañana' | 'Tarde')
    1                                                               -- JCheckBox   (1=Sí | 0=No)
);

-- EJEMPLO (tema Alumnos):
-- INSERT INTO alumno (nombre, nota, carrera_id, turno, matriculado)
-- VALUES ('Pedro Salas', 17.5, (SELECT id FROM carrera WHERE nombre = 'Sistemas'), 'Mañana', 1);


-- ------------------------------------------------------------
-- READ (SELECT) - Listar todos los registros con JOIN
-- Este es el mismo SELECT que ejecuta el DAO en Java (listar)
-- ------------------------------------------------------------
SELECT
    p.id,
    p.<<campo_texto>>,
    p.<<campo_numero>>,
    a.nombre                              AS <<tabla_aux>>,
    p.<<campo_radio>>,
    IF(p.<<campo_checkbox>> = 1, 'Sí', 'No') AS <<campo_checkbox>>
FROM <<tabla_principal>> p
INNER JOIN <<tabla_aux>> a ON p.<<tabla_aux_id>> = a.id
ORDER BY p.id;

-- EJEMPLO (tema Alumnos):
-- SELECT a.id, a.nombre, a.nota, c.nombre AS carrera,
--        a.turno, IF(a.matriculado=1,'Sí','No') AS matriculado
-- FROM alumno a INNER JOIN carrera c ON a.carrera_id = c.id ORDER BY a.id;


-- ------------------------------------------------------------
-- READ con filtro - Buscar por la tabla auxiliar (JComboBox)
-- ------------------------------------------------------------
SELECT
    p.id,
    p.<<campo_texto>>,
    p.<<campo_numero>>,
    a.nombre                              AS <<tabla_aux>>,
    p.<<campo_radio>>,
    IF(p.<<campo_checkbox>> = 1, 'Sí', 'No') AS <<campo_checkbox>>
FROM <<tabla_principal>> p
INNER JOIN <<tabla_aux>> a ON p.<<tabla_aux_id>> = a.id
WHERE a.nombre = '<<opcion_X>>'           -- filtra por la opción del JComboBox
ORDER BY p.<<campo_texto>>;


-- ------------------------------------------------------------
-- UPDATE (modificar) - Actualizar un registro por ID
-- Mismo comportamiento que el botón "Modificar" en Java
-- El WHERE id debe ser el ID de la fila seleccionada en la JTable
-- ------------------------------------------------------------
UPDATE <<tabla_principal>>
SET
    <<campo_texto>>    = '<<nuevo_valor_texto>>',                           -- JTextField
    <<campo_numero>>   = <<nuevo_valor_numero>>,                            -- JTextField
    <<tabla_aux_id>>   = (SELECT id FROM <<tabla_aux>> WHERE nombre = '<<opcion_X>>'), -- JComboBox
    <<campo_radio>>    = '<<nuevo_valor_radio>>',                           -- JRadioButton
    <<campo_checkbox>> = 1                                                  -- JCheckBox (1|0)
WHERE id = <<id_del_registro>>;  -- <-- ID de la fila seleccionada en la JTable

-- EJEMPLO (tema Alumnos):
-- UPDATE alumno SET nombre='Pedro S.', nota=18.0,
--   carrera_id=(SELECT id FROM carrera WHERE nombre='Contabilidad'),
--   turno='Tarde', matriculado=1 WHERE id = 3;


-- ------------------------------------------------------------
-- DELETE - Eliminar un registro por ID
-- Mismo comportamiento que el botón "Eliminar" en Java
-- CUIDADO: no se puede deshacer
-- ------------------------------------------------------------
DELETE FROM <<tabla_principal>>
WHERE id = <<id_del_registro>>;  -- <-- ID de la fila seleccionada en la JTable

-- EJEMPLO (tema Alumnos):
-- DELETE FROM alumno WHERE id = 3;


-- ------------------------------------------------------------
-- SELECT final - verificar cómo quedó la tabla tras los cambios
-- ------------------------------------------------------------
SELECT
    p.id,
    p.<<campo_texto>>,
    p.<<campo_numero>>,
    a.nombre                              AS <<tabla_aux>>,
    p.<<campo_radio>>,
    IF(p.<<campo_checkbox>> = 1, 'Sí', 'No') AS <<campo_checkbox>>
FROM <<tabla_principal>> p
INNER JOIN <<tabla_aux>> a ON p.<<tabla_aux_id>> = a.id
ORDER BY p.id;
