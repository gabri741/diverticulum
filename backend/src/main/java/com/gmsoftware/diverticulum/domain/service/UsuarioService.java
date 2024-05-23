package com.gmsoftware.diverticulum.domain.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmsoftware.diverticulum.api.model.RegisterDTO;
import com.gmsoftware.diverticulum.domain.model.Usuario;
import com.gmsoftware.diverticulum.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository repo;
    private final JogadorService jogadorService;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public Usuario cadastrarNovoUsuario(RegisterDTO body) {
            
           var usuario =  buscarPorEmail(body.email());
           
           if(!usuario.isEmpty()) throw new RuntimeException("Email já cadastrado");
           
           var novoUsuario = Usuario.builder()
                    .email(body.email())
                    .password(passwordEncoder.encode(body.password()))
                    .dataCadastro(LocalDate.now())
                    .build();
            
            repo.save(novoUsuario);
            
            jogadorService.criarJogador(novoUsuario, body);
            
            return novoUsuario;
    }
    
    public Optional<Usuario> buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }
}
