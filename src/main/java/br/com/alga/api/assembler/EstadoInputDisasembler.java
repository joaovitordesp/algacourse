package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Estado;
import br.com.alga.api.model.input.EstadoInput;

@Component
public class EstadoInputDisasembler {
	//Disasembler = desmontador
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Estado toDomainObject(EstadoInput estadoInput) { 
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
		
		modelMapper.map(estadoInput, estado);
	}
}
