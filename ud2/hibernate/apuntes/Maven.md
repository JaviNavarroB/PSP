# Maven e IntelliJ
## 1. ¿Qué es Maven?
Maven es una herramienta de gestión de proyectos y construcción en Java que facilita el manejo de dependencias, construcción, pruebas y despliegue de proyectos. Algunas de sus características principales incluyen:
- **Gestión de dependencias**: Control centralizado de bibliotecas y versiones de dependencias, asegurando compatibilidad y actualizaciones.
- **Estructura de proyecto estándar**: Usa una estructura convencional (`src/main/java`, `src/test/java`), promoviendo organización y consistencia.
- **Ciclo de vida del build**: Fases como `validate`, `compile`, `test`, `package`, `install`, `deploy` que simplifican el desarrollo.
- **Plugins**: Extensibilidad mediante plugins que ejecutan tareas en cada fase del ciclo de vida.

## 2. Configuración de un Proyecto Maven en IntelliJ

### Crear un Proyecto Maven
1. Abre IntelliJ IDEA y selecciona **New Project**.
2. Elige **Maven** en la lista de opciones y configura:
   - **GroupId**: Identificador del grupo de proyectos, como `com.academia`.
   - **ArtifactId**: Nombre del proyecto, como `academia-proyecto`.
3. Selecciona el SDK de Java y crea el proyecto.

### Estructura del Proyecto
Maven genera una estructura estándar al crear el proyecto:
```
academia-proyecto
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       ├── java
│       └── resources
```

## 3. Archivo `pom.xml`: Gestión y Configuración de Dependencias

### Configuración Básica de `pom.xml`
El archivo `pom.xml` es el núcleo de Maven. Aquí se especifican:
- **Propiedades** (como versiones de Java),
- **Dependencias** (bibliotecas externas),
- **Plugins** para extender funcionalidades.

Ejemplo de configuración básica para un proyecto que usa Hibernate y MySQL:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.academia</groupId>
    <artifactId>academia-proyecto</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.6.15.Final</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.27</version>
        </dependency>
    </dependencies>
</project>
```

## 4. Ciclo de Vida de Maven y Comandos Básicos

### Comandos Principales
- `clean`: Limpia el directorio `target`, eliminando archivos generados en compilaciones anteriores.
- `compile`: Compila el código fuente.
- `test`: Ejecuta las pruebas.
- `package`: Empaqueta el proyecto en un archivo `.jar`.
- `install`: Instala el proyecto en el repositorio local.
- `deploy`: Despliega el proyecto en un repositorio remoto.

En IntelliJ, estos comandos se encuentran en el panel de **Maven**, donde puedes ejecutarlos directamente.

## 5. Plugins en Maven

Los plugins permiten extender las funcionalidades de Maven. Ejemplo de configuración de un plugin de compilación que especifica la versión de Java:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>11</source>
                <target>11</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 6. Repositorios en Maven
Maven utiliza repositorios (locales, centrales y remotos) para almacenar y buscar dependencias.

- **Repositorio Local**: En tu máquina (normalmente en `~/.m2/repository`).
- **Repositorio Central**: Repositorio de Maven por defecto desde donde se descargan las dependencias.
- **Repositorios Remotos**: Repositorios adicionales configurados en `pom.xml` para obtener dependencias específicas.

Ejemplo de configuración de un repositorio remoto:
```xml
<repositories>
    <repository>
        <id>my-repo</id>
        <url>http://example.com/maven2</url>
    </repository>
</repositories>
```

## 7. Buenas Prácticas en Proyectos Maven

- **Organización de Dependencias**: Mantén actualizadas las versiones de dependencias.
- **Uso de Plugins**: Aprovecha plugins como `maven-surefire-plugin` para ejecutar pruebas y `maven-jar-plugin` para empaquetar el proyecto.
- **Propiedades Globales**: Define propiedades para versiones y configura variables reutilizables en `<properties>`.
- **Ejecución de Perfiles**: Usa perfiles en `pom.xml` para configurar entornos específicos, como producción y desarrollo.

## Ejemplo de Perfil de Entorno
```xml
<profiles>
    <profile>
        <id>produccion</id>
        <properties>
            <db.url>jdbc:mysql://prod.example.com:3306/academia</db.url>
        </properties>
    </profile>
</profiles>
```

Para seleccionar un perfil de Maven, ejecuta el siguiente comando:
```bash
mvn clean install -Pproduccion
```

## 8. Trabajar con Dependencias Transitorias
Maven incluye dependencias transitivas automáticamente. Por ejemplo, si `hibernate-core` requiere `antlr`, Maven también descargará `antlr` como dependencia.