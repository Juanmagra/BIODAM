package com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CategoriaProducto {

    private  Long idCategoria;
    private  Long idProducto;
    private String nombrePro;
    private Double precioPro;
    private boolean estadoPro;
    private String nombreCat;




}
