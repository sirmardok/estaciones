package com.terpel.estaciones.dto;

import java.time.LocalDateTime;

import com.terpel.estaciones.entity.Estacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstacionDto {
	
	private Long id;
	
	@NotBlank(message = "Campo código no puede ser nulo ni vacío")	
    private String codigo;
	
	@NotBlank(message = "Campo nombre no puede ser nulo ni vacío")	
    private String nombre;
    private String direccion;
    private String ciudadNombre;
    private Double latitud;
    private Double longitud;
    
    @NotNull(message = "Activa no puede estar vacío")
    private boolean activa;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion; 
    
    

}
