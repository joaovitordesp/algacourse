package br.com.alga.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

import br.com.alga.api.assembler.PedidoDTOAssembler;
import br.com.alga.api.assembler.PedidoInputDisassembler;
import br.com.alga.api.assembler.PedidoResumoDTOAssembler;
import br.com.alga.api.core.data.PageableTranslator;
import br.com.alga.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.alga.api.domain.exception.NegocioException;
import br.com.alga.api.domain.filter.PedidoFilter;
import br.com.alga.api.domain.model.Pedido;
import br.com.alga.api.domain.model.Usuario;
import br.com.alga.api.domain.repository.PedidoRepository;
import br.com.alga.api.domain.service.EmissaoPedidoService;
import br.com.alga.api.infrastructure.repository.spec.PedidosSpecs;
import br.com.alga.api.model.dto.PedidoDTO;
import br.com.alga.api.model.dto.PedidoResumoDTO;
import br.com.alga.api.model.input.PedidoInput;
import lombok.var;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private EmissaoPedidoService emissaoPedido;
    
    @Autowired
    private PedidoDTOAssembler pedidoModelAssembler;
    
    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler ;
    
    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;
    
    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(@PageableDefault(size = 10) Pageable pageable ,PedidoFilter filtro) {
        pageable = traduzirPageable(pageable);
    	
    	Page<Pedido> pedidosPage = pedidoRepository
        		.findAll(PedidosSpecs.usandoFiltro(filtro), pageable);
        
        List<PedidoResumoDTO> pedidosResumosDTO = pedidoResumoDTOAssembler
        		.toCollectionModel(pedidosPage.getContent());
        
        Page<PedidoResumoDTO>  pedidosResumoDTOPage = new PageImpl<>(
        		pedidosResumosDTO, pageable, pedidosPage.getTotalElements());
        		
        return pedidosResumoDTOPage;
    }

	@GetMapping("/{codigoPedido}")
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
        
        return pedidoModelAssembler.toModel(pedido);
    }      
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
    
    
    private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = ImmutableMap.of(
				"codigo", "codigo",
				"nomeCliente", "cliente.nome",
				"restaurante.nome","restaurante.nome",
				"valorTotal", "valorTotal"
				); 
				
		return PageableTranslator.translate(pageable, mapeamento);
	}
    
    
//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoDTO> pedidosDTO = pedidoResumoDTOAssembler.toCollectionModel(pedidos);
//        
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDTO);
//        
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//        
//        if(StringUtils.isNotBlank(campos)) {
//        	filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(","))); // quebra as vírgulas em array
//        }
//        
//        pedidosWrapper.setFilters(filterProvider);
//        
//        
//        return pedidosWrapper;
//    }
}           
