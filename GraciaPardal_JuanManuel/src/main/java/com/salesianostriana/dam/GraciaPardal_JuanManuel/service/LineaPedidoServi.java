package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.LineaPedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.LineaPedidoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaPedidoServi extends BaseService<LineaPedido, Long, LineaPedidoRepo> {

    public LineaPedidoServi(LineaPedidoRepo repo) {
        super(repo);
    }

    public List<LineaPedido> lineasDeUnPedido(Pedido p) {
        return repositorio.findByPedido(p);
    }


}
