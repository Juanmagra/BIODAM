package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.PedidoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServi extends BaseService<Pedido, Long, PedidoRepo> {

    public PedidoServi(PedidoRepo repo) {
        super(repo);
    }

    public List<Pedido> porPropietario(Usuario u) {
        return repositorio.findByUsuario(u);
    }

}
