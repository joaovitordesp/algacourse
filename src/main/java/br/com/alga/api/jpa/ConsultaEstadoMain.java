package br.com.alga.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.alga.api.AlgaFoodApplication;
import br.com.alga.api.domain.model.Estado;
import br.com.alga.api.domain.repository.EstadoRepository;

public class ConsultaEstadoMain {
	
//	Classe criada com o intuito de realizar chamadas sem ser web, ou seja, a saída será no console da aplicação
		public static void main(String[] args) {
			ApplicationContext appContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
			
			EstadoRepository cadEstado = appContext.getBean(EstadoRepository.class);
		List<Estado> estados = cadEstado.findAll();
		
		for (Estado estado : estados) {
			System.out.println(estado.getNome());
			}
		}
}
