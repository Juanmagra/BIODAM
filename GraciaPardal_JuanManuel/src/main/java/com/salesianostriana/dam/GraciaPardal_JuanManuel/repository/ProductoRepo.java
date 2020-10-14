package com.salesianostriana.dam.GraciaPardal_JuanManuel.repository;


import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends JpaRepository <Producto, Long> {
}
