package br.com.alga.api.model.input;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

	@NotBlank
	private String nome;
	
	@NotNull
	private EstadoInput estado;
}
