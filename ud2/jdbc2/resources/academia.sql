-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS Academia;
USE Academia;

-- Creación de la tabla Alumnos
CREATE TABLE Alumnos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(50) NOT NULL,
                         apellido VARCHAR(50) NOT NULL,
                         fecha_nacimiento DATE NOT NULL
);

-- Creación de la tabla Profesores
CREATE TABLE Profesores (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(50) NOT NULL,
                            apellido VARCHAR(50) NOT NULL,
                            fecha_nacimiento DATE NOT NULL
);

-- Creación de la tabla Asignaturas
CREATE TABLE Asignaturas (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL,
                             alumnos_maximos INT NOT NULL,
                             ausencias_permitidas INT NOT NULL
);

-- Creación de la tabla Matriculas
CREATE TABLE Matriculas (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            alumno_id INT NOT NULL,
                            asignatura_id INT NOT NULL,
                            fecha_matricula DATE NOT NULL,
                            FOREIGN KEY (alumno_id) REFERENCES Alumnos(id),
                            FOREIGN KEY (asignatura_id) REFERENCES Asignaturas(id)
);

-- Creación de la tabla Asignacion_Profesor
CREATE TABLE Asignacion_Profesor (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     profesor_id INT NOT NULL,
                                     asignatura_id INT NOT NULL,
                                     FOREIGN KEY (profesor_id) REFERENCES Profesores(id),
                                     FOREIGN KEY (asignatura_id) REFERENCES Asignaturas(id)
);

-- Creación de la tabla Calificaciones
CREATE TABLE Calificaciones (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                alumno_id INT NOT NULL,
                                asignatura_id INT NOT NULL,
                                profesor_id INT NOT NULL,
                                calificacion DECIMAL(4, 2) NOT NULL,
                                FOREIGN KEY (alumno_id) REFERENCES Alumnos(id),
                                FOREIGN KEY (asignatura_id) REFERENCES Asignaturas(id),
                                FOREIGN KEY (profesor_id) REFERENCES Profesores(id)
                            );