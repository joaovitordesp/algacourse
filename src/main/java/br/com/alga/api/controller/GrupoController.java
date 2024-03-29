package br.com.alga.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alga.api.assembler.GrupoDTOAssembler;
import br.com.alga.api.assembler.GrupoDTODisassembler;
import br.com.alga.api.domain.model.Grupo;
import br.com.alga.api.domain.repository.GrupoRepository;
import br.com.alga.api.domain.service.CadastroGrupoService;
import br.com.alga.api.model.dto.GrupoDTO;
import br.com.alga.api.model.input.GrupoInput;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	 @Autowired
	    private GrupoRepository grupoRepository;
	    
	    @Autowired
	    private CadastroGrupoService cadastroGrupo;
	    
	    @Autowired
	    private GrupoDTOAssembler grupoModelAssembler;
	    
	    @Autowired
	    private GrupoDTODisassembler grupoInputDisassembler;
	    
	    @GetMapping
	    public List<GrupoDTO> listar() {
	        List<Grupo> todosGrupos = grupoRepository.findAll();
	        
	        return grupoModelAssembler.toCollectionModel(todosGrupos);
	    }
	    
	    @GetMapping("/{grupoId}")
	    public GrupoDTO buscar(@PathVariable Long grupoId) {
	        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
	        
	        return grupoModelAssembler.toModel(grupo);
	    }
	    
	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
	        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
	        
	        grupo = cadastroGrupo.salvar(grupo);
	        
	        return grupoModelAssembler.toModel(grupo);
	    }
	    
	    @PutMapping("/{grupoId}")
	    public GrupoDTO atualizar(@PathVariable Long grupoId,
	            @RequestBody @Valid GrupoInput grupoInput) {
	        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
	        
	        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
	        
	        grupoAtual = cadastroGrupo.salvar(grupoAtual);
	        
	        return grupoModelAssembler.toModel(grupoAtual);
	    }
	    
	    @DeleteMapping("/{grupoId}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void remover(@PathVariable Long grupoId) {
	        cadastroGrupo.excluir(grupoId);	
	    }   
}
