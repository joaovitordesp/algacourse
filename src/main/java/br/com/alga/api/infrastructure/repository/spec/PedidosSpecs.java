package br.com.alga.api.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alga.api.domain.model.Pedido;
import br.com.alga.api.domain.repository.filter.PedidoFilter;
import lombok.var;

public class PedidosSpecs {
	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, query,builder) ->{
			//TODO: problema do n+1 resolvido usando fetch
			root.fetch("restaurante").fetch("cozinha");
			root.fetch("cliente");
			
			var predicates = new ArrayList<Predicate>();
			
			if(filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
			}
			if(filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			if(filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}
			if(filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
