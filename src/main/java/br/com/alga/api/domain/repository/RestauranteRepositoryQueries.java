package br.com.alga.api.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.alga.api.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	List<Restaurante> findComFreteGratis(String nome);
}