package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@AllArgsConstructor
@RequestMapping("/public/")
public class PublicController {

    private final ProductoServi productoServi;
    private final UsuarioServi usuarioServi;
    private final CategoriaServi categoriaServi;

    //Listar producto
    @GetMapping("/")
    public String listarProductos(Model model, @AuthenticationPrincipal Usuario user){

        model.addAttribute("lista", productoServi.findAll());
        model.addAttribute("listaC", categoriaServi.findAll());
        
        return "Index";

    }
    @GetMapping("/{id}")
    public String listarProductosFiltrados(Model model, @AuthenticationPrincipal Usuario user, @PathVariable String id){

            model.addAttribute("lista", productoServi.filtrarPorCategoria(categoriaServi.buscarCategoriaPorNombre(id)));
            model.addAttribute("listaC", categoriaServi.findAll());

        return "Index";

    }

    @GetMapping("/registro")
    public String formularioRegistro(Model model) {

        model.addAttribute("user",  new Usuario());

        return "/Formularios/formUser";
    }

    @PostMapping("/submit/user")
    public String guardarUser(@ModelAttribute Usuario user, BCryptPasswordEncoder bCryptPasswordEncoder, @AuthenticationPrincipal Usuario userA){


        if (user.getId()!=null) {
            Usuario u = usuarioServi.findById(user.getId());
            u.setApellidos(user.getApellidos());
            u.setNombre(user.getNombre());
            usuarioServi.edit(u);
        }else{
            if (userA == null) {
                userA = new Usuario();
            }
            if (userA.isEsAdmin() && userA != null) {
                user.setValidado(true);
            }
            user.setContraseña(bCryptPasswordEncoder.encode(user.getContraseña()));
            System.out.println("Guardado"+user.getId());
            usuarioServi.save(user);
        }

        return "redirect:/public/";
    }
    

}
