package entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Asignacion_Profesor")
public class AsignacionProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;
}
