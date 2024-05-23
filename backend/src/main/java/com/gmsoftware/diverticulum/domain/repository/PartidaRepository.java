package com.gmsoftware.diverticulum.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gmsoftware.diverticulum.domain.model.Partida;

public interface PartidaRepository extends MongoRepository<Partida, String> {

}
