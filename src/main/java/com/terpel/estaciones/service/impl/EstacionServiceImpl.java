package com.terpel.estaciones.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.terpel.estaciones.dto.EstacionDto;
import com.terpel.estaciones.entity.Estacion;
import com.terpel.estaciones.exception.ResourceNotFoundException;
import com.terpel.estaciones.repository.EstacionRepository;
import com.terpel.estaciones.service.EstacionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstacionServiceImpl implements EstacionService{
	
	private EstacionRepository estacionRepository;

    private ModelMapper modelMapper;

    @Override
    @CachePut(value = "estaciones", key = "#estacionDto.id")
    public EstacionDto addEstacion(EstacionDto estacionDto) {

        Estacion estacion = modelMapper.map(estacionDto, Estacion.class);        
        Estacion newEstacion = estacionRepository.save(estacion);        
        EstacionDto newEstacionDto = modelMapper.map(newEstacion, EstacionDto.class);

        return newEstacionDto;
    }

    @Override
    @Cacheable(value = "estaciones", key = "#id")
    public EstacionDto getEstacion(Long id) {

        Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estacion not found with id:" + id));

        return modelMapper.map(estacion, EstacionDto.class);
    }

   @Override
   @Cacheable(value = "estaciones")
    public List<EstacionDto> getAllEstaciones() {

        List<Estacion> estaciones = estacionRepository.findAll();

        return estaciones.stream().map((estacion) -> modelMapper.map(estacion, EstacionDto.class))
                .collect(Collectors.toList());
    }

   @Override
   @CachePut(value = "estaciones", key = "#id")
    public EstacionDto updateEstacion(EstacionDto estacionDto, Long id) {

         Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estacion not found with id : " + id));
         estacion.setCodigo(estacionDto.getCodigo());
         estacion.setNombre(estacionDto.getNombre());
         estacion.setDireccion(estacionDto.getDireccion());
         estacion.setCiudad(estacionDto.getCiudad());
         estacion.setLatitud(estacionDto.getLatitud());
         estacion.setLongitud(estacionDto.getLongitud());         
         estacion.setActiva(estacionDto.isActiva());

         Estacion updatedEstacion = estacionRepository.save(estacion);

        return modelMapper.map(updatedEstacion, EstacionDto.class);
    }

    @Override
    @CacheEvict(value = "estaciones", key = "#id")
    public void deleteEstacion(Long id) {

    	Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estacion not found with id : " + id));

    	estacionRepository.deleteById(id);
    }
    
}
