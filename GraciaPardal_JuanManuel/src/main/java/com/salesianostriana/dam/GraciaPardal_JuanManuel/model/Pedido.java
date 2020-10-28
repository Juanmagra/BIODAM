package com.salesianostriana.dam.GraciaPardal_JuanManuel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private String estado;

    @ManyToOne
    private Usuario user;

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
