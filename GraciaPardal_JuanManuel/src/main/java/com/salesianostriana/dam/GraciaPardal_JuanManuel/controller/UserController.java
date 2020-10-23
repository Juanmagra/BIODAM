package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@Controller
@AllArgsConstructor
@RequestMapping("/user/")
public class UserController {

    private final UsuarioServi usuarioServi;

    @GetMapping("/edit/{id}")
    public String editUsuario(@PathVariable Long id, Model model) {

        Usuario user = usuarioServi.findById(id);
        model.addAttribute("user", user);

        return "Formularios/formUserEdit";
    }

    @PostMapping("/edit/submit")
    public String editarUser (@ModelAttribute Usuario user){
        System.out.println(user);
        usuarioServi.save(user);
        return "redirect:/public/";

    }

}
