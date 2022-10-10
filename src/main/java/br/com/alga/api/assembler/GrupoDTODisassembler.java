package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Grupo;
import br.com.alga.api.model.input.GrupoInput;

@Component
public class GrupoDTODisassembler {
	 @Autowired
	    private ModelMapper modelMapper;
	    
	 public Grupo toDomainObject(GrupoInput grupoInput) {
	        return modelMapper.map(grupoInput, Grupo.class);
	    }
	    
	    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
	        modelMapper.map(grupoInput, grupo);
	    }  
}
