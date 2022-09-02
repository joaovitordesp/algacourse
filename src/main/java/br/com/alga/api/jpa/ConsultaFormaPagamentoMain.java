package br.com.alga.api.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.alga.api.AlgaFoodApplication;
import br.com.alga.api.domain.model.FormaPagamento;
import br.com.alga.api.domain.repository.FormaPagamentoRepository;

public class ConsultaFormaPagamentoMain {
	
//	Classe criada com o intuito de realizar chamadas sem ser web, ou seja, a saída será no console da aplicação
		public static void main(String[] args) {
			ApplicationContext appContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
			
			FormaPagamentoRepository cadPagamento = appContext.getBean(FormaPagamentoRepository.class);
		List<FormaPagamento> pagamentos = cadPagamento.findAll();
		
		for (FormaPagamento pagamento : pagamentos) {
			System.out.println(pagamento.getDescricao());
			}
		}
}
