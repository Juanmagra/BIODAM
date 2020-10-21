package com.salesianostriana.dam.GraciaPardal_JuanManuel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.TrueFalseType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String apellidos;
    private String email;
    private Boolean esAdmin;
    private String contraseña;
    private boolean validado;

    @OneToMany(mappedBy = "user")
    private List<Pedido> pedidos = new ArrayList<>();



    //Helpers
    public void addPedido(Pedido p) {
        p.setUser(this);
        this.pedidos.add(p);
    }

    public void removePedido(Pedido p) {
        this.pedidos.remove(p);
        p.setUser(null);
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
