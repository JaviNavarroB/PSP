import java.sql.*;

public class AlumnoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/Academia";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Metodo para insertar un nuevo alumno
    public void insertarAlumno(String nombre, String apellido, Date fechaNacimiento) {
        String sql = "INSERT INTO Alumnos (nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setDate(3, fechaNacimiento);

            int idGenerado=-1;
            pstmt.executeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            if(resultSet.next()){
                idGenerado = resultSet.getInt(1);
            }
            System.out.println("Alumno insertado con id " + idGenerado);

        } catch (SQLException e) {
            System.out.println("Error al insertar el alumno.");
            e.printStackTrace();
        }
    }

    // Metodo para listar todos los alumnos
    public void listarAlumnos() {
        String sql = "SELECT * FROM Alumnos";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Fecha de Nacimiento: " + fechaNacimiento);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los alumnos.");
            e.printStackTrace();
        }
    }

    // Metodo para actualizar un alumno por su ID
    public void actualizarAlumno(int id, String nuevoNombre, String nuevoApellido, Date nuevaFechaNacimiento) {
        String sql = "UPDATE Alumnos SET nombre = ?, apellido = ?, fecha_nacimiento = ? WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoApellido);
            pstmt.setDate(3, nuevaFechaNacimiento);
            pstmt.setInt(4, id);

            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Alumno actualizado. Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Error al actualizar el alumno.");
            e.printStackTrace();
        }
    }

    // Metodo para eliminar un alumno por su ID
    public void eliminarAlumno(int id) {
        String sql = "DELETE FROM Alumnos WHERE id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Alumno eliminado. Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.out.println("Error al eliminar el alumno.");
            e.printStackTrace();
        }
    }
}