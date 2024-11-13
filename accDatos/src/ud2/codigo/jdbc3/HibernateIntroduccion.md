### **1. Crear un Proyecto en IntelliJ IDEA**

1. **Abre IntelliJ IDEA** y selecciona la opción **Create New Project**.
2. En la ventana de creación de proyecto, selecciona **Maven** (si prefieres usar **Gradle**, la configuración sería similar).
3. Marca la opción **Create from archetype** si deseas trabajar con plantillas predeterminadas de Maven.
4. Especifica los siguientes detalles del proyecto:
   - **GroupId**: `com.academia`
   - **ArtifactId**: `hibernate-academia`
   - **Version**: `1.0-SNAPSHOT`
5. **Haz clic en Finish**.

### **2. Configurar el archivo `pom.xml`**

#### 2.1. Añadir las Dependencias de Hibernate

Para integrar Hibernate en tu proyecto Maven, debes agregar las dependencias en el archivo `pom.xml`.

```xml
<dependencies>
    <!-- Hibernate Core -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.10.Final</version>
    </dependency>

    <!-- MySQL Connector (para conectarse a la base de datos) -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>

    <!-- JPA API -->
    <dependency>
        <groupId>javax.persistence</groupId>
        <artifactId>javax.persistence-api</artifactId>
        <version>2.2</version>
    </dependency>

    <!-- Hibernate EntityManager -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>5.6.10.Final</version>
    </dependency>
</dependencies>
```

Estas dependencias incluyen el núcleo de Hibernate, el conector de MySQL y la API de Java Persistence (JPA).

### **3. Crear el archivo `hibernate.cfg.xml`**

Este archivo de configuración le indica a Hibernate cómo conectarse a la base de datos y cómo debe comportarse. Colócalo en el directorio `src/main/resources`.

#### 3.1. Configuración básica del archivo `hibernate.cfg.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- Configuración JDBC -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/Academia?useSSL=false&amp;serverTimezone=UTC</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">password</property>

        <!-- Habilitar la impresión de las sentencias SQL generadas por Hibernate -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>

        <!-- Configuración de la creación y actualización de la base de datos -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Pool de conexiones -->
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>
        <property name="hibernate.c3p0.timeout">300</property>
    </session-factory>
</hibernate-configuration>
```

- **`hibernate.dialect`**: Especifica el dialecto de la base de datos (en este caso, MySQL).
- **`hibernate.connection.url`**: Define la URL de conexión a la base de datos.
- **`hibernate.hbm2ddl.auto`**: El valor `update` permite que Hibernate maneje la creación y actualización de las tablas según las entidades definidas.

### **4. Crear la Entidad `Alumno`**

En este ejemplo, solo vamos a trabajar con la entidad **Alumno**. Esta clase representará la tabla `Alumnos` en la base de datos.

#### 4.1. Clase `Alumno.java`

```java
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
```

- **`@Entity`**: Define que esta clase es una entidad JPA que se mapea con una tabla de base de datos.
- **`@Id`**: Marca el campo `id` como la clave primaria de la entidad.
- **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: Indica que el valor del `id` se generará automáticamente en la base de datos.
- **`@Column(name = "nombre")`**: Define el nombre de la columna en la base de datos correspondiente al atributo.

### **5. Crear el Código para Gestionar la Persistencia de Datos**

Ahora vamos a crear el código que interactuará con la base de datos utilizando Hibernate. Vamos a utilizar el patrón de sesión de Hibernate para crear un **CRUD** (Crear, Leer, Actualizar y Eliminar).

#### 5.1. Clase `Main.java`

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Crear la sesión de Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Alumno.class) // Añadir la entidad Alumno
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // Crear un nuevo alumno
            Alumno alumno = new Alumno();
            alumno.setNombre("Juan");
            alumno.setApellido("Perez");
            alumno.setFechaNacimiento(new Date());

            // Iniciar la transacción
            session.beginTransaction();

            // Guardar el alumno en la base de datos
            session.save(alumno);

            // Commit de la transacción
            session.getTransaction().commit();

            System.out.println("Alumno guardado: " + alumno);

            // Obtener un alumno usando su ID
            session = factory.getCurrentSession();
            session.beginTransaction();

            // Consultar el alumno por su ID
            Alumno alumnoConsultado = session.get(Alumno.class, alumno.getId());

            System.out.println("Alumno consultado: " + alumnoConsultado);

            // Commit de la transacción
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
```

### **6. Explicación del Código en `Main.java`**

1. **Crear la sesión de Hibernate**: Se crea un `SessionFactory` utilizando la configuración definida en `hibernate.cfg.xml` y se añade la clase `Alumno` como entidad.
2. **Crear un nuevo `Alumno`**: Se crea un objeto `Alumno` con nombre, apellido y fecha de nacimiento.
3. **Iniciar una transacción**: Hibernate requiere que las operaciones de base de datos se realicen dentro de una transacción.
4. **Guardar el `Alumno`**: Se guarda el objeto `Alumno` utilizando el método `session.save()`.
5. **Consultar un `Alumno`**: Se obtiene el `Alumno` por su ID con el método `session.get()`.
6. **Commit de la transacción**: Se finaliza la transacción y se guarda todo en la base de datos.

### **7. Ejecutar el Proyecto**

Una vez que hayas configurado todo, puedes ejecutar el proyecto desde IntelliJ IDEA:

1. **Haz clic derecho en la clase `Main`** y selecciona **Run 'Main'**.
2. Verás que Hibernate se conecta a la base de datos **Academia**, guarda un nuevo `Alumno` y lo consulta posteriormente.

---