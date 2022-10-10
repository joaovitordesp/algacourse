package br.com.alga.api.domain.service;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alga.api.domain.exception.NegocioException;
import br.com.alga.api.domain.exception.UsuarioNaoEncontradoException;
import br.com.alga.api.domain.model.Usuario;
import br.com.alga.api.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
    	// faz com que o jpa nao gerencie mais essa instancia
    	usuarioRepository.detach(usuario);
    	
    	Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
    	
    	//2 condicao, esse usuario e diferente do que ja tem?
    	if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
    		throw new NegocioException(String.format("Ja existe um usuario cadastrado com o e-mail %s", usuario.getEmail()));
    	}
    	
        return usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        
        usuario.setSenha(novaSenha);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }            
}     