# Relaciones @OneToMany en Hibernate

## 1. ¿Qué es `@OneToMany`?

En Hibernate, la anotación `@OneToMany` se usa para definir una relación de "uno a muchos" entre dos entidades. En esta relación, un registro de la tabla principal está asociado con varios registros en la tabla secundaria. En la base de datos "Academia", esta relación puede aplicarse de varias maneras. Por ejemplo:

- Un profesor puede impartir varias asignaturas.
- Un alumno puede tener varias matrículas.

### Notación de `@OneToMany`

La anotación `@OneToMany` va en el lado de la entidad que tiene una sola instancia relacionada con muchas instancias en la otra entidad. Para esta configuración, es común también usar la anotación `@ManyToOne` en la entidad que representa el lado "muchos" de la relación, lo que permite una relación bidireccional.

## 2. Ejemplo de Relaciones en la Base de Datos Academia

Para ilustrar `@OneToMany`, vamos a modelar dos relaciones en nuestra base de datos:

1. **Relación entre `Profesor` y `Asignacion_Profesor`**: Un profesor puede impartir varias asignaturas, pero cada asignatura está a cargo de un solo profesor.
2. **Relación entre `Alumno` y `Matricula`**: Un alumno puede tener varias matrículas, cada una relacionada con una asignatura específica.

### Relación Profesor - Asignacion_Profesor

La relación aquí es de "uno a muchos" desde `Profesor` hacia `Asignacion_Profesor`. La entidad `Profesor` tendrá una lista de `Asignacion_Profesor`.

#### Entidad `Profesor`

```java
package com.academia;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    // Relación de uno a muchos con Asignacion_Profesor
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<AsignacionProfesor> asignaciones;

    // Getters y Setters
}
```

En este caso:

- `@OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)`: Define la relación `@OneToMany` y especifica que el mapeo inverso de esta relación está en el campo `profesor` de la entidad `AsignacionProfesor`.
- `cascade = CascadeType.ALL`: Significa que todas las operaciones realizadas en `Profesor` (persistir, actualizar, eliminar) se aplicarán automáticamente en las `AsignacionProfesor` relacionadas.

#### Entidad `AsignacionProfesor`

```java
package com.academia;

import javax.persistence.*;

@Entity
@Table(name = "Asignacion_Profesor")
public class AsignacionProfesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    // Getters y Setters
}
```

En la entidad `AsignacionProfesor`:

- `@ManyToOne`: Indica que `AsignacionProfesor` está en el lado "muchos" de la relación.
- `@JoinColumn(name = "profesor_id")`: Especifica la columna `profesor_id` en la tabla `Asignacion_Profesor` como la clave foránea para la relación.

### Relación Alumno - Matricula

Un alumno puede tener muchas matrículas, cada una representando una asignatura diferente.

#### Entidad `Alumno`

```java
package com.academia;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    // Relación de uno a muchos con Matricula
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    private List<Matricula> matriculas;

    // Getters y Setters
}
```

#### Entidad `Matricula`

```java
package com.academia;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;

    @Column(name = "fecha_matricula", nullable = false)
    private Date fechaMatricula;

    // Getters y Setters
}
```

## 3. Cascada y Carga Perezosa en `@OneToMany`

### Cascada (`cascade`)

El atributo `cascade` en `@OneToMany` permite definir qué operaciones se aplicarán de manera automática en las entidades relacionadas. Las opciones más comunes son:

- **`CascadeType.PERSIST`**: Al persistir un objeto `Profesor`, también se persisten sus `AsignacionProfesor`.
- **`CascadeType.MERGE`**: Al actualizar un `Profesor`, se actualizan sus `AsignacionProfesor`.
- **`CascadeType.REMOVE`**: Al eliminar un `Profesor`, se eliminan sus `AsignacionProfesor`.

### Carga Perezosa (`FetchType.LAZY`)

El atributo `fetch` permite definir cómo se carga la colección de entidades relacionadas.

- **`FetchType.LAZY`**: Carga las entidades relacionadas solo cuando son necesarias.
- **`FetchType.EAGER`**: Carga las entidades relacionadas inmediatamente al cargar la entidad padre.

Ejemplo de uso:

```java
@OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<AsignacionProfesor> asignaciones;
```

## 4. Ejemplo de CRUD con Relaciones `@OneToMany`

### Crear y Guardar un `Profesor` con `Asignaciones`

```java
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.academia.Profesor;
import com.academia.AsignacionProfesor;

Session session = HibernateUtil.getSessionFactory().openSession();
Transaction transaction = session.beginTransaction();

// Crear el profesor
Profesor profesor = new Profesor();
profesor.setNombre("Laura");
profesor.setApellido("Gómez");

// Crear una asignación
AsignacionProfesor asignacion = new AsignacionProfesor();
asignacion.setProfesor(profesor); // Asociar el profesor a la asignación

// Agregar la asignación al profesor
profesor.setAsignaciones(List.of(asignacion));

// Guardar el profesor (y su asignación por cascada)
session.save(profesor);
transaction.commit();
session.close();
```

En este ejemplo, al guardar el `Profesor`, Hibernate guarda automáticamente las `AsignacionProfesor` asociadas gracias al `CascadeType.ALL`.

---