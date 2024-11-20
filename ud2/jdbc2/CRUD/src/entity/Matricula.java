package entity;

import java.util.Date;

public class Matricula {
    private int id;
    private int alumnoId;
    private int asignaturaId;
    private Date fechaMatricula;

    public Matricula(int id, int alumnoId, int asignaturaId, Date fechaMatricula) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
        this.fechaMatricula = fechaMatricula;
    }

    public Matricula(int alumnoId, int asignaturaId, Date fechaMatricula) {
        this.alumnoId = alumnoId;
        this.asignaturaId = asignaturaId;
        this.fechaMatricula = fechaMatricula;
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

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    @Override
    public String toString() {
        return "Matrícula{id=" + id + ", alumnoId=" + alumnoId + ", asignaturaId=" + asignaturaId + ", fechaMatricula=" + fechaMatricula + '}';
    }
}
