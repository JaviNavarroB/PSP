# Unidad 2: Conectores de Datos - Introducción y Modelado

## Introducción a la Unidad

En esta unidad, exploraremos los conectores de datos y su integración en aplicaciones Java para trabajar con bases de datos. Los conectores permiten a las aplicaciones acceder, gestionar y manipular datos almacenados en bases de datos relacionales, como MySQL, utilizando la API JDBC (Java Database Connectivity). Aprenderemos a configurar conexiones, ejecutar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) y gestionar transacciones en un entorno de desarrollo.

## Objetivo de la Unidad

- Comprender el uso de JDBC para interactuar con bases de datos en aplicaciones Java.
- Conectar una aplicación Java con una base de datos MySQL.
- Modelar la estructura de una base de datos para satisfacer los requisitos de un sistema real.
- Implementar operaciones básicas de gestión de datos.

Para lograr estos objetivos, trabajaremos con un escenario de ejemplo que nos permitirá practicar la integración con bases de datos relacionales.

## Ejercicio: Modelado y Creación de una Base de Datos Escolar

### Descripción del Escenario

En una academia de formación, se necesita un sistema para gestionar la información de los alumnos, profesores y las asignaturas que se imparten. El sistema debe ser capaz de registrar:

- Los **alumnos**, con su nombre, apellido y fecha de nacimiento.
- Los **profesores**, también con su nombre, apellido y fecha de nacimiento.
- Las **asignaturas**, que incluyen el nombre de la asignatura, el número máximo de alumnos permitidos y el número de ausencias permitidas por alumno.
- Las **matriculas** de los alumnos en las asignaturas.
- La **asignación de profesores** para impartir las asignaturas.
- Las **calificaciones** que los profesores otorgan a los alumnos en sus respectivas asignaturas.

### Requisitos del Sistema

El sistema deberá ser capaz de:

1. **Registrar alumnos y profesores**, almacenando su información personal.
2. **Definir asignaturas**, incluyendo límites de inscripción y ausencias permitidas.
3. **Matricular alumnos en asignaturas**, guardando la fecha de matrícula.
4. **Asignar profesores a las asignaturas** que imparten.
5. **Gestionar las calificaciones de los alumnos**, permitiendo a los profesores registrar las notas.

### Estructura de la Base de Datos

Para implementar el escenario, crearemos las siguientes tablas en la base de datos:

1. **Tabla `Alumnos`**: Almacena información sobre los estudiantes.
2. **Tabla `Profesores`**: Almacena información sobre los profesores.
3. **Tabla `Asignaturas`**: Contiene información sobre las asignaturas.
4. **Tabla `Matriculas`**: Relaciona a los alumnos con las asignaturas en las que están inscritos.
5. **Tabla `Asignacion_Profesor`**: Relaciona a los profesores con las asignaturas que imparten.
6. **Tabla `Calificaciones`**: Almacena las calificaciones de los alumnos en cada asignatura.

### Diagrama de la Base de Datos

```plaintext
Alumnos               Profesores            Asignaturas
+----+----------+     +----+----------+     +----+----------+
| id | nombre   |     | id | nombre   |     | id | nombre   |
|    | apellido |     |    | apellido |     |    | alumnos  |
|    | fecha    |     |    | fecha    |     |    | ausencias|
+----+----------+     +----+----------+     +----+----------+
   |                    |                  |
   |                    |                  |
   +--------------------+------------------+
                 Matriculas
                 +----+------------+
                 | id | alumno_id  |
                 |    | fecha      |
                 +----+------------+
                 +-------------------+
                 | Calificaciones    |
                 +----+-------------+
                 | id | alumno_id    |
                 |    | asignatura_id |
                 |    | profesor_id   |
                 |    | calificacion   |
                 |    | fecha         |
                 +-------------------+
