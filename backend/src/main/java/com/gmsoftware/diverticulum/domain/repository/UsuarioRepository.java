package com.gmsoftware.diverticulum.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmsoftware.diverticulum.domain.model.Usuario;

public interface UsuarioRepository  extends MongoRepository<Usuario,String>{

}
