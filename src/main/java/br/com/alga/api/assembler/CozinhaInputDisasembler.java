package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.model.Estado;
import br.com.alga.api.model.input.CidadeInput;
import br.com.alga.api.model.input.CozinhaInput;

@Component
public class CozinhaInputDisasembler {
	//Disasembler = desmontador
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Cozinha toDomainObject(CozinhaInput cozinhaIdInput) { 
		return modelMapper.map(cozinhaIdInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInput cozinhaIdInput, Cozinha cozinha) {
		
		modelMapper.map(cozinhaIdInput, cozinha);
	}
}
