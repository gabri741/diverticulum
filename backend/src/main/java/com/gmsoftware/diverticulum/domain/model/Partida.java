package com.gmsoftware.diverticulum.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "partidas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Partida {

    @Id
    private String id;

    private String jogadorUm;

    private String jogadorDois;

    private String idVencedor;

    private LocalDate data;
    
    private Boolean finalizada;

}
