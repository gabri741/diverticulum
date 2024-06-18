package com.gmsoftware.diverticulum.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gmsoftware.diverticulum.api.model.PartidaDTO;
import com.gmsoftware.diverticulum.domain.model.Partida;

@Component
public class PartidaInputDisassembler {
    
    private ModelMapper modelMapper;
       
    public PartidaInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Partida toDomainObject(PartidaDTO partida) {
            return modelMapper.map(partida, Partida.class);
    }
    

    
}
