package com.gmsoftware.diverticulum.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gmsoftware.diverticulum.api.assembler.PartidaInputDisassembler;
import com.gmsoftware.diverticulum.api.model.PartidaDTO;
import com.gmsoftware.diverticulum.domain.model.Partida;
import com.gmsoftware.diverticulum.domain.repository.PartidaRepository;

@Service
public class PartidaService {

    final private PartidaRepository repository;
    final private PartidaInputDisassembler disassembler;

    public PartidaService(PartidaRepository repository, PartidaInputDisassembler disaseembler) {
        this.repository = repository;
        this.disassembler = disaseembler;
    }

    public Partida buscarPartida(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Partida> listar() {
        return repository.findAll();
    }

    public Partida novaPartida(PartidaDTO dto) {
        Partida partida = disassembler.toDomainObject(dto);
        partida.setData(LocalDate.now());
        return repository.save(partida);
    }

}
