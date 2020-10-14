package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ProductoController  {

    private final ProductoServi productoServi;
    private final CategoriaServi categoriaServi;


    @GetMapping("/")
    public String ListarProductos(Model model){

        model.addAttribute("lista", productoServi.findAll());

        return "Index";
    }

    @GetMapping("/nuevoProducto")
    public String MostrarForm (Model model){
        model.addAttribute("categorias", categoriaServi.findAll());
        model.addAttribute("producto", new Producto());

        return "Formularios/FormProducto";
    }

    @PostMapping("/submit")
    public String AñadirProducto(@ModelAttribute Producto producto){

        if (productoServi.findAll().contains(producto)){
            System.out.println(producto.getId());

            productoServi.edit(producto);

        }else{

            productoServi.save(producto);
            System.out.println(producto.getId());
        }

        return "redirect:/";

    }

}
