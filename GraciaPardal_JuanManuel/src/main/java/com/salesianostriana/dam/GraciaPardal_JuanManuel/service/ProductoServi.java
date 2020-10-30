package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.ProductoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductoServi extends BaseService<Producto, Long, ProductoRepo> {

    public ProductoServi(ProductoRepo repo) {
        super(repo);
    }

    public Page<Producto> findAllPaginated(Pageable pageable){

        return repositorio.findAll(pageable);
    }
    public List<Producto> findAllByCategoria(Categoria categoria){
        return repositorio.findByCategoria(categoria);
    }

    public List<Producto> variosPorId(List<Long> ids) { return repositorio.findAllById(ids); }


}
