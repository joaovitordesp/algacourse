package br.com.alga.api.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alga.api.domain.model.Restaurante;
import br.com.alga.api.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository{
	//Repository para implementações que não tem regras de negócio, apenas partes relacionadas a infraestrutura

	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
	private EntityManager manager;
	
	//Retornar uma lista de Cozinha
	@Override
	public List<Restaurante> listar(){
		return manager.createQuery("from COZINHA", Restaurante.class).getResultList();
	}
	
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	//adicionar ou alterar cozinha
	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
	}
	//

}
