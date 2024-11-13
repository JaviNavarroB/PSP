package entity;

import java.math.BigDecimal;

public class Calificacion {
    private int id;
    private int alumnoId;
    private int asignaturaId;
    private int profesorId;
    private BigDecimal calificacion;

    public Calificacion(int id, int alumnoId, int asignaturaId, int profesorId, BigDecimal calificacion) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
        this.profesorId = profesorId;
        this.calificacion = calificacion;
    }

    public Calificacion(int alumnoId, int asignaturaId, int profesorId, BigDecimal calificacion) {
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
        this.profesorId = profesorId;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Calificación{id=" + id + ", alumnoId=" + alumnoId + ", asignaturaId=" + asignaturaId + ", profesorId=" + profesorId + ", calificacion=" + calificacion + '}';
    }
}
