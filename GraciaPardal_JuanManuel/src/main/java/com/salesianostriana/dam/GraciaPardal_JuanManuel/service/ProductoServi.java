package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.ProductoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;


@Service
public class ProductoServi extends BaseService<Producto, Long, ProductoRepo> {

    public ProductoServi(ProductoRepo repo) {
        super(repo);
    }
}
