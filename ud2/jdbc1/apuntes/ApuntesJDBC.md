## 1. Conceptos Básicos de Bases de Datos Relacionales

### ¿Qué es una base de datos relacional?
- Una **base de datos relacional (RDBMS)** es un sistema de almacenamiento de datos que organiza la información en **tablas**. Cada tabla representa una **entidad** (por ejemplo, usuarios, productos o pedidos) y se compone de **filas y columnas**. Las filas representan **registros individuales** (cada uno con un identificador único), mientras que las columnas representan los **atributos** o características del registro.
- Un **RDBMS** sigue un **modelo basado en relaciones**, donde las tablas pueden estar relacionadas mediante **claves primarias y foráneas** para mantener la consistencia y la integridad de los datos. Por ejemplo, una tabla "Pedidos" puede estar relacionada con una tabla "Clientes" a través de una columna que almacena el identificador del cliente.
- **Ejemplos de RDBMS** comunes incluyen:
    - **MySQL**: Un sistema de gestión de bases de datos relacional de código abierto muy popular.
    - **Oracle Database**: Un RDBMS comercial con características avanzadas para grandes aplicaciones empresariales.
    - **PostgreSQL**: Un sistema de base de datos de código abierto con soporte para características avanzadas como tipos de datos personalizados y extensiones.
    - **Microsoft SQL Server**: Un sistema de base de datos comercial con integración profunda con el ecosistema de Microsoft.
- **Ventajas de usar bases de datos relacionales**:
    - **Consistencia de datos**: Gracias a las **transacciones**, se garantiza que los cambios a la base de datos ocurren de manera atómica y consistente.
    - **Integridad referencial**: Permite mantener la coherencia entre tablas relacionadas mediante restricciones como las **claves foráneas**.
    - **Consultas estructuradas**: Usando **SQL (Structured Query Language)**, los desarrolladores pueden ejecutar consultas complejas para recuperar datos específicos.

### Componentes de una base de datos relacional
- **Tablas**: Son la principal estructura de almacenamiento en una base de datos relacional. Cada tabla tiene un nombre único y almacena datos sobre una entidad específica (por ejemplo, clientes, productos).
- **Registros (Filas)**: Cada fila en una tabla representa una instancia específica de la entidad que se está almacenando. Por ejemplo, en una tabla de clientes, cada fila corresponde a un cliente diferente.
- **Columnas**: Las columnas representan los atributos de la entidad. Por ejemplo, en una tabla de clientes, las columnas pueden ser "nombre", "dirección", "correo electrónico".
- **Tipos de datos**: Cada columna tiene un tipo de dato específico, como `VARCHAR` (texto), `INT` (números enteros), `DATE` (fechas), etc., que determina qué tipo de datos puede almacenar.

## 2. Instalación y Configuración de MySQL

### Instalación de MySQL
- **MySQL** es un sistema de gestión de bases de datos gratuito y de código abierto que se utiliza ampliamente para el almacenamiento y la recuperación de datos.
- Para instalar MySQL:
    1. Descargar el instalador de MySQL desde su sitio web oficial.
    2. Elegir la versión adecuada para el sistema operativo (Windows, macOS, Linux).
    3. Seguir el proceso de instalación, que puede incluir la configuración de un nombre de usuario y una contraseña para el **usuario root**.
- Se recomienda instalar también **MySQL Workbench**, una herramienta gráfica que facilita la administración y el desarrollo de bases de datos.

### Configuración inicial de MySQL
- Después de la instalación, es necesario configurar el entorno:
    - **Establecer una contraseña segura** para el usuario root.
    - **Crear usuarios adicionales** con permisos específicos para mejorar la seguridad.
    - **Configurar la conexión por red**, si la base de datos va a ser accedida remotamente.

## 3. ¿Qué es JDBC? Ventajas y Desventajas

### Definición de JDBC
- **JDBC (Java Database Connectivity)** es una **API (Application Programming Interface)** que permite a las aplicaciones Java interactuar con bases de datos relacionales.
- La API de JDBC proporciona un conjunto estándar de clases e interfaces que los desarrolladores pueden utilizar para **conectar, ejecutar consultas y gestionar los resultados** de una base de datos.
- **Arquitectura de JDBC**:
    - **Driver Manager**: Administra una lista de controladores de base de datos disponibles para la aplicación.
    - **Drivers JDBC**: Son controladores específicos que permiten la comunicación con un tipo particular de base de datos (por ejemplo, MySQL, Oracle).
    - **Conexión**: Representa una conexión abierta con la base de datos.
    - **Sentencias y Resultados**: Permiten ejecutar consultas y manejar los resultados.

### Ventajas y Desventajas de JDBC
- **Ventajas**:
    - **Portabilidad**: Al ser una API estándar de Java, permite que el mismo código funcione en distintas bases de datos con mínimos cambios.
    - **Soporte para múltiples bases de datos**: Existen controladores JDBC para casi todos los RDBMS del mercado.
    - **Integración con herramientas y frameworks Java**: JDBC se utiliza comúnmente en frameworks como Hibernate o Spring.
- **Desventajas**:
    - **Código verboso**: Comparado con tecnologías más modernas, el código JDBC puede ser más extenso y menos intuitivo.
    - **Gestión manual de recursos**: Requiere que el desarrollador cierre explícitamente conexiones, sentencias y resultados para evitar fugas de recursos.

## 4. Arquitectura de JDBC
- La **arquitectura de JDBC** se basa en varios componentes clave que interactúan entre sí para proporcionar acceso a los datos:
    - **Driver Manager**: Actúa como un administrador para manejar diferentes controladores de base de datos. Detecta automáticamente qué controlador debe usarse para conectarse a una base de datos específica.
    - **Controladores JDBC**: Son bibliotecas específicas para cada tipo de base de datos que permiten la comunicación entre la aplicación Java y el motor de base de datos.
    - **Conexión**: Una vez seleccionado el controlador adecuado, se establece una conexión con la base de datos.
    - **Sentencias (Statement, PreparedStatement, CallableStatement)**: Se utilizan para enviar consultas SQL a la base de datos. `Statement` es la forma más básica, `PreparedStatement` permite consultas parametrizadas y `CallableStatement` se utiliza para llamar a procedimientos almacenados.
    - **ResultSet**: Es el objeto que contiene los datos devueltos por una consulta `SELECT`.