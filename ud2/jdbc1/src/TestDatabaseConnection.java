import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/academia"; // Reemplaza con tu base de datos
    private static final String USER = "tu_usuario"; // Reemplaza con tu usuario
    private static final String PASSWORD = "tu_contraseña"; // Reemplaza con tu contraseña

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        } finally {
            // Cerrar la conexión si fue establecida
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
