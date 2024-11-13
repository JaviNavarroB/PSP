package entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "alumnos_maximos", nullable = false)
    private int alumnosMaximos;

    @Column(name = "ausencias_permitidas", nullable = false)
    private int ausenciasPermitidas;

    @OneToMany(mappedBy = "asignatura_id")
    private Set<Matricula> matriculas;

    @OneToMany(mappedBy = "asignatura_id")
    private Set<Calificacion> calificaciones;

    @OneToMany(mappedBy = "asignatura_id")
    private Set<AsignacionProfesor> asignaciones;
}
