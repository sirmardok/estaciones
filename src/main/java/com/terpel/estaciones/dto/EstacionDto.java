package com.terpel.estaciones.dto;

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
    private String codigo;
    private String nombre;
    private String direccion;
    private Integer ciudad;
    private Double latitud;
    private Double longitud;
    private boolean activa;

}
