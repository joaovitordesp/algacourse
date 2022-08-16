//package br.com.alga.api.infrastructure.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import br.com.alga.api.domain.model.Cidade;
//import br.com.alga.api.domain.repository.CidadeRepository;
//
//@Repository
//public class CidadeRepositoryImpl implements CidadeRepository{
//	//Repository para implementações que não tem regras de negócio, apenas partes relacionadas a infraestrutura
//
//	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
//	private EntityManager manager;
//	
//	//Retornar uma lista de Cozinha
//	@Override
//	public List<Cidade> listar(){
//		return manager.createQuery("from CIDADE", Cidade.class).getResultList();
//	}
//	
//	public Cidade buscar(Long id) {
//		return manager.find(Cidade.class, id);
//	}
//	
//	//adicionar ou alterar cozinha
//	@Transactional
//	@Override
//	public Cidade salvar(Cidade cozinha) {
//		return manager.merge(cozinha);
//	}
//	
//	@Transactional
//	@Override
//	public void remover(Long  id) {
//		Cidade cidade = buscar(id);
//		
//		if (cidade == null) {
//            throw new EmptyResultDataAccessException(1);
//        }
//		
//		manager.remove(cidade);
//	}
//}
