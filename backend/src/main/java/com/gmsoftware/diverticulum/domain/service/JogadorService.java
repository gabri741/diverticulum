package com.gmsoftware.diverticulum.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmsoftware.diverticulum.domain.model.Jogador;
import com.gmsoftware.diverticulum.domain.repository.JogadorRepository;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Jogador> listarJogadores() {
        return jogadorRepository.findAll();
    }

    public Optional<Jogador> buscarJogadorPorId(String id) {
        return jogadorRepository.findById(id);
    }

    public Jogador criarJogador(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public Jogador atualizarJogador(String id, Jogador jogador) {
        if (jogadorRepository.existsById(id)) {
            jogador.setId(id);
            return jogadorRepository.save(jogador);
        } else {
            return null; // ou lançar uma exceção, dependendo dos requisitos do sistema
        }
    }

    public void deletarJogador(String id) {
        jogadorRepository.deleteById(id);
    }
}
