package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.LineaPedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Pedido;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.LineaPedidoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.PedidoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user/")
public class UserController {

    @Autowired
    HttpSession httpSession;
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


    /* Parte del carrito*/

    @ModelAttribute("carrito")
    public List<Producto> productosCarrito() {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        return (contenido == null) ? null : productoServi.variosPorId(contenido);
    }

    @ModelAttribute("total_carrito")
    public Double totalCarrito() {
        List<Producto> productosCarrito = productosCarrito();
        if (productosCarrito != null)
            return productosCarrito.stream()
                    .mapToDouble(p -> p.getPrecio())
                    .sum();
        return 0.0;
    }

//    @ModelAttribute("mis_pedidos")
//    public List<Pedido> misCompras() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        usuarioCompra = usuarioServi.buscarPorEmail(email);
//        return pedidoServi.porPropietario(usuarioCompra );
//    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        return "carrito";
    }

    @GetMapping("/carrito/add/{id}")
    public String addCarrito(Model model, @PathVariable Long id) {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        if (contenido == null)
            contenido = new ArrayList<>();
        if (!contenido.contains(id))
            contenido.add(id);
        httpSession.setAttribute("carrito", contenido);
        return "redirect:/user/carrito";
    }

    @GetMapping("/carrito/eliminar/{id}")
    public String borrarDeCarrito(Model model, @PathVariable Long id) {
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        if (contenido == null)
            return "redirect:/public/";
        contenido.remove(id);
        if (contenido.isEmpty())
            httpSession.removeAttribute("carrito");
        else
            httpSession.setAttribute("carrito", contenido);
        return "redirect:/user/carrito";

    }


    @GetMapping("/carrito/finalizar")
    public String checkout() {
        Pedido pedido= new Pedido();
        List<Long> contenido = (List<Long>) httpSession.getAttribute("carrito");
        if (contenido == null)
            return "redirect:/public";

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        pedido.setUsuario(usuarioServi.buscarPorEmail(email));
        pedido.setFecha(LocalDate.now());

        pedidoServi.save(pedido);

        for (Long id:contenido
             ) {
            LineaPedido lineaPedido = new LineaPedido();
            lineaPedido.setCatidad(1);
            lineaPedido.setProducto(productoServi.findById(id));
            pedido.addLineaPedido(lineaPedido);
            lineaPedidoServi.save(lineaPedido);


        }

        pedidoServi.edit(pedido);

     //   Compra c = compraServicio.insertar(new Compra(), usuario);
     //   productos.forEach(p -> compraServicio.addProductoCompra(p, c));
     //   session.removeAttribute("carrito");

        return "redirect:/public/";

    }

}
