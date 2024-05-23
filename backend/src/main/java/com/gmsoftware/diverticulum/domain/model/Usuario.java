package com.gmsoftware.diverticulum.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "usuarios")
@Data
public class Usuario {
    
    @Id
    private String id;
    private String email;
    private String password;
    
}
