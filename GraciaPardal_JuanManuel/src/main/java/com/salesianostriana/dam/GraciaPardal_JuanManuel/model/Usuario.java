package com.salesianostriana.dam.GraciaPardal_JuanManuel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private boolean esAdmin;
    private String contraseña;
    private boolean validado;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos = new ArrayList<>();



    //Helpers
    public void addPedido(Pedido p) {
        p.setUsuario(this);
        this.pedidos.add(p);
    }

    public void removePedido(Pedido p) {
        this.pedidos.remove(p);
        p.setUsuario(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_";
        if (esAdmin) {
            role += "ADMIN";
        } else {
            role += "USER";
        }
        //return Arrays.asList(new SimpleGrantedAuthority(role));
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() { return getContraseña(); }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
