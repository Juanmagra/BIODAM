//package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;
//
//import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
//import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
//import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto.CategoriaProducto;
//import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
//import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//@AllArgsConstructor
//public class CategoriaController {
//
//    private final CategoriaServi categoriaServi;
//    private final ProductoServi productoServi;
//    private CategoriaProducto categoriaProducto;
//
//
//    @PostMapping("/submit/categoria")
//    public  String guardarCat(@ModelAttribute CategoriaProducto categoriaProducto, Model model){
//        model.addAttribute("categoriaProducto", categoriaProducto);
//
//        categoriaServi.save(new Categoria(1L, categoriaProducto.getNombreCat()));
//        if (categoriaProducto.getIdProducto()!=null) {
//            Producto producto = productoServi.findById(categoriaProducto.getIdProducto());
//            model.addAttribute("producto", producto);
//        }
//
//        if (categoriaProducto.getIdProducto()!=null) {
//            return "redirect:/edit/" + categoriaProducto.getIdProducto();
//
//        }else{
//            return "redirect:/nuevoProducto";
//        }
//
//    }
//
//
//
//}
