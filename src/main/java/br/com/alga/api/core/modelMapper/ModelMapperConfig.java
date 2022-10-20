package br.com.alga.api.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alga.api.domain.model.Endereco;
import br.com.alga.api.domain.model.ItemPedido;
import br.com.alga.api.model.dto.EnderecoDTO;
import br.com.alga.api.model.input.ItemPedidoInput;
import lombok.var;

@Configuration
public class ModelMapperConfig { //config para ter o model mapper no contexto do spring
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		/modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
	    .addMappings(mapper -> mapper.skip(ItemPedido::setId)); 

		var enderecoToEnderecoDTOTYpeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoDTO.class);
		
		enderecoToEnderecoDTOTYpeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), 
				(destino, value) -> destino.getCidade().setEstado(value));
		
		return modelMapper;
	}
}
