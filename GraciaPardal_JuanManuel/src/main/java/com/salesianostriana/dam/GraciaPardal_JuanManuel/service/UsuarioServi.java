package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.UsuarioRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServi extends BaseService<Usuario, Long, UsuarioRepo> {

    public UsuarioServi(UsuarioRepo repo) {
        super(repo);
    }

    public Usuario buscarPorEmail(String email){return repositorio.findFirstByEmail(email);}

    public List<Usuario> usuariosPorValidar(){
        List<Usuario> listaValidaciones = new ArrayList<>();
        for (Usuario u :repositorio.findAll()) {
            if (!u.isValidado()){
                listaValidaciones.add(u);
            }
        }
        return listaValidaciones;
    }
}
