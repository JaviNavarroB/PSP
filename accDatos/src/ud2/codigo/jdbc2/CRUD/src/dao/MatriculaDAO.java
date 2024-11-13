package dao;

import entity.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    private Connection connection;

    public MatriculaDAO(Connection connection) {
        this.connection = connection;
    }

    public void matricularAlumno(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO Matriculas (alumno_id, asignatura_id, fecha_matricula) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, matricula.getAlumnoId());
            ps.setInt(2, matricula.getAsignaturaId());
            ps.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            ps.executeUpdate();
        }
    }

    public List<Matricula> listarMatriculas() throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM Matriculas";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Matricula matricula = new Matricula(
                        rs.getInt("id"),
                        rs.getInt("alumno_id"),
                        rs.getInt("asignatura_id"),
                        rs.getDate("fecha_matricula")
                );
                matriculas.add(matricula);
            }
        }
        return matriculas;
    }

    public void actualizarMatricula(Matricula matricula) throws SQLException {
        String sql = "UPDATE Matriculas SET alumno_id = ?, asignatura_id = ?, fecha_matricula = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, matricula.getAlumnoId());
            ps.setInt(2, matricula.getAsignaturaId());
            ps.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            ps.setInt(4, matricula.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarMatricula(int id) throws SQLException {
        String sql = "DELETE FROM Matriculas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
