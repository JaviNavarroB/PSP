package dao;

import entity.Asignatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {
    private Connection connection;

    public AsignaturaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarAsignatura(Asignatura asignatura) throws SQLException {
        String sql = "INSERT INTO Asignaturas (nombre, alumnos_maximos, ausencias_permitidas) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, asignatura.getNombre());
            ps.setInt(2, asignatura.getAlumnosMaximos());
            ps.setInt(3, asignatura.getAusenciasPermitidas());
            ps.executeUpdate();
        }
    }

    public List<Asignatura> listarAsignaturas() throws SQLException {
        List<Asignatura> asignaturas = new ArrayList<>();
        String sql = "SELECT * FROM Asignaturas";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Asignatura asignatura = new Asignatura(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("alumnos_maximos"),
                        rs.getInt("ausencias_permitidas")
                );
                asignaturas.add(asignatura);
            }
        }
        return asignaturas;
    }

    public void actualizarAsignatura(Asignatura asignatura) throws SQLException {
        String sql = "UPDATE Asignaturas SET nombre = ?, alumnos_maximos = ?, ausencias_permitidas = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, asignatura.getNombre());
            ps.setInt(2, asignatura.getAlumnosMaximos());
            ps.setInt(3, asignatura.getAusenciasPermitidas());
            ps.setInt(4, asignatura.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarAsignatura(int id) throws SQLException {
        String sql = "DELETE FROM Asignaturas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
