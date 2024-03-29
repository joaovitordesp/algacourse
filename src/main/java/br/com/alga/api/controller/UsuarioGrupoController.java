package br.com.alga.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.assembler.GrupoDTOAssembler;
import br.com.alga.api.domain.model.Usuario;
import br.com.alga.api.domain.service.CadastroUsuarioService;
import br.com.alga.api.model.dto.GrupoDTO;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	 @Autowired
	    private CadastroUsuarioService cadastroUsuario;
	    
	    @Autowired
	    private GrupoDTOAssembler grupoModelAssembler;
	    
	    @GetMapping
	    public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
	        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
	        
	        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
	    }
	    
	    @DeleteMapping("/{grupoId}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
	        cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
	    }
	    
	    @PutMapping("/{grupoId}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
	        cadastroUsuario.associarGrupo(usuarioId, grupoId);
	    }        
}
