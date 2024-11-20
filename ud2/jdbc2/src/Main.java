import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();

        // Insertar un nuevo alumno
        alumnoDAO.insertarAlumno("Juan", "Pérez", Date.valueOf("2000-05-14"));

        // Listar todos los alumnos
        alumnoDAO.listarAlumnos();

        // Actualizar un alumno
        alumnoDAO.actualizarAlumno(1, "Ana", "López", Date.valueOf("1999-07-22"));

        // Eliminar un alumno
        alumnoDAO.eliminarAlumno(1);
    }
}