package com.gmsoftware.diverticulum.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmsoftware.diverticulum.domain.model.Usuario;


public interface UsuarioRepository  extends MongoRepository<Usuario,String>{

    Optional<Usuario> findByEmail(String email);
}
