package com.terpel.estaciones.service;

import com.terpel.estaciones.dto.EstacionDto;
import java.util.List;

public interface EstacionService {
	
	EstacionDto addEstacion(EstacionDto estacionDto);

	EstacionDto getEstacion(Long id);

    List<EstacionDto> getAllEstaciones();

    EstacionDto updateEstacion(EstacionDto estacionDto, Long id);

    void deleteEstacion(Long id);
    
}