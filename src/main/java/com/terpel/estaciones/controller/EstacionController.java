package com.terpel.estaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terpel.estaciones.dto.EstacionDto;
import com.terpel.estaciones.service.EstacionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("api/estaciones")
@AllArgsConstructor
public class EstacionController {
	
	private EstacionService estacionService;
    
    @PostMapping
    public ResponseEntity<EstacionDto> addEstacion(@Valid @RequestBody EstacionDto estacionDto){

    	EstacionDto newEstacion = estacionService.addEstacion(estacionDto);
        return new ResponseEntity<>(newEstacion, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<EstacionDto> getEstacion(@PathVariable("id") Long estacionId){
    	EstacionDto estacionDto = estacionService.getEstacion(estacionId);
        return new ResponseEntity<>(estacionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstacionDto>> getAllEstaciones(){
        List<EstacionDto> estaciones = estacionService.getAllEstaciones();        
        return ResponseEntity.ok(estaciones);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<EstacionDto> updateEstacion(@Valid @RequestBody EstacionDto estacionDto, @PathVariable("id") Long estacionId){
    	EstacionDto updatedEstacion = estacionService.updateEstacion(estacionDto, estacionId);
        return ResponseEntity.ok(updatedEstacion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEstacion(@PathVariable("id") Long estacionId){
    	estacionService.deleteEstacion(estacionId);
        return ResponseEntity.ok("Estación borrada exitosamente!.");
    }
    
}