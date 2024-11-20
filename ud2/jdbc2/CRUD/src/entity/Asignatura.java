package entity;

public class Asignatura {
    private int id;
    private String nombre;
    private int alumnosMaximos;
    private int ausenciasPermitidas;

    public Asignatura(int id, String nombre, int alumnosMaximos, int ausenciasPermitidas) {
        this.id = id;
        this.nombre = nombre;
        this.alumnosMaximos = alumnosMaximos;
        this.ausenciasPermitidas = ausenciasPermitidas;
    }

    public Asignatura(String nombre, int alumnosMaximos, int ausenciasPermitidas) {
        this.nombre = nombre;
        this.alumnosMaximos = alumnosMaximos;
        this.ausenciasPermitidas = ausenciasPermitidas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAlumnosMaximos() {
        return alumnosMaximos;
    }

    public void setAlumnosMaximos(int alumnosMaximos) {
        this.alumnosMaximos = alumnosMaximos;
    }

    public int getAusenciasPermitidas() {
        return ausenciasPermitidas;
    }

    public void setAusenciasPermitidas(int ausenciasPermitidas) {
        this.ausenciasPermitidas = ausenciasPermitidas;
    }

    @Override
    public String toString() {
        return "Asignatura{id=" + id + ", nombre='" + nombre + "', alumnosMaximos=" + alumnosMaximos + ", ausenciasPermitidas=" + ausenciasPermitidas + '}';
    }
}
