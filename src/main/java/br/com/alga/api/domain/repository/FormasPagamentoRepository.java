package br.com.alga.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alga.api.domain.model.FormasPagamento;

@Repository
public interface FormasPagamentoRepository extends JpaRepository<FormasPagamento, Long>{
	

}
