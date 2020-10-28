package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.PedidoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PedidoServi extends BaseService<Pedido, Long, PedidoRepo> {

    public PedidoServi(PedidoRepo repo) {
        super(repo);
    }

     static UsuarioServi usuarioServi;

    public Pedido guardarCarrito(Pedido pedido, Principal p) {

        pedido.setUser(usuarioServi.buscarPorEmail(p.getName()));
        pedido.setFecha(LocalDate.now());
        repositorio.save(pedido);

        return pedido;
    }

    public List<Pedido> findByTrabajador(Usuario u) {

        return repositorio.findByUser(u);

    }

}
