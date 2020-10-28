package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.LineaPedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.LineaPedidoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.PedidoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.sound.sampled.Line;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/user/")
public class UserController {

    private final UsuarioServi usuarioServi;
    private final ProductoServi productoServi;
    private final LineaPedidoServi lineaPedidoServi;
    private final PedidoServi pedidoServi;

    @GetMapping("/edit/profile/{id}")
    public String editUsuario(@PathVariable Long id, Model model) {

        Usuario user = usuarioServi.findById(id);
        model.addAttribute("user", user);

        return "Formularios/formUserEdit";
    }

    @PostMapping("/edit/submit")
    public String editarUser (@ModelAttribute Usuario user){

        if (user.getId()!=null) {
            Usuario u = usuarioServi.findById(user.getId());
            u.setApellidos(user.getApellidos());
            u.setNombre(user.getNombre());
            usuarioServi.edit(u);
        }
        return "redirect:/public/";

    }


    @GetMapping("/añadirToCarrito/{id}")
    public String añadirACarrito(@ModelAttribute LineaPedido lineaPedido, @PathVariable Long id){

        lineaPedidoServi.addProducto(productoServi.findById(id));

        return"redirect:/public/";
    }

    @GetMapping("/borrarDeCarrito/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {

        lineaPedidoServi.removeProducto(productoServi.findById(id));
        //TODO
        return "redirect:/user/carrito";
    }


    @PostMapping("/guardarCarrito")
    public String guardarCarrito(@ModelAttribute("carrito") Pedido p, Model model, Principal prin) {

        pedidoServi.guardarCarrito(p, prin);

        lineaPedidoServi.addLineaDePedido(p);

        p.setLineaPedido(lineaPedidoServi.findAll());

        lineaPedidoServi.borrarMap();

        return "redirect:/public/";

    }



}
