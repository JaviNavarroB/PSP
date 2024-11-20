package dao;

import entity.Alumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    private Connection connection;

    public AlumnoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO Alumnos (nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, new Date(alumno.getFechaNacimiento().getTime()));
            ps.executeUpdate();
        }
    }

    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumnos";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fecha_nacimiento")
                );
                alumnos.add(alumno);
            }
        }
        return alumnos;
    }

    public void actualizarAlumno(Alumno alumno) throws SQLException {
        String sql = "UPDATE Alumnos SET nombre = ?, apellido = ?, fecha_nacimiento = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, new Date(alumno.getFechaNacimiento().getTime()));
            ps.setInt(4, alumno.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarAlumno(int id) throws SQLException {
        String sql = "DELETE FROM Alumnos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
