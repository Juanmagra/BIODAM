package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class PublicController {

    private final ProductoServi productoServi;

    //Listar producto
    @GetMapping("/")
    public String ListarProductos(Model model){

        model.addAttribute("lista", productoServi.findAll());

        return "Index";
    }

}
