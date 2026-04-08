package com.terpel.estaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estaciones")
public class Estacion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = true)
    private String direccion;
    
    @Column(name = "ciudad_id",  nullable = false)
    private Integer ciudad;
    
    @Column(name = "latitud", precision = 10)
    private Double latitud;
    
    @Column(name = "longitud", precision = 11)
    private Double longitud;
    
    @Column(nullable = false)
    private boolean activa;

}