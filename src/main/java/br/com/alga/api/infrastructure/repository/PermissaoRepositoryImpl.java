//package br.com.alga.api.infrastructure.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import br.com.alga.api.domain.model.Permissao;
//import br.com.alga.api.domain.repository.PermissaoRepository;
//
//@Repository
//public class PermissaoRepositoryImpl implements PermissaoRepository{
//	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
//	private EntityManager manager;
//	
//	//Retornar uma lista de Cozinha
//	@Override
//	public List<Permissao> listar(){
//		return manager.createQuery("from PERMISSAO", Permissao.class).getResultList();
//	}
//	
//	public Permissao buscar(Long id) {
//		return manager.find(Permissao.class, id);
//	}
//	
//	//adicionar ou alterar cozinha
//	@Transactional
//	@Override
//	public Permissao salvar(Permissao permissao) {
//		return manager.merge(permissao);
//	}
//	
//	@Transactional
//	@Override
//	public void remover(Permissao permissao) {
//		permissao = buscar(permissao.getId());
//		manager.remove(permissao);
//	}
//}
