package com.gmsoftware.diverticulum.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmsoftware.diverticulum.api.model.PartidaDTO;
import com.gmsoftware.diverticulum.domain.model.Partida;
import com.gmsoftware.diverticulum.domain.service.PartidaService;

@RestController()
@RequestMapping("partidas")
public class PartidaResource {

    private final PartidaService partidaService;

    public PartidaResource(PartidaService partidaService) {
            this.partidaService = partidaService;
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

        
}
