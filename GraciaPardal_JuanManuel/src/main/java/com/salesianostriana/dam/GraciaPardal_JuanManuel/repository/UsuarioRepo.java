package com.salesianostriana.dam.GraciaPardal_JuanManuel.repository;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);
}
