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
//import br.com.alga.api.domain.model.FormasPagamento;
//import br.com.alga.api.domain.repository.FormasPagamentoRepository;
//
//@Repository
//public class FormaPagamentoRepositoryImpl implements FormasPagamentoRepository{
//
//	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
//	private EntityManager manager;
//	
//	//Retornar uma lista de Cozinha
//	@Override
//	public List<FormasPagamento> listar(){
//		return manager.createQuery("from FORMAS_PAGAMENTO", FormasPagamento.class).getResultList();
//	}
//	
//	public FormasPagamento buscar(Long id) {
//		return manager.find(FormasPagamento.class, id);
//	}
//	
//	//adicionar ou alterar cozinha
//	@Transactional
//	@Override
//	public FormasPagamento salvar(FormasPagamento formasPgto) {
//		return manager.merge(formasPgto);
//	}
//	
//	@Transactional
//	@Override
//	public void remover(FormasPagamento formasPgto) {
//		formasPgto = buscar(formasPgto.getId());
//		manager.remove(formasPgto);
//	}
//
//}
