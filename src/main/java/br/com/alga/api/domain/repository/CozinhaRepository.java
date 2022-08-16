package br.com.alga.api.domain.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alga.api.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	List<Cozinha> findTodasByNomeContaining(String nome);
	
	//nome da propriedade que quer realizar a consulta, nesse caso foi nome
	Optional<Cozinha> findByNome(String nome);
	
	boolean existsByNome(String nome);
}
