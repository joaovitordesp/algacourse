package br.com.alga.api.domain.repository;

import br.com.alga.api.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save(FotoProduto foto);
	
	void delete(FotoProduto foto);
}
