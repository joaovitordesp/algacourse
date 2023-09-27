package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Cidade;
import br.com.alga.api.domain.model.Estado;
import br.com.alga.api.model.input.CidadeInput;

@Component
public class CidadeInputDisasembler {
	//Disasembler = desmontador
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Cidade toDomainObject(CidadeInput cidadeInput) { 
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		/* Para evitar um 500 - identifier of an instance of br.com.alga.api.domain.model.Cozinha was altered from 1 to 2
		 * */
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
}
