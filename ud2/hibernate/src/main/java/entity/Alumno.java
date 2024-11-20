package entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "Alumnos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;


    @OneToMany(mappedBy = "alumno")
    private Set<Calificacion> calificaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "alumno")
    private Set<Matricula> matriculas = new LinkedHashSet<>();

}
