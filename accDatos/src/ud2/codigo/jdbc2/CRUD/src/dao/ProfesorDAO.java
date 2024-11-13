package dao;

import entity.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {
    private Connection connection;

    public ProfesorDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarProfesor(Profesor profesor) throws SQLException {
        String sql = "INSERT INTO Profesores (nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setDate(3, new java.sql.Date(profesor.getFechaNacimiento().getTime()));
            ps.executeUpdate();
        }
    }

    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM Profesores";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Profesor profesor = new Profesor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fecha_nacimiento")
                );
                profesores.add(profesor);
            }
        }
        return profesores;
    }

    public void actualizarProfesor(Profesor profesor) throws SQLException {
        String sql = "UPDATE Profesores SET nombre = ?, apellido = ?, fecha_nacimiento = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setDate(3, new java.sql.Date(profesor.getFechaNacimiento().getTime()));
            ps.setInt(4, profesor.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarProfesor(int id) throws SQLException {
        String sql = "DELETE FROM Profesores WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
