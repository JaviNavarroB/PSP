# Consultas Preparadas y Llamadas a Procedimientos Almacenados

### 1. Diferencia entre `Statement` y `PreparedStatement`

Al usar JDBC para ejecutar consultas SQL, existen dos formas principales de hacerlo:
- **`Statement`**: Se utiliza para ejecutar consultas SQL sencillas. Sin embargo, **es vulnerable a inyecciones SQL** y es menos eficiente para consultas repetitivas.
- **`PreparedStatement`**: Es una forma más segura y optimizada de ejecutar consultas SQL. Los parámetros se establecen mediante métodos (`setString`, `setInt`, etc.), lo que evita inyecciones SQL y mejora el rendimiento al reutilizar las consultas preparadas.

**Ejemplo de `Statement` (No recomendado):**
```java
Statement stmt = connection.createStatement();
String sql = "SELECT * FROM Alumnos WHERE nombre = '" + nombre + "'";
ResultSet rs = stmt.executeQuery(sql);
```

**Ejemplo de `PreparedStatement` (Recomendado):**
```java
String sql = "SELECT * FROM Alumnos WHERE nombre = ?";
PreparedStatement pstmt = connection.prepareStatement(sql);
pstmt.setString(1, nombre);
ResultSet rs = pstmt.executeQuery();
```

### 2. Ejecución de Consultas Dinámicas y Seguras con `PreparedStatement`

Al usar `PreparedStatement`, podemos insertar valores en la consulta utilizando **parámetros** (`?`). Esto permite pasar datos de manera segura, evitando que se inyecte SQL malicioso.

#### Ejemplo: Insertar un Alumno de forma segura

```java
public void insertarAlumno(String nombre, String apellido, Date fechaNacimiento) {
    String sql = "INSERT INTO Alumnos (nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, nombre);
        pstmt.setString(2, apellido);
        pstmt.setDate(3, fechaNacimiento);
        pstmt.executeUpdate();
        System.out.println("Alumno insertado exitosamente.");
    } catch (SQLException e) {
        System.err.println("Error al insertar el alumno: " + e.getMessage());
    }
}
```

Este método permite:
1. **Evitar inyecciones SQL**: Los parámetros `?` aseguran que los datos ingresados se traten como valores, no como parte de la consulta.
2. **Optimización**: La base de datos puede optimizar las consultas `PreparedStatement`, haciéndolas más rápidas en ejecuciones repetidas.

### 3. Llamadas a Procedimientos Almacenados y Manejo de Parámetros

Un **procedimiento almacenado** es un conjunto de instrucciones SQL guardadas en la base de datos. Podemos invocar procedimientos almacenados desde Java utilizando JDBC y la clase `CallableStatement`. Esto es útil para ejecutar lógica compleja en la base de datos.

#### Ejemplo: Crear un Procedimiento Almacenado en MySQL

Imaginemos un procedimiento que calcula el promedio de calificaciones de un alumno:
```sql
DELIMITER //
CREATE PROCEDURE CalcularPromedio(IN alumno_id INT, OUT promedio DECIMAL(5,2))
BEGIN
    SELECT AVG(calificacion) INTO promedio
    FROM Calificaciones
    WHERE alumno_id = alumno_id;
END //
DELIMITER ;
```

Este procedimiento toma un `alumno_id` como entrada y devuelve el `promedio` de sus calificaciones.

#### Llamada al Procedimiento Almacenado desde Java

Para llamar a este procedimiento desde Java, utilizaremos `CallableStatement`:

```java
public double calcularPromedio(int alumnoId) {
    String sql = "{CALL CalcularPromedio(?, ?)}";
    double promedio = 0.0;

    try (Connection conn = DatabaseConnection.getConnection();
         CallableStatement cstmt = conn.prepareCall(sql)) {

        // Establecemos el parámetro de entrada
        cstmt.setInt(1, alumnoId);

        // Registramos el parámetro de salida
        cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);

        // Ejecutamos el procedimiento almacenado
        cstmt.execute();

        // Obtenemos el valor de salida
        promedio = cstmt.getDouble(2);

    } catch (SQLException e) {
        System.err.println("Error al calcular el promedio: " + e.getMessage());
    }

    return promedio;
}
```