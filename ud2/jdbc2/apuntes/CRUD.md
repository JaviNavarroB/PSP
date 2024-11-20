
# Sesión 2: Operaciones CRUD con JDBC

### Objetivos de la Sesión
1. Realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) con JDBC.
2. Entender el uso de `PreparedStatement` para parametrizar consultas.
3. Utilizar `ResultSet` para manipular datos obtenidos de la base de datos.

---

## Introducción a DAO y DTO

### ¿Qué es un DAO (Data Access Object)?
- **Definición**: Un **DAO** es un patrón de diseño que se usa para separar la lógica de acceso a datos del resto de la aplicación. El DAO es responsable de interactuar con la base de datos y proporciona métodos específicos para realizar operaciones CRUD sin que el resto de la aplicación necesite conocer los detalles de la base de datos.
- **Ventajas**:
    - **Separación de responsabilidades**: Mantiene el acceso a datos independiente de la lógica de negocio.
    - **Facilita el mantenimiento y escalabilidad**: Cambios en la base de datos solo afectan al DAO.
- **Ejemplo en esta sesión**: La clase `AlumnoDAO` es un ejemplo de DAO que gestiona todas las operaciones CRUD de la tabla `alumnos`.

### ¿Qué es un DTO (Data Transfer Object)?
- **Definición**: Un **DTO** es un objeto utilizado para transportar datos entre capas o sistemas sin lógica de negocio. En Java, un DTO suele ser una clase simple con atributos privados y métodos *getters* y *setters*.
- **Ventajas**:
    - **Optimización**: Reduce el número de llamadas y la cantidad de datos transmitidos al solo contener los atributos necesarios.
    - **Seguridad y control**: Proporciona un formato específico de los datos que se exponen entre capas.
- **Ejemplo**: En aplicaciones más complejas, podríamos definir una clase `AlumnoDTO` para representar los datos de un alumno, sin que esta clase tenga ninguna lógica de negocio.

---

## 1. Entorno de Trabajo y Revisión de la Tabla `alumnos`

### Estructura de la Tabla `alumnos`

```sql
CREATE DATABASE escuela;
USE escuela;
CREATE TABLE alumnos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    edad INT
);
```

---

## 2. Insertar Datos (Create)

Para insertar registros en la tabla `alumnos`, usamos `INSERT` con un `PreparedStatement`, que evita problemas como SQL Injection.

### Código - Método para Insertar un Alumno

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlumnoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/escuela";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";

    public void insertarAlumno(String nombre, int edad) {
        String sql = "INSERT INTO alumnos (nombre, edad) VALUES (?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombre);  // Reemplaza el primer "?" por el nombre
            pstmt.setInt(2, edad);       // Reemplaza el segundo "?" por la edad

            int filasAfectadas = pstmt.executeUpdate(); // Ejecuta la inserción
            System.out.println("Filas insertadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Error al insertar el alumno.");
            e.printStackTrace();
        }
    }
}
```

### Uso del Método

```java
public class Main {
    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();
        dao.insertarAlumno("Juan Pérez", 20);
    }
}
```

---

## 3. Leer Datos (Read)

Para leer datos, usamos `SELECT` y `ResultSet` para manejar los resultados. La consulta se ejecuta con `executeQuery()`.

### Código - Método para Leer Todos los Alumnos

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    public void listarAlumnos() {
        String sql = "SELECT * FROM alumnos";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los alumnos.");
            e.printStackTrace();
        }
    }
}
```

### Uso del Método

```java
public class Main {
    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();
        dao.listarAlumnos();
    }
}
```

---

## 4. Actualizar Datos (Update)

Para actualizar registros, usamos `UPDATE`, especificando los campos a modificar y el registro a actualizar mediante el `id`.

### Código - Método para Actualizar un Alumno

```java
public class AlumnoDAO {
    public void actualizarAlumno(int id, String nuevoNombre, int nuevaEdad) {
        String sql = "UPDATE alumnos SET nombre = ?, edad = ? WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nuevoNombre);
            pstmt.setInt(2, nuevaEdad);
            pstmt.setInt(3, id);

            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Error al actualizar el alumno.");
            e.printStackTrace();
        }
    }
}
```

### Uso del Método

```java
public class Main {
    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();
        dao.actualizarAlumno(1, "Ana López", 22); // Cambia los datos del alumno con id=1
    }
}
```

---

## 5. Eliminar Datos (Delete)

Para eliminar registros, usamos `DELETE`, especificando el registro a eliminar mediante el `id`.

### Código - Método para Eliminar un Alumno

```java
public class AlumnoDAO {
    public void eliminarAlumno(int id) {
        String sql = "DELETE FROM alumnos WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Filas eliminadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Error al eliminar el alumno.");
            e.printStackTrace();
        }
    }
}
```

### Uso del Método

```java
public class Main {
    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();
        dao.eliminarAlumno(1); // Elimina el alumno con id=1
    }
}
```

---

## Resumen de la Sesión

- **Inserción (Create)**: Añadir registros con `INSERT`.
- **Lectura (Read)**: Recuperar datos con `SELECT` y manejar el `ResultSet`.
- **Actualización (Update)**: Modificar registros con `UPDATE`.
- **Eliminación (Delete)**: Borrar registros con `DELETE`.

---

## Ejercicio Práctico

1. **Inserción y Lectura**: Inserta varios alumnos y luego recupéralos usando `listarAlumnos()`.
2. **Actualización y Eliminación**: Actualiza un registro, elimina otro, y verifica los cambios ejecutando `listarAlumnos()`.

---
