package dao;

import entity.AsignacionProfesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionProfesorDAO {
    private Connection connection;

    public AsignacionProfesorDAO(Connection connection) {
        this.connection = connection;
    }

    public void asignarProfesor(AsignacionProfesor asignacionProfesor) throws SQLException {
        String sql = "INSERT INTO Asignacion_Profesor (profesor_id, asignatura_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, asignacionProfesor.getProfesorId());
            ps.setInt(2, asignacionProfesor.getAsignaturaId());
            ps.executeUpdate();
        }
    }

    public List<AsignacionProfesor> listarAsignaciones() throws SQLException {
        List<AsignacionProfesor> asignaciones = new ArrayList<>();
        String sql = "SELECT * FROM Asignacion_Profesor";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                AsignacionProfesor asignacion = new AsignacionProfesor(
                        rs.getInt("id"),
                        rs.getInt("profesor_id"),
                        rs.getInt("asignatura_id")
                );
                asignaciones.add(asignacion);
            }
        }
        return asignaciones;
    }

    public void actualizarAsignacion(AsignacionProfesor asignacionProfesor) throws SQLException {
        String sql = "UPDATE Asignacion_Profesor SET profesor_id = ?, asignatura_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, asignacionProfesor.getProfesorId());
            ps.setInt(2, asignacionProfesor.getAsignaturaId());
            ps.setInt(3, asignacionProfesor.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarAsignacion(int id) throws SQLException {
        String sql = "DELETE FROM Asignacion_Profesor WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
