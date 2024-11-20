## Guía de Uso de Lombok

**Lombok** es una biblioteca de Java que permite reducir el código repetitivo, como getters, setters, constructores, `equals`, `hashCode`, y `toString`. A través de anotaciones, Lombok genera automáticamente este código en tiempo de compilación, haciéndolo ideal para modelos de datos o entidades donde estas operaciones son comunes.

### 1. ¿Por qué usar Lombok?

Al utilizar Lombok, puedes:
- **Reducir código repetitivo**: Lombok elimina la necesidad de escribir manualmente métodos como getters, setters, constructores, etc.
- **Mejorar la legibilidad**: El código es más fácil de leer y mantener.
- **Evitar errores humanos**: Lombok genera métodos estandarizados, eliminando posibles errores de implementación manual.

### 2. Configuración de Lombok en un Proyecto Maven

Para comenzar a usar Lombok, primero debemos agregar la dependencia en nuestro archivo `pom.xml`.

#### Dependencia en `pom.xml`

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version> <!-- Usa la última versión disponible -->
    <scope>provided</scope>
</dependency>
```

- La anotación `<scope>provided</scope>` indica que Lombok se utiliza solo en tiempo de compilación y no será necesaria en tiempo de ejecución.

### 3. Habilitar Lombok en el IDE

#### IntelliJ IDEA

1. Ve a **File > Settings > Plugins**.
2. Busca **Lombok** en el repositorio de plugins.
3. Instala el plugin y reinicia IntelliJ IDEA.
4. Asegúrate de habilitar la opción **Enable annotation processing** en **File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors**.

### 4. Anotaciones Principales de Lombok

A continuación, veremos algunas de las anotaciones más utilizadas de Lombok, junto con ejemplos para cada una.

#### 4.1. `@Getter` y `@Setter`

Estas anotaciones generan automáticamente los métodos getter y setter para los atributos de una clase.

```java
import lombok.Getter;
import lombok.Setter;

public class Alumno {
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String apellido;

    @Getter @Setter
    private Date fechaNacimiento;
}
```

#### 4.2. `@ToString`

Genera un método `toString` que incluye todos los campos de la clase.

```java
import lombok.ToString;

@ToString
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
}
```

- También puedes especificar campos excluidos: `@ToString(exclude = "id")`.

#### 4.3. `@EqualsAndHashCode`

Genera los métodos `equals` y `hashCode` basados en todos los campos de la clase. Esto es útil para comparar objetos por contenido en lugar de por referencia.

```java
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
}
```

#### 4.4. `@NoArgsConstructor`, `@AllArgsConstructor` y `@RequiredArgsConstructor`

Estas anotaciones generan constructores automáticamente:
- `@NoArgsConstructor`: Crea un constructor sin argumentos.
- `@AllArgsConstructor`: Crea un constructor con todos los campos como parámetros.
- `@RequiredArgsConstructor`: Crea un constructor solo con los campos `final` o marcados como `@NonNull`.

```java
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Alumno {
    private final int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
}
```

#### 4.5. `@Data`

`@Data` combina varias anotaciones de Lombok en una sola: `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, y `@RequiredArgsConstructor`.

```java
import lombok.Data;

@Data
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
}
```

Con `@Data`, todos los métodos esenciales están generados, incluyendo getters, setters, `toString`, `equals`, `hashCode`, y un constructor para los campos `final` o `@NonNull`.

#### 4.6. `@Builder`

Genera un patrón **Builder** para la clase, que permite construir objetos de una manera más legible y flexible.

```java
import lombok.Builder;

@Builder
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
}
```

Con el patrón Builder, puedes crear un objeto de esta manera:

```java
Alumno alumno = Alumno.builder()
                      .id(1)
                      .nombre("Juan")
                      .apellido("Perez")
                      .fechaNacimiento(new Date())
                      .build();
```

### 5. Ejemplo Completo con Lombok

```java
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
}
```

Con este código, Lombok generará todos los métodos necesarios para trabajar con la clase `Alumno`, además de un constructor sin argumentos, un constructor con todos los argumentos, y el patrón Builder.

### 6. Ventajas y Desventajas de Usar Lombok

#### Ventajas

- **Ahorro de tiempo**: Reduce el código repetitivo y agiliza la escritura de modelos de datos.
- **Legibilidad**: Los modelos de datos se vuelven más compactos y fáciles de entender.
- **Consistencia**: Los métodos generados siguen un formato estándar.

#### Desventajas

- **Dependencia de una biblioteca externa**: Aunque es ligero, depende de una biblioteca adicional.
- **Compatibilidad**: Puede que algunas herramientas o plugins no funcionen bien con Lombok.
- **Compilación**: Lombok depende de la anotación `@Enable annotation processing` para funcionar en ciertos IDEs.
