package dao;

import entity.Calificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDAO {
    private Connection connection;

    public CalificacionDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarCalificacion(Calificacion calificacion) throws SQLException {
        String sql = "INSERT INTO Calificaciones (alumno_id, asignatura_id, profesor_id, calificacion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, calificacion.getAlumnoId());
            ps.setInt(2, calificacion.getAsignaturaId());
            ps.setInt(3, calificacion.getProfesorId());
            ps.setBigDecimal(4, calificacion.getCalificacion());
            ps.executeUpdate();
        }
    }

    public List<Calificacion> listarCalificaciones() throws SQLException {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Calificaciones";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Calificacion calificacion = new Calificacion(
                        rs.getInt("id"),
                        rs.getInt("alumno_id"),
                        rs.getInt("asignatura_id"),
                        rs.getInt("profesor_id"),
                        rs.getBigDecimal("calificacion")
                );
                calificaciones.add(calificacion);
            }
        }
        return calificaciones;
    }

    public void actualizarCalificacion(Calificacion calificacion) throws SQLException {
        String sql = "UPDATE Calificaciones SET alumno_id = ?, asignatura_id = ?, profesor_id = ?, calificacion = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, calificacion.getAlumnoId());
            ps.setInt(2, calificacion.getAsignaturaId());
            ps.setInt(3, calificacion.getProfesorId());
            ps.setBigDecimal(4, calificacion.getCalificacion());
            ps.setInt(5, calificacion.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarCalificacion(int id) throws SQLException {
        String sql = "DELETE FROM Calificaciones WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
