package com.salesianostriana.dam.GraciaPardal_JuanManuel.security;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {


    private final UsuarioServi usuarioServicio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioServicio.buscarPorEmail(username);
    }

}
