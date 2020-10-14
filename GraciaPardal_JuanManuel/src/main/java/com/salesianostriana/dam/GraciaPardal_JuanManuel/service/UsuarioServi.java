package com.salesianostriana.dam.GraciaPardal_JuanManuel.service;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.UsuarioRepo;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServi extends BaseService<Usuario, Long, UsuarioRepo> {

    public UsuarioServi(UsuarioRepo repo) {
        super(repo);
    }
}
