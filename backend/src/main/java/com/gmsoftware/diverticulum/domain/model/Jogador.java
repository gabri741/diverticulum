package com.gmsoftware.diverticulum.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "jogadores")
public class Jogador {

    private String id;
    private String idUsuario;
    private String nickname;
    private String nome;
    private String descricao;
    private Long pontuacao;
      
}
