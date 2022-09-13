package br.com.alga.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alga.api.core.validation.Groups;
import br.com.alga.api.core.validation.Multiplo;
import br.com.alga.api.core.validation.TaxaFrete;
import br.com.alga.api.core.validation.ValorZeroIncluiDescricao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", 
	descricaoField = "nome", descricaoObrigatoria = "Frete grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "restaurante")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank  //não pode ser nulo, vazio e em branco
	private String nome;
	
	@NotNull 
//	@PositiveOrZero(message = "{TaxaFrete.invalida}")
	@PositiveOrZero
	//@TaxaFrete
	@Column(name = "taxa_frete", nullable = false) // nullable se for true, aceita null, se for false, não aceita null
	//@Multiplo(numero  = 5) //pode tirar essa linha no futuro caso queira
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false) //serve para banco legado. Pode retirar se quiser
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded //incopora a classe endereco
	private Endereco endereco;
	
	@CreationTimestamp //anotacao do hibernate
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@Column(name = "data_atualizacao",nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "restaurante_forma_pagamento", 
	joinColumns =  @JoinColumn( name = "restaurante_id"), 
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) 
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@OneToMany(mappedBy = "restaurante")
	@JsonIgnore
	private List<Produto> produto = new ArrayList<>();
}
