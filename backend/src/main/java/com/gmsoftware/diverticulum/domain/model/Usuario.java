package com.gmsoftware.diverticulum.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "usuarios")
@Data
@Builder
public class Usuario {
    
    @Id
    private String id;
    private String email;
    private String password;
    private LocalDate dataCadastro;
    
}
