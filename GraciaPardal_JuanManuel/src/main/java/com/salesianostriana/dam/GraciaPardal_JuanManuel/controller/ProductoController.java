package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto.CategoriaProducto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ProductoController  {

    private final ProductoServi productoServi;
    private final CategoriaServi categoriaServi;
    private CategoriaProducto categoriaProducto;


    @GetMapping("/")
    public String ListarProductos(Model model){

        model.addAttribute("lista", productoServi.findAll());

        return "Index";
    }

    @GetMapping("/nuevoProducto")
    public String MostrarForm (Model model){
        model.addAttribute("categoriaProducto", categoriaProducto);
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categorias", categoriaServi.findAll());
        model.addAttribute("producto", new Producto());

        return "Formularios/FormProducto";
    }

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

        return "redirect:/";

    }

    @GetMapping("/edit/{id}")
    public String editarProducto(@PathVariable Long id, Model model){

        Producto productoS = productoServi.findById(id);

        model.addAttribute("producto", productoS);
        model.addAttribute("categorias", categoriaServi.findAll());
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categoriaProducto", new CategoriaProducto(productoS.getCategoria().getId(), productoS.getId(),productoS.getNombre(),productoS.getPrecio(),productoS.getEstado()," "));


        return "Formularios/FormProducto";
    }

    @GetMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable Long id){

       productoServi.findById(id).setEstado(false);
       productoServi.edit(productoServi.findById(id));

       return "redirect:/";
    }




}
