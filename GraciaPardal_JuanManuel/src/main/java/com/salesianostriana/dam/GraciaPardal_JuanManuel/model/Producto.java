package com.salesianostriana.dam.GraciaPardal_JuanManuel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private Double precio;
    private Boolean estado;

    @ManyToOne
    private Categoria categoria;


}
