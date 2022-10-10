package br.com.alga.api.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alga.api.domain.model.Endereco;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.model.dto.EnderecoDTO;
import br.com.alga.api.model.dto.RestauranteDTO;
import lombok.var;

@Configuration
public class ModelMapperConfig { //config para ter o model mapper no contexto do spring
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		/modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

		var enderecoToEnderecoDTOTYpeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoDTO.class);
		
		enderecoToEnderecoDTOTYpeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), 
				(destino, value) -> destino.getCidade().setEstado(value));
		
		return modelMapper;
	}
}
