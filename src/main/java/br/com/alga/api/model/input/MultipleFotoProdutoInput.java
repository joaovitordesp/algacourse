package br.com.alga.api.model.input;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultipleFotoProdutoInput {
	private List<FotoProdutoInput> fotos;
}
