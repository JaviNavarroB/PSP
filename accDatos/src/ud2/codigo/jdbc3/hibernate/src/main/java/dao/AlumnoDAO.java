package dao;

import entity.Alumno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AlumnoDAO {

    private SessionFactory sessionFactory;

    public AlumnoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Crear un nuevo alumno
    public void insertarAlumno(Alumno alumno) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(alumno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Obtener un alumno por ID
    public Alumno obtenerAlumnoPorId(int id) {
        Session session = sessionFactory.openSession();
        Alumno alumno = null;
        try {
            alumno = session.get(Alumno.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return alumno;
    }

    // Listar todos los alumnos
    public List<Alumno> listarAlumnos() {
        Session session = sessionFactory.openSession();
        List<Alumno> alumnos = null;
        try {
            Query<Alumno> query = session.createQuery("FROM Alumno", Alumno.class);
            alumnos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return alumnos;
    }

    // Actualizar un alumno
    public void actualizarAlumno(Alumno alumno) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(alumno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Eliminar un alumno
    public void eliminarAlumno(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) {
                session.delete(alumno);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
