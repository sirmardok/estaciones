-- Estaciones de Servicio (TERPEL S.A.) project tables creation SCRIPT
-- Date: Apr-2026
-- Created by: Miguel Garces 

USE terpel;

DROP TABLE departamentos;

-- 1. Creación tabla de departamentos
CREATE TABLE departamentos (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

DROP TABLE ciudades;

-- 2. Creación tabla de ciudades/municipios
CREATE TABLE ciudades (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    departamento_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
) ENGINE=InnoDB;

DROP TABLE estaciones;

-- 3. Creación tabla estaciones de servicio
CREATE TABLE estaciones (
	id INT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(100) NOT NULL UNIQUE,
    nombre VARCHAR(200) NOT NULL,
    direccion VARCHAR(200),
    ciudad_id INT NOT NULL,
    latitud DECIMAL(10,8),
    longitud DECIMAL(10,8),
    activa BOOLEAN NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (ciudad_id) REFERENCES ciudades(id)
) ENGINE=InnoDB;

