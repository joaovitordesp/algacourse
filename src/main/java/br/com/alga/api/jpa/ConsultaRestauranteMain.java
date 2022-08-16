package br.com.alga.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.alga.api.AlgaFoodApplication;
import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {
	
//	Classe criada com o intuito de realizar chamadas sem ser web, ou seja, a saída será no console da aplicação
		public static void main(String[] args) {
			ApplicationContext appContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
			
			RestauranteRepository cadRestaurante = appContext.getBean(RestauranteRepository.class);
		List<Restaurante> restaurantes = cadRestaurante.findAll();
		
		for (Restaurante restaurante : restaurantes) {
			System.out.printf("%s - %f - %s\n", restaurante.getNome(),
					restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
			}
		}
}
