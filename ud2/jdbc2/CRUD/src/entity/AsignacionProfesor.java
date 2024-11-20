package entity;

public class AsignacionProfesor {
    private int id;
    private int profesorId;
    private int asignaturaId;

    public AsignacionProfesor(int id, int profesorId, int asignaturaId) {
        this.id = id;
        this.profesorId = profesorId;
        this.asignaturaId = asignaturaId;
    }

    public AsignacionProfesor(int profesorId, int asignaturaId) {
        this.profesorId = profesorId;
        this.asignaturaId = asignaturaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    @Override
    public String toString() {
        return "AsignacionProfesor{id=" + id + ", profesorId=" + profesorId + ", asignaturaId=" + asignaturaId + '}';
    }
}
