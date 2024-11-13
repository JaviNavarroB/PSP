## Diferencias entre SQL y HQL

**SQL** (Structured Query Language) y **HQL** (Hibernate Query Language) son lenguajes de consulta utilizados para acceder a datos en bases de datos, pero existen varias diferencias clave entre ambos. Mientras SQL es un lenguaje estándar para bases de datos relacionales, HQL es un lenguaje orientado a objetos que Hibernate utiliza para trabajar con entidades Java. Aquí exploramos sus diferencias principales.

### 1. Tipo de Orientación

- **SQL**: Está orientado a trabajar directamente con **tablas** y **columnas** de una base de datos relacional.
- **HQL**: Está orientado a trabajar con **entidades** y sus **atributos** en el contexto de la capa de objetos en Java.

### 2. Independencia de la Base de Datos

- **SQL**: Las consultas SQL están escritas para una base de datos específica y, en muchos casos, no son portables entre diferentes tipos de SGBD sin modificaciones.
- **HQL**: Al ser parte de Hibernate, HQL es **independiente del tipo de base de datos**. Hibernate se encarga de traducir HQL en la sintaxis SQL específica del SGBD en uso, lo que facilita la portabilidad.

### 3. Manipulación de Entidades vs. Tablas

- **SQL**: Accede y manipula datos directamente en las **tablas y columnas** de la base de datos.
- **HQL**: Trabaja con **objetos** que representan las tablas (es decir, entidades Java), por lo que las consultas están orientadas a las propiedades de las clases en lugar de a columnas.

### 4. Relaciones entre Entidades

- **SQL**: Para manejar relaciones, como `JOINs`, SQL requiere unir tablas explícitamente.
- **HQL**: Permite el uso de **joins basados en entidades**, lo cual simplifica el manejo de relaciones usando mapeos de objetos en lugar de tablas.

### 5. Sintaxis de las Consultas

- **SQL**: Las consultas SQL son explícitas en el uso de tablas y columnas.
  ```sql
  SELECT nombre FROM Alumnos WHERE id = 1;
  ```
- **HQL**: En HQL, se hace referencia a clases y atributos, no a tablas.
  ```java
  FROM Alumno WHERE id = 1
  ```

### 6. Funciones de Agregación y Operaciones CRUD

- **SQL**: Proporciona funciones como `COUNT`, `SUM`, `AVG`, `MIN`, y `MAX`, que son directamente soportadas en SQL.
- **HQL**: También soporta estas funciones de agregación y permite operaciones CRUD a través de métodos Hibernate, como `session.save()` para insertar o `session.delete()` para eliminar.

### 7. Manejo de Parámetros

- **SQL**: Para evitar la inyección SQL, SQL permite parámetros mediante el uso de `PreparedStatement`.
  ```sql
  SELECT * FROM Alumnos WHERE nombre = ?;
  ```
- **HQL**: Utiliza `Query` de Hibernate, que permite definir parámetros con el uso de `:nombre_parametro`.
  ```java
  FROM Alumno WHERE nombre = :nombre
  ```

### 8. Objetivo Principal

- **SQL**: Diseñado para realizar **consultas y manipulaciones de datos directamente** en la base de datos.
- **HQL**: Su propósito es **integrar el acceso a datos en aplicaciones orientadas a objetos**, ofreciendo una capa de abstracción que conecta clases Java con tablas.

### Ejemplo Comparativo

Supongamos que queremos seleccionar todos los alumnos con nombre "Juan".

**SQL:**

```sql
SELECT * FROM Alumnos WHERE nombre = 'Juan';
```

**HQL:**

```java
FROM Alumno WHERE nombre = 'Juan'
```

### Conclusión

| Aspecto                    | SQL                                       | HQL                                 |
|----------------------------|-------------------------------------------|-------------------------------------|
| Orientación                | Basada en tablas                          | Basada en entidades Java            |
| Dependencia de SGBD        | Dependiente                               | Independiente                       |
| Sintaxis                   | Tablas y columnas                         | Clases y atributos                  |
| Relaciones (joins)         | Directas entre tablas                     | Basadas en relaciones de entidades  |
| Uso en aplicaciones        | Directamente en bases de datos            | Integrado en aplicaciones Java      |

En resumen, mientras que **SQL** es el lenguaje estándar para bases de datos relacionales, **HQL** está diseñado para aplicaciones Java que usan Hibernate, proporcionando una capa de abstracción entre los objetos Java y la base de datos.