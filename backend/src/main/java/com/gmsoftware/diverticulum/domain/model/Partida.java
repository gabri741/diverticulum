package com.gmsoftware.diverticulum.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "partidas")
public class Partida {

    @Id
    private String id;

    private String idJogador1;

    private String idJogador2;

    private String idVencedor;

    private LocalDate data;

}
