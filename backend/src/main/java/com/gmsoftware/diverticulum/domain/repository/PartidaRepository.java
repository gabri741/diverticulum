package com.gmsoftware.diverticulum.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmsoftware.diverticulum.domain.model.Partida;

public interface PartidaRepository extends MongoRepository<Partida, String> {
    
    
    Optional<Partida> findByIdAndFinalizadaFalse(String id);
}
