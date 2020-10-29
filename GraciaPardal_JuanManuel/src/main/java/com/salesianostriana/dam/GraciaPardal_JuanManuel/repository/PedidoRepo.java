package com.salesianostriana.dam.GraciaPardal_JuanManuel.repository;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuario(Usuario u);
}
