package com.salesianostriana.dam.GraciaPardal_JuanManuel.repository;


import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository <Producto, Long> {

    List<Producto> findByCategoria(Categoria categoria);
}
