package com.salesianostriana.dam.GraciaPardal_JuanManuel.model;


import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.LineaPedidoServi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Pedido {



    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy="pedido")
    private List<LineaPedido> lineaPedido = new ArrayList<>();

    //Helpers
    public void addLineaPedido(LineaPedido o) {
        o.setPedido(this);
        this.lineaPedido.add(o);

    }

    public void removeLineaPedido(LineaPedido o) {
        this.lineaPedido.remove(o);
        o.setPedido(null);
    }




}
