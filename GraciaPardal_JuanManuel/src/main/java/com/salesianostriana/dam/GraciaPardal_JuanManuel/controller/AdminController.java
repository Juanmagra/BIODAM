package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto.CategoriaProducto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/")
public class AdminController {

    private final ProductoServi productoServi;
    private final CategoriaServi categoriaServi;
    private CategoriaProducto categoriaProducto;


    //Formulario nuevo producto
    @GetMapping("/nuevoProducto")
    public String MostrarForm (Model model){
        model.addAttribute("categoriaProducto", categoriaProducto);
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categorias", categoriaServi.findAll());
        model.addAttribute("producto", new Producto());

        return "Formularios/FormProducto";
    }
    //Nuevo producto
    @PostMapping("/submit")
    public String AñadirProducto(@ModelAttribute Producto producto){

        Boolean guardado= null;
        for (Producto p:  productoServi.findAll()){
            if(p.getId().equals(producto.getId())){
                guardado = true;
            }else{
                guardado= false;
            }
        }
        if (guardado){
            System.out.println(producto.getId()+ " Editado");
            productoServi.edit(producto);

        }else{

            producto.setEstado(true);
            productoServi.save(producto);
            System.out.println(producto.getId()+  " Guardado");
        }

        return "redirect:/public/";

    }

    //Editar producto
    @GetMapping("/edit/{id}")
    public String editarProducto(@PathVariable Long id, Model model){

        Producto productoS = productoServi.findById(id);

        model.addAttribute("producto", productoS);
        model.addAttribute("categorias", categoriaServi.findAll());
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categoriaProducto", new CategoriaProducto(productoS.getCategoria().getId(), productoS.getId(),productoS.getNombre(),productoS.getPrecio(),productoS.getEstado()," "));


        return "Formularios/FormProducto";
    }

    //Borrar producto
    @GetMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable Long id){

        productoServi.findById(id).setEstado(false);
        productoServi.edit(productoServi.findById(id));

        return "redirect:/public/";
    }
    //Nueva categoria
    @PostMapping("/submit/categoria")
    public  String guardarCat(@ModelAttribute CategoriaProducto categoriaProducto, Model model){
        model.addAttribute("categoriaProducto", categoriaProducto);

        categoriaServi.save(new Categoria(1L, categoriaProducto.getNombreCat()));
        if (categoriaProducto.getIdProducto()!=null) {
            Producto producto = productoServi.findById(categoriaProducto.getIdProducto());
            model.addAttribute("producto", producto);
        }

        if (categoriaProducto.getIdProducto()!=null) {
            return "redirect:/admin/edit/" + categoriaProducto.getIdProducto();

        }else{
            return "redirect:/admin/nuevoProducto";
        }

    }
}
