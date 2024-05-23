package com.gmsoftware.diverticulum.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document(collection = "jogadores")
@Builder
public class Jogador {

    private String id;
    private String idUsuario;
    private String nickname;
    private String nome;
    private String descricao;
    private Long pontuacao;
    private String foto;

}
