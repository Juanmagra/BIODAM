package com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Component
public class CategoriaProducto {

    @Id
    @GeneratedValue
    private  Long idCategoria;

    private  Long idProducto;
    private String nombrePro;
    private Double precioPro;
    private boolean estadoPro;
    private String nombreCat;




}
