package entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @OneToMany(mappedBy = "profesor_id")
    private Set<AsignacionProfesor> asignaciones;

    @OneToMany(mappedBy = "profesor_id")
    private Set<Calificacion> calificaciones;
}
