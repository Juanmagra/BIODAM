package com.salesianostriana.dam.GraciaPardal_JuanManuel.repository;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.LineaPedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineaPedidoRepo  extends JpaRepository<LineaPedido, Long> {

    List<LineaPedido> findByPedido(Pedido p);
}
