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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "restaurante")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false) // nullable se for true, aceita null, se for false, não aceita null
	private BigDecimal taxaFrete;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer"}) //solução para o erro do Lazy loading, caso queira ver, comente essa linha toda e dê um get em restaurantes
	@ManyToOne(fetch = FetchType.LAZY) //carregamento só caso precise
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
	@ManyToMany(fetch = FetchType.EAGER) //fazer o select de qualquer forma
	@JoinTable(name = "restaurante_forma_pagamento", //nome do relacionamento entre Restaurante e FormaPagamento
	joinColumns =  @JoinColumn( name = "restaurante_id"), //nome do atributo da tabela 
	inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) //nome do atributo da tabela 
	private List<FormasPagamento> formasPagamento = new ArrayList<>();

	@OneToMany(mappedBy = "restaurante")
	@JsonIgnore
	private List<Produto> produto = new ArrayList<>();
}
