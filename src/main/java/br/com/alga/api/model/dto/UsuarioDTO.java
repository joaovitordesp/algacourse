package br.com.alga.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;            
}   