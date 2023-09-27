package br.com.alga.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.FormaPagamento;
import br.com.alga.api.model.input.FormaPagamentoInput;

@Component
public class FormaPagamentoDTODisassembler {
	 	
		@Autowired
	    private ModelMapper modelMapper;
	    
	    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
	        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	    }
	    
	    public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
	        modelMapper.map(formaPagamentoInput, formaPagamento);
	    }   
}
