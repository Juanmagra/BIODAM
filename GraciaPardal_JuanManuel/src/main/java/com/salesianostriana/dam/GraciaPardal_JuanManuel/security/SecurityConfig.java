package com.salesianostriana.dam.GraciaPardal_JuanManuel.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//                auth
//                .inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
         .authorizeRequests()
                .antMatchers("/css/**","/js/**", "/images/**","/register", "/submit-registration","/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout();

          http.csrf().disable();
          http.headers().frameOptions().disable();

//        http
//                .authorizeRequests()
//                .antMatchers("/css/**","/js/**", "/images/**","/register", "/submit-registration", "/h2-console/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .successHandler(customSuccesHandler)
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/index", true)
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .permitAll();
//        Añadimos esto para poder seguir accediendo a la consola de H2
//        con Spring Security habilitado.
//        http.csrf().disable();
//        http.headers().frameOptions().disable();

    }


}
