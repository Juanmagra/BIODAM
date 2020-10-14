package com.salesianostriana.dam.GraciaPardal_JuanManuel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Usuario  {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private Boolean esAdmin;

    @OneToMany(mappedBy = "user")
    private List<Pedido> pedidos = new ArrayList<>();



    //Helpers
    public void addPedido(Pedido p) {
        p.setUser(this);
        this.pedidos.add(p);
    }

    public void removePedido(Pedido p) {
        this.pedidos.remove(p);
        p.setUser(null);
    }

}
