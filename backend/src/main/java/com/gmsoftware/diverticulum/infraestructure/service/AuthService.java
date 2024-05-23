package com.gmsoftware.diverticulum.infraestructure.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmsoftware.diverticulum.api.model.LoginDTO;
import com.gmsoftware.diverticulum.api.model.LoginResponseDTO;
import com.gmsoftware.diverticulum.api.model.RegisterDTO;
import com.gmsoftware.diverticulum.domain.service.UsuarioService;
import com.gmsoftware.diverticulum.infraestructure.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginResponseDTO login(LoginDTO body) {
        var usuario = userService.buscarPorEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não cadastrado"));
            
        if (passwordEncoder.matches(body.password(), usuario.getPassword())) {
            var token = tokenService.generateToken(usuario);
            return new LoginResponseDTO(usuario.getEmail(), token);
        } else {
            throw new RuntimeException("Senha incorreta");
        }

    }
    
    public LoginResponseDTO register(RegisterDTO body) {
        var novoUsuario = userService.cadastrarNovoUsuario(body);

        var token = tokenService.generateToken(novoUsuario);

        return new LoginResponseDTO(body.email(), token);
    }

}
