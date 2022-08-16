package br.com.alga.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.alga.api.AlgaFoodApplication;
import br.com.alga.api.domain.model.Permissao;
import br.com.alga.api.domain.repository.PermissaoRepository;

public class ConsultaPermissaoMain {
	
//	Classe criada com o intuito de realizar chamadas sem ser web, ou seja, a saída será no console da aplicação
		public static void main(String[] args) {
			ApplicationContext appContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
			
			PermissaoRepository cadPermissao = appContext.getBean(PermissaoRepository.class);
		List<Permissao> permissoes = cadPermissao.findAll();
		
		for (Permissao permissao : permissoes) {
			System.out.println(permissao.getNome());
			}
		}
}
