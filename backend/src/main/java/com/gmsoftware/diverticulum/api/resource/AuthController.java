package com.gmsoftware.diverticulum.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmsoftware.diverticulum.api.model.LoginDTO;
import com.gmsoftware.diverticulum.api.model.LoginResponseDTO;
import com.gmsoftware.diverticulum.api.model.RegisterDTO;
import com.gmsoftware.diverticulum.infraestructure.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
   private final AuthService service;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO body){
        return ResponseEntity.ok(service.login(body));
    }
    
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterDTO body){
        return ResponseEntity.ok(service.register(body));
    }
    
}
