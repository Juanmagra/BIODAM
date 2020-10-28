package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.LineaPedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.LineaPedidoRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LineaPedidoServi extends BaseService<LineaPedido, Long, LineaPedidoRepo> {

    public LineaPedidoServi(LineaPedidoRepo repo) {
        super(repo);
    }



    private Map<Producto, Integer> lineas = new HashMap<>();

    public Map<Producto, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(lineas);

    }

    public Map<Producto,Integer>getMapa(){
        return lineas;
    }

    public void addProducto(Producto p) {
        if (lineas.containsKey(p)) {
            lineas.replace(p, lineas.get(p) + 1);
        } else {
            lineas.put(p, 1);
        }
    }

    public void removeProducto(Producto p) {
        if (lineas.containsKey(p)) {
            if (lineas.get(p) > 1)
                lineas.replace(p, lineas.get(p) - 1);
            else if (lineas.get(p) == 1) {
                lineas.remove(p);
            }
        }
    }

    public void addLineaDePedido(Pedido p) {

        getProductsInCart().forEach((k, v) -> {
            LineaPedido l = new LineaPedido();

            l.setProducto(k);
            l.setCatidad(v);
            l.setPedido(p);
            repositorio.save(l);

        });

    }

    public void borrarMap() {

        lineas.clear();

    }

    public List<LineaPedido> findByCarrito(Pedido p) {

        return repositorio.findByPedido(p);

    }
}
