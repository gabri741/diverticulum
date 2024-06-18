package com.gmsoftware.diverticulum.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmsoftware.diverticulum.domain.model.Jogador;
import com.gmsoftware.diverticulum.domain.service.JogadorService;

@RestController
@RequestMapping("/jogadores")
public class JogadorResource {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public List<Jogador> listarJogadores() {
        return jogadorService.listarJogadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarJogadorPorId(@PathVariable String id) {
        Jogador jogador = jogadorService.buscarJogadorPorId(id);
        return ResponseEntity.ok().body(jogador);
                
    }


    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizarJogador(@PathVariable String id, @RequestBody Jogador jogador) {
        Jogador jogadorAtualizado = jogadorService.atualizarJogador(id, jogador);
        return jogadorAtualizado != null ? ResponseEntity.ok().body(jogadorAtualizado)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogador(@PathVariable String id) {
        jogadorService.deletarJogador(id);
        return ResponseEntity.noContent().build();
    }
}
