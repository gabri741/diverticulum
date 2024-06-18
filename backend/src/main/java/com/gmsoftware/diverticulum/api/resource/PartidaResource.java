package com.gmsoftware.diverticulum.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmsoftware.diverticulum.api.model.FinalizarPartidaDTO;
import com.gmsoftware.diverticulum.api.model.PartidaDTO;
import com.gmsoftware.diverticulum.domain.model.Partida;
import com.gmsoftware.diverticulum.domain.service.PartidaService;
import com.gmsoftware.diverticulum.infraestructure.service.MatchMakingService;

@RestController()
@RequestMapping("partidas")
public class PartidaResource {
    
    
    private final PartidaService partidaService;
    private final MatchMakingService matchmaking;

    public PartidaResource(PartidaService partidaService, MatchMakingService matchmaking) {
            this.partidaService = partidaService;
            this.matchmaking = matchmaking;
        }

    @GetMapping
    public ResponseEntity<List<Partida>> listar() {
        return ResponseEntity.ok(partidaService.listar());

    }

    @GetMapping("/{id}")
        public ResponseEntity<Partida> buscar(@PathVariable String id) {
            return ResponseEntity.ok(partidaService.buscarPartida(id));
    }
    
    @PostMapping
    public ResponseEntity<Partida> nova(@RequestBody PartidaDTO partidaInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.novaPartida(partidaInput));
    }
    
    @GetMapping("/matchmaking/{jogadorId}")
    public ResponseEntity<String> matchmaking(@PathVariable String jogadorId){
        return ResponseEntity.status(HttpStatus.CREATED).body(matchmaking.addPlayer(jogadorId));
    }
    
    @PutMapping("/finalizar")
    public ResponseEntity<String> finalizar(@RequestBody FinalizarPartidaDTO dto){
        
        partidaService.finalizarPartida(dto.partidaId(), dto.vencedor(), dto.perdedor());
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    

        
}
