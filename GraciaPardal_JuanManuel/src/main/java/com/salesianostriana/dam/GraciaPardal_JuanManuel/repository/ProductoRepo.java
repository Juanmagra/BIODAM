package com.salesianostriana.dam.GraciaPardal_JuanManuel.repository;


import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository <Producto, Long> {

    Page<Producto> findByCategoria(Pageable pageable, Categoria categoria);
}
