package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.CategoriaRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServi extends BaseService<Categoria, Long, CategoriaRepo> {

    public CategoriaServi(CategoriaRepo repo) {
        super(repo);
    }

    public Categoria buscarCategoriaNombre(String nombre){return repositorio.findByNombre(nombre);}
}
