package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.ProductoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoServi extends BaseService<Producto, Long, ProductoRepo> {

    public ProductoServi(ProductoRepo repo) {
        super(repo);
    }

    public List<Producto> filtrarPorCategoria(Categoria categoria){return repositorio.findByCategoria(categoria);}
}
