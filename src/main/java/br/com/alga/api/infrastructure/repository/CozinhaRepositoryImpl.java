package br.com.alga.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alga.api.domain.model.Cozinha;
import br.com.alga.api.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository{
	//Repository para implementações que não tem regras de negócio, apenas partes relacionadas a infraestrutura

	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
	private EntityManager manager;
	
	//Retornar uma lista de Cozinha
	@Override
	public List<Cozinha> listar(){
		return manager.createQuery("from COZINHA", Cozinha.class).getResultList();
	}
	
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	//adicionar ou alterar cozinha
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}
	//

}
