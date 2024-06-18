package com.gmsoftware.diverticulum.infraestructure.service;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.gmsoftware.diverticulum.api.model.PartidaDTO;
import com.gmsoftware.diverticulum.domain.model.Jogador;
import com.gmsoftware.diverticulum.domain.service.JogadorService;
import com.gmsoftware.diverticulum.domain.service.PartidaService;

@Service
public class MatchMakingService {

    private final PartidaService partidaService;
    private final JogadorService jogadorService;
    private final ConcurrentLinkedQueue<Jogador> queue;

    public MatchMakingService(PartidaService partidaService, JogadorService jogadorService) {
        this.partidaService = partidaService;
        this.jogadorService = jogadorService;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public synchronized String addPlayer(String newPlayer) {
        Jogador player = jogadorService.buscarJogadorPorId(newPlayer);
        Jogador opponent = findOpponent(player);

        if (opponent != null) {
            // Found an opponent, form a match
            
            var partida = partidaService.novaPartida(new PartidaDTO(player.getId(), opponent.getId()));
            System.out.println("Match found: " + player + " vs " + opponent);
            return partida.getId();
        } else {
            // No suitable opponent, add to queue
            queue.add(player);
            System.out.println("Player added to queue: " + newPlayer);
            return "Player added to queue: " + newPlayer;
        }
    }
    
    public synchronized void removePlayer(Jogador player) {
        queue.remove(player);
        
    }

    private Jogador findOpponent(Jogador newPlayer) {
        for (Jogador player : queue) {
            if (Math.abs(player.getPontuacao() - newPlayer.getPontuacao()) <= 100) {
                queue.remove(player);
                return player;
            }
        }
        return null;
    }

}
