
# Ejercicio: Sistema de Gestión de Academia en Consola

## Enunciado

Desarrolla un programa en Java que gestione datos de una academia mediante una interfaz de consola. Este programa debe interactuar con una base de datos llamada `Academia` y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las siguientes entidades:

- **Alumnos**
- **Profesores**
- **Asignaturas**
- **Matrículas**
- **Calificaciones**

Además, cuando se añada un alumno, el sistema deberá permitir registrar las asignaturas en las que está matriculado y sus calificaciones correspondientes.

## Objetivo

Crear un sistema de gestión interactivo que permita al usuario realizar operaciones completas sobre las entidades mencionadas, con un enfoque en la gestión de alumnos y sus respectivas asignaturas y calificaciones.

## Funcionalidades del Programa

El programa debe presentar un **menú principal** que permita gestionar cada una de las entidades. Cada opción del menú llevará a un submenú con las operaciones CRUD para la entidad seleccionada.

### Menú Principal

1. **Gestión de Alumnos**
2. **Gestión de Profesores**
3. **Gestión de Asignaturas**
4. **Gestión de Matrículas**
5. **Gestión de Calificaciones**
6. **Gestión de Asignación-Profesor**
7. **Mostrar Profesor y Alumnos de Asignatura**
8. **Salir**

### Detalles de Funcionalidades

#### 1. Gestión de Alumnos

- **Insertar Alumno**: Solicita `nombre`, `apellido`, y `fecha de nacimiento`. Luego permite registrar las asignaturas en las que está matriculado.

- **Listar Alumnos**: Muestra todos los alumnos registrados en la base de datos.

- **Actualizar Alumno**: Permite actualizar los datos de un alumno seleccionado por su ID.

- **Eliminar Alumno**: Permite eliminar un alumno a partir de su ID.

---

#### 2. Gestión de Profesores

- **Insertar Profesor**: Solicita `nombre`, `apellido`, y `fecha de nacimiento`.

- **Listar Profesores**: Muestra todos los profesores registrados.

- **Actualizar Profesor**: Permite actualizar los datos de un profesor seleccionado.

- **Eliminar Profesor**: Permite eliminar un profesor a partir de su ID.

---

#### 3. Gestión de Asignaturas

- **Insertar Asignatura**: Solicita `nombre`, `alumnos_maximos`, y `ausencias_permitidas`.

- **Listar Asignaturas**: Muestra todas las asignaturas disponibles.

- **Actualizar Asignatura**: Permite actualizar los datos de una asignatura seleccionada.

- **Eliminar Asignatura**: Permite eliminar una asignatura a partir de su ID.

---

#### 4. Gestión de Matrículas

- **Matricular Alumno**: Permite asociar un alumno a una asignatura y registra la fecha de matrícula.

- **Listar Matrículas**: Muestra todas las matrículas realizadas.

- **Actualizar Matrícula**: Permite actualizar los datos de una matrícula seleccionada.

- **Eliminar Matrícula**: Permite eliminar una matrícula a partir de su ID.

---

#### 5. Gestión de Calificaciones

- **Registrar Calificación**: Permite ingresar una calificación para un alumno en una asignatura específica.

- **Listar Calificaciones**: Muestra todas las calificaciones registradas.

- **Actualizar Calificación**: Permite actualizar una calificación a partir de su ID.

- **Eliminar Calificación**: Permite eliminar una calificación a partir de su ID.

---

#### 6. Mostrar Profesor y Alumnos de Asignatura

- **Mostrar Profesor y Alumnos**: Dada una asignatura, solicita el `id` de la asignatura y muestra el profesor que la imparte junto con todos los alumnos matriculados en dicha asignatura.

---

### Ejemplo de Interacción

```
Bienvenido al Sistema de Gestión de Academia

Seleccione una opción:
1. Gestión de Alumnos
2. Gestión de Profesores
3. Gestión de Asignaturas
4. Gestión de Matrículas
5. Gestión de Calificaciones
6. Mostrar Profesor y Alumnos de Asignatura
7. Gestión de Asignacion-Profesor
8. Salir

> 1

--- Gestión de Alumnos ---
Seleccione una operación:
1. Insertar Alumno
2. Listar Alumnos
3. Actualizar Alumno
4. Eliminar Alumno
5. Volver al Menú Principal

> 1

Ingrese el nombre del alumno:
> Juan
Ingrese el apellido del alumno:
> Pérez
Ingrese la fecha de nacimiento (YYYY-MM-DD):
> 2001-05-14

Alumno insertado exitosamente con id 23.
```
---
