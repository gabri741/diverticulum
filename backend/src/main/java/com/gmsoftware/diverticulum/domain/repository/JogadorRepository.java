package com.gmsoftware.diverticulum.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmsoftware.diverticulum.domain.model.Jogador;

public interface JogadorRepository extends MongoRepository<Jogador, String> {
}
