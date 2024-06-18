package com.gmsoftware.diverticulum.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.gmsoftware.diverticulum.api.assembler.PartidaInputDisassembler;
import com.gmsoftware.diverticulum.api.model.PartidaDTO;
import com.gmsoftware.diverticulum.domain.model.Jogador;
import com.gmsoftware.diverticulum.domain.model.Partida;
import com.gmsoftware.diverticulum.domain.repository.PartidaRepository;
import com.gmsoftware.diverticulum.util.EloRating;

@Service
public class PartidaService {

    private final PartidaRepository repository;
    private final PartidaInputDisassembler disassembler;
    private final JogadorService jogadorService;

    public PartidaService(PartidaRepository repository, PartidaInputDisassembler disaseembler,
            JogadorService jogadorService) {
        this.repository = repository;
        this.disassembler = disaseembler;
        this.jogadorService = jogadorService;
    }

    public Partida buscarPartida(String id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Partida> listar() {
        return repository.findAll();
    }
    
    public Partida buscarPartidaNaoFinalizada( String id) {
        return repository.findByIdAndFinalizadaFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Partida novaPartida(PartidaDTO dto) {
        Partida partida = disassembler.toDomainObject(dto);
        partida.setData(LocalDate.now());
        partida.setFinalizada(false);
        return repository.save(partida);
    }

    @Transactional
    public void finalizarPartida(String partidaId, String jogadorVencedorId, String jogadorPerdedorId) {
        Partida partida = buscarPartidaNaoFinalizada(partidaId);
        Jogador vencedor = jogadorService.buscarJogadorPorId(jogadorVencedorId);
        Jogador perdedor = jogadorService.buscarJogadorPorId(jogadorPerdedorId);

        partida.setFinalizada(true);
        partida.setIdVencedor(jogadorVencedorId);

        vencedor.setPontuacao(novaPontuacao(vencedor, perdedor, true));
        perdedor.setPontuacao(novaPontuacao(vencedor,perdedor, false));
        
        jogadorService.save(vencedor);
        jogadorService.save(perdedor);
        
        repository.save(partida);

    }

    public Long novaPontuacao(Jogador vencedor, Jogador perdedor, boolean isVencedor) {
        var pontuacaoVencedor = vencedor.getPontuacao();
        var pontuacaoPerdedor = perdedor.getPontuacao();

        var pontuacaoEsperada = EloRating.calculateExpectedScore(pontuacaoVencedor, pontuacaoPerdedor);

        if (isVencedor) {

            return EloRating.updateRating(pontuacaoVencedor, pontuacaoEsperada, 1);
        } else {

            return EloRating.updateRating(pontuacaoVencedor, pontuacaoEsperada, 0);
        }

    }

}
