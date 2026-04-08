use terpel;

INSERT INTO departamentos (nombre) VALUES ("Antioquia"), ("Bogotá D.C."), ("Bolivar"), ("Valle del Cauca");

INSERT INTO ciudades (nombre, departamento_id) VALUES ('Medellín', 1), ('Envigado', 1), ('Bogotá', 2), ('Cartagena', 3), ('Cali', 4);

INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3314", "Pie del Cerro", "Diagonal 31  No. 81B-96", 4, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS5161", "Doña Manuela", "Transversal 54 No. 41 D - 50", 4, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3269", "El Bosque", "TV 21 45 24 CRT EL BOSQUE", 4, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3204", "Belalcazar", "Carrera 17B No. 23-31", 5, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS5187", "Pasoancho", "CL 13 80-111", 5, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3202", "Carrera Primera", "Carrera 1  No.47—58", 5, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3264", "Las Mercedes", "Calle 65 No 55-35", 1, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3310", "La raya", "Carrera 52 No. 14 Sur-04", 1, 0, 0, true);
INSERT INTO estaciones(codigo, nombre, direccion, ciudad_id, latitud, longitud, activa) VALUES("EDS3266", "Sabaneta", "Carrera 43A No. 61 Sur-114", 1, 0, 0, true);

SELECT * FROM estaciones;

