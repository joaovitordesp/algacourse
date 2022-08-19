package br.com.alga.api.infrastructure.repository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.RestauranteRepository;
import br.com.alga.api.domain.repository.RestauranteRepositoryQueries;
import br.com.alga.api.infrastructure.repository.spec.RestaurantesSpecs;
import lombok.var;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	//Repository para implementações que não tem regras de negócio, apenas partes relacionadas a infraestrutura

	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
	private EntityManager manager;
	
	@Autowired @Lazy //instancia só quando é preciso
	private RestauranteRepository restauranteRepository;
	
	/*mesmo parecendo que está desconectado com o Impl dele, o Jpa faz a conexão
		 é importante o sufixo da implementação esta IMPL */
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal){

		CriteriaBuilder builder = manager.getCriteriaBuilder(); //controi elementos para consulta
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); //preciso de uma query de Restaurante
		Root<Restaurante> root = criteria.from(Restaurante.class); // from Restaurante
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			predicates.add( builder.like(root.get("nome"),  "%" + nome + "%")); 
		}

		if(taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"),taxaFreteInicial));
		}
		
		if(taxaFreteFinal != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"),taxaFreteFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0])); //convertendo lista em um array, pois só assim o where acc
		
		var query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(RestaurantesSpecs.comFreteGratis()
				.and(RestaurantesSpecs.comNomeSemelhante(nome)));
	}
}
