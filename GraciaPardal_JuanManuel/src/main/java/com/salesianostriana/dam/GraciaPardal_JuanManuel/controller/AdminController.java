package com.salesianostriana.dam.GraciaPardal_JuanManuel.controller;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Categoria;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Producto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.dto.CategoriaProducto;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.CategoriaServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.ProductoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/")
public class AdminController {

    private final ProductoServi productoServi;
    private final CategoriaServi categoriaServi;
    private  final UsuarioServi usuarioServi;
    private CategoriaProducto categoriaProducto;


    //Formulario nuevo producto
    @GetMapping("/nuevoProducto")
    public String mostrarForm (Model model){



        model.addAttribute("categoriaProducto", categoriaProducto);
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categorias", categoriaServi.findAll());
        model.addAttribute("producto", new Producto());

        return "Formularios/FormProducto";
    }
    //Nuevo producto
    @PostMapping("/submit")
    public String añadirProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs){

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
        redirectAttrs
                .addFlashAttribute("mensaje", "Producto añadido con exito")
                .addFlashAttribute("clase", "success");

        return "redirect:/admin/nuevoProducto";

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
    public  String guardarCategoria(@ModelAttribute CategoriaProducto categoriaProducto, Model model){
        model.addAttribute("categoriaProducto", categoriaProducto);
        model.addAttribute("categoria", new Categoria());

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

    @GetMapping("/listaValidacion")
    public String listarValidaciones(Model model){

        if (usuarioServi.usuariosPorValidar().isEmpty()){
            model.addAttribute("lista", new ArrayList<>());
        }else{
            model.addAttribute("lista", usuarioServi.usuariosPorValidar());
        }

        return usuarioServi.usuariosPorValidar().isEmpty() ? "redirect:/public/" : "validaciones";
    }

    @GetMapping("/validar/{id}")
    public  String validarUser(@PathVariable Long id){

        Usuario userEdited = usuarioServi.findById(id); userEdited.setValidado(true);
        usuarioServi.edit(userEdited);

        return  usuarioServi.usuariosPorValidar().isEmpty() ? "redirect:/admin/listaValidacion": "redirect:/public/";


    }

    @GetMapping("/validar/eliminar/{id}")
    public String borrarUserValidacion(@PathVariable Long id){

        usuarioServi.deleteById(id);


        return  usuarioServi.usuariosPorValidar().isEmpty() ? "redirect:/admin/listaValidacion": "redirect:/public/";
    }

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

    //Guardar una categoria
    @PostMapping("/submit/UnaCategoria")
    public String guardarUnaCategoria(@ModelAttribute Categoria categoria){

        categoriaServi.save(categoria);

        return "redirect:/admin/categorias";
    }

    @GetMapping("/categorias")
    public String showCategoria( Model model){
        model.addAttribute("lista", categoriaServi.findAll());
        model.addAttribute("categoria", new Categoria());
        return "categorias";
    }

}
