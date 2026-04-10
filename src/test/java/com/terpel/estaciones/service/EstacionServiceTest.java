package com.terpel.estaciones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.terpel.estaciones.dto.EstacionDto;
import com.terpel.estaciones.entity.Ciudad;
import com.terpel.estaciones.entity.Departamento;
import com.terpel.estaciones.entity.Estacion;
import com.terpel.estaciones.exception.ResourceNotFoundException;
import com.terpel.estaciones.repository.CiudadRepository;
import com.terpel.estaciones.repository.EstacionRepository;
import com.terpel.estaciones.service.impl.EstacionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EstacionServiceTest {
	
	@Mock
	private EstacionRepository estacionRepository;
	
	@Mock
    private ModelMapper modelMapper;
	
	@Mock
    private CiudadRepository ciudadRepository;	
	
	@InjectMocks
	private EstacionServiceImpl estacionService;
	
	private Estacion estacion;
	
	private EstacionDto estacionDto;
	
	private Ciudad ciudad;	
	
	private Departamento departamento;
	
	
	@BeforeEach
	void setUp() {
		
		LocalDateTime fecha = LocalDateTime.now();
		departamento = new Departamento(1L, "Cauca");
		ciudad = new Ciudad(1L, "Popayán", departamento);
		estacion = new Estacion(1L, "EDS123", "Estacion Test", "Cra 1", ciudad, 0.0, 0.0, true, fecha, fecha);	
		
	}
	
	@Test
	void addEstacionTest() {
		
		estacionDto = new EstacionDto();
		
		estacionDto.setCodigo("EDS123");
		estacionDto.setNombre("Estacion Test");
		estacionDto.setCiudadNombre("Popayán");
		estacionDto.setActiva(true);
		
		Estacion estacionConvertida = new Estacion();
		estacionConvertida.setCodigo("EDS123");
		estacionConvertida.setNombre("Estacion Test");
		estacionConvertida.setActiva(true);
		
		//Configuracion del comportamiento del mapper
		when(modelMapper.map(estacionDto, Estacion.class)).thenReturn(estacionConvertida);
		//Configuracion del comportamient del repo
		when(estacionRepository.save(any(Estacion.class))).thenReturn(estacion);
		//Configuracion de la ciudad que existe en BD
        when(ciudadRepository.findByNombre("Popayán")).thenReturn(Optional.of(ciudad));
        //Configuracion de salida del servicio
        when(modelMapper.map(any(Estacion.class), eq(EstacionDto.class))).thenReturn(estacionDto);
        
		
		//llamado a procedimiento de agregar estación
		EstacionDto resultado = estacionService.addEstacion(estacionDto);
		
		assertNotNull(resultado);		
		assertEquals("EDS123", resultado.getCodigo());
		
		//Verifica el uso del mapper
		verify(modelMapper).map(any(EstacionDto.class), eq(Estacion.class));
		//Verifica que se buscó la ciudad
		verify(ciudadRepository).findByNombre("Popayán"); 
		//Verifica que el repositorio se llamó 1 vez
		verify(estacionRepository, times(1)).save(any(Estacion.class));
		//Verifica que se guardó
		verify(estacionRepository).save(any(Estacion.class)); 
				
	}
	
	@Test
	void getEstacionTestExitoso() {

	    Long id = 1L;
	    when(estacionRepository.findById(id)).thenReturn(Optional.of(estacion));
	    
	    //Mapeo de la entidad encontrada hacia el DTO de salida
	    EstacionDto esperadoDto = new EstacionDto();
	    esperadoDto.setCodigo("EDS123");
	    when(modelMapper.map(estacion, EstacionDto.class)).thenReturn(esperadoDto);

	    //Llamado al servicio
	    EstacionDto resultado = estacionService.getEstacion(id);

	    //Validamos con asserts
	    assertNotNull(resultado);
	    assertEquals("EDS123", resultado.getCodigo());
	    verify(estacionRepository).findById(id);
	}
	
	@Test
	void getEstacionTestNoExitoso() {
		
	    when(estacionRepository.findById(99L)).thenReturn(Optional.empty());
	    
	    //Verifica que el servicio lance la excepción esperada
	    assertThrows(ResourceNotFoundException.class, () -> {
	        estacionService.getEstacion(99L);
	    });
	}
	
	@Test
	void updateEstacionTest() {

	    Long id = 1L;
	    EstacionDto dtoUpdate = new EstacionDto();
	    dtoUpdate.setNombre("Nombre Actualizado");
	    dtoUpdate.setCiudadNombre("Popayán");

	    //El servicio busca si existe
	    when(estacionRepository.findById(id)).thenReturn(Optional.of(estacion));
	    
	    //El repo guarda la entidad modificada
	    when(estacionRepository.save(any(Estacion.class))).thenReturn(estacion);
	    
	    //Mapeo para retornar el DTO
	    when(modelMapper.map(any(Estacion.class), eq(EstacionDto.class))).thenReturn(dtoUpdate);

	    EstacionDto resultado = estacionService.updateEstacion(dtoUpdate, id);

	    //Asserts que validan el resultado no nulo y nombre actualizado
	    assertNotNull(resultado);
	    assertEquals("Nombre Actualizado", resultado.getNombre());
	    verify(estacionRepository).save(any(Estacion.class));
	}
	
	@Test
	void deleteEstacionTestExitoso() {
	    Long id = 1L;
	    
	    //Configuración del mock
	    when(estacionRepository.findById(id)).thenReturn(Optional.of(estacion));
	    
	    //deleteById retorna void, así que se usa doNothing
	    doNothing().when(estacionRepository).deleteById(id);
	    estacionService.deleteEstacion(id);

	    //Verifica que se buscó la estación
	    verify(estacionRepository, times(1)).findById(id);
	    //Verifica que se llamó a eliminar
	    verify(estacionRepository, times(1)).deleteById(id);
	}

}
