package com.terpel.estaciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terpel.estaciones.entity.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long>{
	
	Optional<Ciudad> findByNombre(String nombre);

}
