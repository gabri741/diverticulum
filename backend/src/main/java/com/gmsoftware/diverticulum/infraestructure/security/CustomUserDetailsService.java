package com.gmsoftware.diverticulum.infraestructure.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gmsoftware.diverticulum.domain.model.Usuario;
import com.gmsoftware.diverticulum.domain.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private  UsuarioRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

}
