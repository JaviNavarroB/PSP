import dao.*;
import entity.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Academia", "root", "123456");

            while (true) {
                mostrarMenuPrincipal();
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        gestionarAlumnos();
                        break;
                    case 2:
                        gestionarProfesores();
                        break;
                    case 3:
                        gestionarAsignaturas();
                        break;
                    case 4:
                        gestionarMatriculas();
                        break;
                    case 5:
                        gestionarCalificaciones();
                        break;
                    case 6:
                        mostrarProfesorYAlumnosDeAsignatura();
                        break;
                    case 7:
                        gestionarAsignacionProfesor();
                        break;
                    case 8:
                        System.out.println("Saliendo del sistema...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\nBienvenido al Sistema de Gestión de Academia\n");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Gestión de Alumnos");
        System.out.println("2. Gestión de Profesores");
        System.out.println("3. Gestión de Asignaturas");
        System.out.println("4. Gestión de Matrículas");
        System.out.println("5. Gestión de Calificaciones");
        System.out.println("6. Mostrar Profesor y Alumnos de Asignatura");
        System.out.println("7. Gestión de Asignación-Profesor");
        System.out.println("8. Salir");
    }

    private static void gestionarAlumnos() {
        try {
            AlumnoDAO alumnoDAO = new AlumnoDAO(connection);
            while (true) {
                System.out.println("\n--- Gestión de Alumnos ---");
                System.out.println("Seleccione una operación:");
                System.out.println("1. Insertar Alumno");
                System.out.println("2. Listar Alumnos");
                System.out.println("3. Actualizar Alumno");
                System.out.println("4. Eliminar Alumno");
                System.out.println("5. Volver al Menú Principal");
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        insertarAlumno(alumnoDAO);
                        break;
                    case 2:
                        listarAlumnos(alumnoDAO);
                        break;
                    case 3:
                        actualizarAlumno(alumnoDAO);
                        break;
                    case 4:
                        eliminarAlumno(alumnoDAO);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarAlumno(AlumnoDAO alumnoDAO) throws SQLException {
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del alumno: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        Alumno alumno = new Alumno(nombre, apellido, Date.valueOf(fechaNacimiento));
        alumnoDAO.insertarAlumno(alumno);
        System.out.println("Alumno insertado exitosamente.");
    }

    private static void listarAlumnos(AlumnoDAO alumnoDAO) throws SQLException {
        List<Alumno> alumnos = alumnoDAO.listarAlumnos();
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    private static void actualizarAlumno(AlumnoDAO alumnoDAO) throws SQLException {
        System.out.print("Ingrese el ID del alumno a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        Alumno alumno = new Alumno(id, nombre, apellido, Date.valueOf(fechaNacimiento));
        alumnoDAO.actualizarAlumno(alumno);
        System.out.println("Alumno actualizado exitosamente.");
    }

    private static void eliminarAlumno(AlumnoDAO alumnoDAO) throws SQLException {
        System.out.print("Ingrese el ID del alumno a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        alumnoDAO.eliminarAlumno(id);
        System.out.println("Alumno eliminado exitosamente.");
    }

    private static void mostrarProfesorYAlumnosDeAsignatura() {
        try {
            System.out.print("Ingrese el ID de la asignatura: ");
            int asignaturaId = Integer.parseInt(scanner.nextLine());

            AsignacionProfesorDAO asignacionProfesorDAO = new AsignacionProfesorDAO(connection);
            List<AsignacionProfesor> asignaciones = asignacionProfesorDAO.listarAsignaciones();
            for (AsignacionProfesor asignacion : asignaciones) {
                if (asignacion.getAsignaturaId() == asignaturaId) {
                    System.out.println("Profesor asignado: " + asignacion.getProfesorId());
                    break;
                }
            }

            MatriculaDAO matriculaDAO = new MatriculaDAO(connection);
            List<Matricula> matriculas = matriculaDAO.listarMatriculas();
            System.out.println("Alumnos matriculados:");
            for (Matricula matricula : matriculas) {
                if (matricula.getAsignaturaId() == asignaturaId) {
                    System.out.println("Alumno ID: " + matricula.getAlumnoId());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void gestionarProfesores() {
        try {
            ProfesorDAO profesorDAO = new ProfesorDAO(connection);
            while (true) {
                System.out.println("\n--- Gestión de Profesores ---");
                System.out.println("Seleccione una operación:");
                System.out.println("1. Insertar Profesor");
                System.out.println("2. Listar Profesores");
                System.out.println("3. Actualizar Profesor");
                System.out.println("4. Eliminar Profesor");
                System.out.println("5. Volver al Menú Principal");
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        insertarProfesor(profesorDAO);
                        break;
                    case 2:
                        listarProfesores(profesorDAO);
                        break;
                    case 3:
                        actualizarProfesor(profesorDAO);
                        break;
                    case 4:
                        eliminarProfesor(profesorDAO);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarProfesor(ProfesorDAO profesorDAO) throws SQLException {
        System.out.print("Ingrese el nombre del profesor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del profesor: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        Profesor profesor = new Profesor(nombre, apellido, Date.valueOf(fechaNacimiento));
        profesorDAO.insertarProfesor(profesor);
        System.out.println("Profesor insertado exitosamente.");
    }

    private static void listarProfesores(ProfesorDAO profesorDAO) throws SQLException {
        List<Profesor> profesores = profesorDAO.listarProfesores();
        for (Profesor profesor : profesores) {
            System.out.println(profesor);
        }
    }

    private static void actualizarProfesor(ProfesorDAO profesorDAO) throws SQLException {
        System.out.print("Ingrese el ID del profesor a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        Profesor profesor = new Profesor(id, nombre, apellido, Date.valueOf(fechaNacimiento));
        profesorDAO.actualizarProfesor(profesor);
        System.out.println("Profesor actualizado exitosamente.");
    }

    private static void eliminarProfesor(ProfesorDAO profesorDAO) throws SQLException {
        System.out.print("Ingrese el ID del profesor a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        profesorDAO.eliminarProfesor(id);
        System.out.println("Profesor eliminado exitosamente.");
    }

    private static void gestionarAsignaturas() {
        try {
            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(connection);
            while (true) {
                System.out.println("\n--- Gestión de Asignaturas ---");
                System.out.println("Seleccione una operación:");
                System.out.println("1. Insertar Asignatura");
                System.out.println("2. Listar Asignaturas");
                System.out.println("3. Actualizar Asignatura");
                System.out.println("4. Eliminar Asignatura");
                System.out.println("5. Volver al Menú Principal");
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        insertarAsignatura(asignaturaDAO);
                        break;
                    case 2:
                        listarAsignaturas(asignaturaDAO);
                        break;
                    case 3:
                        actualizarAsignatura(asignaturaDAO);
                        break;
                    case 4:
                        eliminarAsignatura(asignaturaDAO);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarAsignatura(AsignaturaDAO asignaturaDAO) throws SQLException {
        System.out.print("Ingrese el nombre de la asignatura: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese los alumnos máximos: ");
        int alumnosMaximos = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese las ausencias permitidas: ");
        int ausenciasPermitidas = Integer.parseInt(scanner.nextLine());

        Asignatura asignatura = new Asignatura(nombre, alumnosMaximos, ausenciasPermitidas);
        asignaturaDAO.insertarAsignatura(asignatura);
        System.out.println("Asignatura insertada exitosamente.");
    }

    private static void listarAsignaturas(AsignaturaDAO asignaturaDAO) throws SQLException {
        List<Asignatura> asignaturas = asignaturaDAO.listarAsignaturas();
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura);
        }
    }

    private static void actualizarAsignatura(AsignaturaDAO asignaturaDAO) throws SQLException {
        System.out.print("Ingrese el ID de la asignatura a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese los nuevos alumnos máximos: ");
        int alumnosMaximos = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese las nuevas ausencias permitidas: ");
        int ausenciasPermitidas = Integer.parseInt(scanner.nextLine());

        Asignatura asignatura = new Asignatura(id, nombre, alumnosMaximos, ausenciasPermitidas);
        asignaturaDAO.actualizarAsignatura(asignatura);
        System.out.println("Asignatura actualizada exitosamente.");
    }

    private static void eliminarAsignatura(AsignaturaDAO asignaturaDAO) throws SQLException {
        System.out.print("Ingrese el ID de la asignatura a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        asignaturaDAO.eliminarAsignatura(id);
        System.out.println("Asignatura eliminada exitosamente.");
    }

    private static void gestionarMatriculas() {
        try {
            MatriculaDAO matriculaDAO = new MatriculaDAO(connection);
            while (true) {
                System.out.println("\n--- Gestión de Matrículas ---");
                System.out.println("Seleccione una operación:");
                System.out.println("1. Matricular Alumno");
                System.out.println("2. Listar Matrículas");
                System.out.println("3. Actualizar Matrícula");
                System.out.println("4. Eliminar Matrícula");
                System.out.println("5. Volver al Menú Principal");
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        matricularAlumno(matriculaDAO);
                        break;
                    case 2:
                        listarMatriculas(matriculaDAO);
                        break;
                    case 3:
                        actualizarMatricula(matriculaDAO);
                        break;
                    case 4:
                        eliminarMatricula(matriculaDAO);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void matricularAlumno(MatriculaDAO matriculaDAO) throws SQLException {
        System.out.print("Ingrese el ID del alumno: ");
        int alumnoId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID de la asignatura: ");
        int asignaturaId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la fecha de matrícula (YYYY-MM-DD): ");
        String fechaMatricula = scanner.nextLine();

        Matricula matricula = new Matricula(alumnoId, asignaturaId, Date.valueOf(fechaMatricula));
        matriculaDAO.matricularAlumno(matricula);
        System.out.println("Matrícula registrada exitosamente.");
    }

    private static void listarMatriculas(MatriculaDAO matriculaDAO) throws SQLException {
        List<Matricula> matriculas = matriculaDAO.listarMatriculas();
        for (Matricula matricula : matriculas) {
            System.out.println(matricula);
        }
    }

    private static void actualizarMatricula(MatriculaDAO matriculaDAO) throws SQLException {
        System.out.print("Ingrese el ID de la matrícula a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo ID del alumno: ");
        int alumnoId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo ID de la asignatura: ");
        int asignaturaId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la nueva fecha de matrícula (YYYY-MM-DD): ");
        String fechaMatricula = scanner.nextLine();

        Matricula matricula = new Matricula(id, alumnoId, asignaturaId, Date.valueOf(fechaMatricula));
        matriculaDAO.actualizarMatricula(matricula);
        System.out.println("Matrícula actualizada exitosamente.");
    }

    private static void eliminarMatricula(MatriculaDAO matriculaDAO) throws SQLException {
        System.out.print("Ingrese el ID de la matrícula a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        matriculaDAO.eliminarMatricula(id);
        System.out.println("Matrícula eliminada exitosamente.");
    }

    private static void gestionarCalificaciones() {
        try {
            CalificacionDAO calificacionDAO = new CalificacionDAO(connection);
            while (true) {
                System.out.println("\n--- Gestión de Calificaciones ---");
                System.out.println("Seleccione una operación:");
                System.out.println("1. Registrar Calificación");
                System.out.println("2. Listar Calificaciones");
                System.out.println("3. Actualizar Calificación");
                System.out.println("4. Eliminar Calificación");
                System.out.println("5. Volver al Menú Principal");
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        registrarCalificacion(calificacionDAO);
                        break;
                    case 2:
                        listarCalificaciones(calificacionDAO);
                        break;
                    case 3:
                        actualizarCalificacion(calificacionDAO);
                        break;
                    case 4:
                        eliminarCalificacion(calificacionDAO);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registrarCalificacion(CalificacionDAO calificacionDAO) throws SQLException {
        System.out.print("Ingrese el ID del alumno: ");
        int alumnoId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID de la asignatura: ");
        int asignaturaId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID del profesor: ");
        int profesorId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la calificación (0-10): ");
        double calificacion = Double.parseDouble(scanner.nextLine());

        Calificacion calificacionObj = new Calificacion(alumnoId, asignaturaId, profesorId, new BigDecimal(calificacion));
        calificacionDAO.registrarCalificacion(calificacionObj);
        System.out.println("Calificación registrada exitosamente.");
    }

    private static void listarCalificaciones(CalificacionDAO calificacionDAO) throws SQLException {
        List<Calificacion> calificaciones = calificacionDAO.listarCalificaciones();
        for (Calificacion calificacion : calificaciones) {
            System.out.println(calificacion);
        }
    }

    private static void actualizarCalificacion(CalificacionDAO calificacionDAO) throws SQLException {
        System.out.print("Ingrese el ID de la calificación a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo ID del alumno: ");
        int alumnoId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo ID de la asignatura: ");
        int asignaturaId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo ID del profesor: ");
        int profesorId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la nueva calificación (0-10): ");
        double calificacion = Double.parseDouble(scanner.nextLine());

        Calificacion calificacionObj = new Calificacion(id, alumnoId, asignaturaId, profesorId, new BigDecimal(calificacion));
        calificacionDAO.actualizarCalificacion(calificacionObj);
        System.out.println("Calificación actualizada exitosamente.");
    }

    private static void eliminarCalificacion(CalificacionDAO calificacionDAO) throws SQLException {
        System.out.print("Ingrese el ID de la calificación a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        calificacionDAO.eliminarCalificacion(id);
        System.out.println("Calificación eliminada exitosamente.");
    }


    private static void gestionarAsignacionProfesor() {
        try {
            AsignacionProfesorDAO asignacionProfesorDAO = new AsignacionProfesorDAO(connection);
            while (true) {
                System.out.println("\n--- Gestión de Asignación de Profesor ---");
                System.out.println("Seleccione una operación:");
                System.out.println("1. Asignar Profesor a Asignatura");
                System.out.println("2. Listar Asignaciones de Profesor");
                System.out.println("3. Eliminar Asignación de Profesor");
                System.out.println("4. Volver al Menú Principal");
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        asignarProfesorASignatura(asignacionProfesorDAO);
                        break;
                    case 2:
                        listarAsignacionesProfesor(asignacionProfesorDAO);
                        break;
                    case 3:
                        eliminarAsignacionProfesor(asignacionProfesorDAO);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void asignarProfesorASignatura(AsignacionProfesorDAO asignacionProfesorDAO) throws SQLException {
        System.out.print("Ingrese el ID del profesor: ");
        int profesorId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID de la asignatura: ");
        int asignaturaId = Integer.parseInt(scanner.nextLine());

        AsignacionProfesor asignacionProfesor = new AsignacionProfesor(profesorId, asignaturaId);
        asignacionProfesorDAO.asignarProfesor(asignacionProfesor);
        System.out.println("Profesor asignado a la asignatura exitosamente.");
    }

    private static void listarAsignacionesProfesor(AsignacionProfesorDAO asignacionProfesorDAO) throws SQLException {
        List<AsignacionProfesor> asignaciones = asignacionProfesorDAO.listarAsignaciones();
        for (AsignacionProfesor asignacion : asignaciones) {
            System.out.println(asignacion);
        }
    }

    private static void eliminarAsignacionProfesor(AsignacionProfesorDAO asignacionProfesorDAO) throws SQLException {
        System.out.print("Ingrese el ID de la asignación de profesor a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        asignacionProfesorDAO.eliminarAsignacion(id);
        System.out.println("Asignación de profesor eliminada exitosamente.");
    }

}
