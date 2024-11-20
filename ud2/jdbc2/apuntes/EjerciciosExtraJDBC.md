### Práctica

#### Ejercicio 1: Consultas Dinámicas con `PreparedStatement`

Implementa un método `buscarAlumnosPorApellido` en la clase `AlumnoDAO` que reciba un `apellido` como parámetro y devuelva una lista de alumnos con ese apellido.


#### Ejercicio 2: Llamada a Procedimiento Almacenado

1. Crea un procedimiento almacenado en la base de datos que cuente el número de alumnos matriculados en una asignatura dada (`ContarMatriculados`).
2. Implementa el método `contarMatriculados(int asignaturaId)` en `AsignaturaDAO` que llame al procedimiento y devuelva el número de alumnos.
3. Incluye este procedimiento en el gestor de base de datos de academia.

**Ejemplo de Procedimiento `ContarMatriculados`:**
```sql
DELIMITER //
CREATE PROCEDURE ContarMatriculados(IN asignatura_id INT, OUT total INT)
BEGIN
    SELECT COUNT(*) INTO total
    FROM Matriculas
    WHERE asignatura_id = asignatura_id;
END //
DELIMITER ;
```


