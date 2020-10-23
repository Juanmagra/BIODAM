package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/public/")
public class PublicController {

    private final ProductoServi productoServi;
    private final UsuarioServi usuarioServi;

    //Listar producto
    @GetMapping("/")
    public String listarProductos(Model model, @AuthenticationPrincipal Usuario user){
        String url="Index";
        model.addAttribute("lista", productoServi.findAll());

        return url;

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
