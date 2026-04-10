package com.terpel.estaciones.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.terpel.estaciones.dto.EstacionDto;
import com.terpel.estaciones.entity.Ciudad;
import com.terpel.estaciones.entity.Estacion;
import com.terpel.estaciones.exception.ResourceNotFoundException;
import com.terpel.estaciones.repository.CiudadRepository;
import com.terpel.estaciones.repository.EstacionRepository;
import com.terpel.estaciones.service.EstacionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstacionServiceImpl implements EstacionService{
	
	private EstacionRepository estacionRepository;
	private CiudadRepository ciudadRepository;

    private ModelMapper modelMapper;

    @Override    
    public EstacionDto addEstacion(EstacionDto estacionDto) {

        Estacion estacion = modelMapper.map(estacionDto, Estacion.class);
        Ciudad ciudad = ciudadRepository.findByNombre(estacionDto.getCiudadNombre())
        		.orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada: " + estacionDto.getCiudadNombre()));
        
        estacion.setCiudad(ciudad);
        
        Estacion newEstacion = estacionRepository.save(estacion);        
        EstacionDto newEstacionDto = modelMapper.map(newEstacion, EstacionDto.class);

        return newEstacionDto;
    }

    @Override
    @Cacheable(value = "estaciones", key = "#id")
    public EstacionDto getEstacion(Long id) {    	
    	System.out.println("LOG: Buscando estación " + id + " en MySQL...");

        Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estación no encontrada con Id:" + id));

        return modelMapper.map(estacion, EstacionDto.class);
    }

   @Override
   @Cacheable(value = "estaciones_lista")
    public List<EstacionDto> getAllEstaciones() {
	   
	   System.out.println("LOG: Trayendo todas las estaciones de MySQL...");
	   
       List<Estacion> estaciones = estacionRepository.findAll();

       return estaciones.stream().map((estacion) -> modelMapper.map(estacion, EstacionDto.class))
                .collect(Collectors.toList());
    }

   @Override
   @CachePut(value = "estaciones", key = "#id")
   @CacheEvict(value = "estaciones_lista", allEntries = true) // Limpia la lista general
   public EstacionDto updateEstacion(EstacionDto estacionDto, Long id) {
	   Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estación no encontrada con Id: " + id));
         estacion.setCodigo(estacionDto.getCodigo());
         estacion.setNombre(estacionDto.getNombre());
         estacion.setDireccion(estacionDto.getDireccion());         
         estacion.setLatitud(estacionDto.getLatitud());
         estacion.setLongitud(estacionDto.getLongitud());         
         estacion.setActiva(estacionDto.isActiva());         
         
       System.out.println("LOG: Actualizando estacion " + estacion.getId() + " en MySQL y Redis...");

       Estacion updatedEstacion = estacionRepository.save(estacion);

       return modelMapper.map(updatedEstacion, EstacionDto.class);
    }

    @Override
    @CacheEvict(value = {"estaciones", "estaciones_lista"}, key = "#id", allEntries = false)
    public void deleteEstacion(Long id) {

    	Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estación no encontrada con Id: " + id));

    	estacionRepository.deleteById(id);
    }
    
}
