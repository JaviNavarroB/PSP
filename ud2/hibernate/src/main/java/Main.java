import dao.AlumnoDAO;
import entity.Alumno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class Main {

    private static AlumnoDAO alumnoDAO;

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        inicializarDAOs(sessionFactory);

        alumnoDAO.insertarAlumno(Alumno.builder().nombre("Juan").apellido("Perez").fechaNacimiento(new Date()).build());
    }

    private static void inicializarDAOs(SessionFactory sessionFactory) {
        alumnoDAO = new AlumnoDAO(sessionFactory);
    }
}
