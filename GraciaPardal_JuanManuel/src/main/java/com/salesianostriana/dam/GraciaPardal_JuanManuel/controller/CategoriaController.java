package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto.CategoriaProducto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaServi categoriaServi;
    private final ProductoServi productoServi;

//    @GetMapping("/categoria/nueva")
//    public String MostrarForm (Model model){
//        model.addAttribute("categoria", new Categoria());
//
//        return "redirect:/nuevoProducto";
//    }

    @PostMapping("/submit/categoria")
    public  String guardarCat(@ModelAttribute CategoriaProducto categoriaProducto, Model model){
        categoriaServi.save(new Categoria(categoriaProducto.getIdCategoria(), categoriaProducto.getNombreCat()));
        Producto producto = productoServi.findById(categoriaProducto.getIdProducto());

        model.addAttribute("producto", producto);




        return "redirect:/nuevoProducto";


    }



}
