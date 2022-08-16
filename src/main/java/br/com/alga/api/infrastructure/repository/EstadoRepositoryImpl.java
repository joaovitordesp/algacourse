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
//import br.com.alga.api.domain.model.Estado;
//import br.com.alga.api.domain.repository.EstadoRepository;
//
//@Repository
//public class EstadoRepositoryImpl implements EstadoRepository{
//	
//	@PersistenceContext //poderiamos usar  o @Autowired, mas deixamos esse pq pega tudo
//	private EntityManager manager;
//	
//	@Override
//	public List<Estado> listar() {
//		return manager.createQuery("from ESTADO", Estado.class).getResultList();
//	}
//
//	@Override
//	public Estado buscar(Long id) {
//		return manager.find(Estado.class, id);
//	}
//
//	@Transactional
//	@Override
//	public Estado salvar(Estado estado) {
//		return manager.merge(estado);
//	}
//	
//	@Transactional
//	@Override
//	public void remover(Long id) {
//		Estado estado = buscar(id);
//		
//		if (estado == null) {
//            throw new EmptyResultDataAccessException(1);
//        }
//		
//		manager.remove(estado);
//		
//	}
//
//}
