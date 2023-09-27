package br.com.alga.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alga.api.domain.model.Pedido;
import br.com.alga.api.model.dto.PedidoResumoDTO;

@Component
public class PedidoResumoDTOAssembler {
	 @Autowired
	    private ModelMapper modelMapper;
	    
	    public PedidoResumoDTO toModel(Pedido grupo) {
	        return modelMapper.map(grupo, PedidoResumoDTO.class);
	    }
	    
	    public List<PedidoResumoDTO> toCollectionModel(Collection<Pedido> grupos) {
	        return grupos.stream()
	                .map(grupo -> toModel(grupo))
	                .collect(Collectors.toList());
	    }   
}
